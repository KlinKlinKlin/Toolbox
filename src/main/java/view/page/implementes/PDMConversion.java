package view.page.implementes;

import utils.CollectionUtils;
import utils.Layout.AfColumnLayout;
import utils.SwingUtil;
import view.page.implementes.pdm.PDMParser;
import view.page.implementes.pdm.bean.Category;
import view.page.implementes.pdm.bean.Column;
import view.page.implementes.pdm.bean.Project;
import view.page.implementes.pdm.bean.Table;
import view.page.interfaces.Conversion;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**绘制PDM页面
 * @作者 朱志鹏
 * @时间 2021-06-21 下午2:27
 */
public class PDMConversion implements Conversion {


    public void createPage(JPanel panel){

        panel.setLayout(new AfColumnLayout());
        JPanel panel1 = SwingUtil.createPanel();
        JButton open = SwingUtil.createButton("打开");
        open.addActionListener(e->{

            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    if(f.isFile()){
                        return isPDM(f);
                    }
                    return true;
                }

                @Override
                public String getDescription() {
                    return ".pdm";
                }
            });
            jFileChooser.showOpenDialog(null);
            File file = jFileChooser.getSelectedFile();
            if(isPDM(file)){
                try {
                    Project parse = PDMParser.parse(file);
                    createPDM(parse);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null,"解析失败","PDM",JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        open.setPreferredSize(new Dimension(100,40));
        panel1.add(open);
        panel.add(SwingUtil.createPanel(),"40%");
        panel.add(panel1,"20%");
        panel.add(SwingUtil.createPanel(),"40%");
    }

    private void createPDM(Project parse) {

        JPanel panel = SwingUtil.createPanel();
        panel.setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        width -= 100;
        height -= 200;
        panel.setPreferredSize(new Dimension((int) width, (int) height));

        DefaultMutableTreeNode root = new DefaultMutableTreeNode(parse.getName().isEmpty()?"":parse.getName());
        if (parse != null)
        {
            if(CollectionUtils.isNotEmpty(parse.getCategoryList())){
                parse.getCategoryList().forEach(e-> {
                    DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(e.getName());
                    root.add(treeNode);
                    if(CollectionUtils.isNotEmpty(e.getTableList())){
                        e.getTableList().forEach(x-> treeNode.add(new DefaultMutableTreeNode(x.getName())));
                    }
                });
            }
            if(CollectionUtils.isNotEmpty(parse.getTableList())){
                DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("所有");
                root.add(treeNode);
                parse.getTableList().forEach(e->treeNode.add(new DefaultMutableTreeNode(e.getName())));
            }
        }

        JTree jTree = new JTree(root);

        JScrollPane scrollPane = SwingUtil.createScrollPane(jTree);
        scrollPane.setPreferredSize(new Dimension(200,0));

        panel.add(scrollPane,BorderLayout.LINE_START);

        JPanel right = SwingUtil.createPanel();
//        right.setBorder(new LineBorder(new Color(0x9B30FF),2));

        panel.add(right,BorderLayout.CENTER);


        jTree.addTreeSelectionListener(e->{
            String rootName = root.getUserObject().toString();
            if(e.getNewLeadSelectionPath()!= null) {
                DefaultMutableTreeNode component = (DefaultMutableTreeNode) e.getNewLeadSelectionPath().getLastPathComponent();
                String nodeName = component.getUserObject().toString();
                if(!nodeName.equals(rootName)){
                    showTable(right,nodeName,parse);
                }
            }
        });
        JOptionPane.showMessageDialog(null,panel,"PDM",JOptionPane.PLAIN_MESSAGE);




    }

    /***
     * 绘制图形
     * @param panel 居中底板
     * @param nodeName 选中的节点
     * @param parse 解析的数据
     */
    private void showTable(JPanel panel, String nodeName, Project parse) {

        Component[] components = panel.getComponents();
        if (components.length > 0)
        {
            Arrays.stream(components).forEach(e->panel.remove(e));
        }
        if (nodeName.equals("所有")) {

            panel.updateUI();
            return;
        }

        JPanel table = SwingUtil.createPanel();
        String head [] = {"字段","类型","是否主键","备注"};

        final double[] heigt = {0.0};

        Category category = parse.getCategoryList().stream().filter(f -> f.getName().equals(nodeName)).findFirst().orElse(null);
        List<Table> tableList = new ArrayList<>();
        if(category != null) {
            tableList  = category.getTableList();
        }else {
            Table table1 = parse.getTableList().stream().filter(f -> f.getName().equals(nodeName)).findFirst().orElse(null);
            if(table1 != null) {
                tableList.add(table1);
            }
        }

        if(CollectionUtils.isNotEmpty(tableList)) {
            tableList.forEach(e -> {
                JPanel jPanel = SwingUtil.createPanel();
                jPanel.setLayout(new BorderLayout());

                String data[][] = new String[e.getColumns().size()][head.length];
                heigt[0] += e.getColumns().size() * 40 + 40;
                List<Column> columns = e.getColumns();
                for (int i = 0; i < columns.size(); i++) {
                    for (int j = 0; j < head.length; j++) {
                        switch (j) {
                            case 0:
                                data[i][j] = columns.get(i).getCode();
                                break;
                            case 1:
                                data[i][j] = columns.get(i).getType();
                                break;
                            case 2:
                                data[i][j] = columns.get(i).isPkFlag() + "";
                                break;
                            case 3:
                                data[i][j] = columns.get(i).getComment();
                                break;
                        }
                    }
                }

                JTable jTable = new MyTable(data, head);
                jTable.setBackground(Color.white);
                jTable.setPreferredSize(new Dimension(40 * 10, e.getColumns().size() * 20));
                String code = e.getCode() +"【"+ e.getName()+"】";
                jTable.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(e.getClickCount() == 2){
                            JOptionPane.showMessageDialog(panel,"双击","PDM",JOptionPane.PLAIN_MESSAGE);
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        jTable.setToolTipText(code);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });

                jPanel.add(jTable.getTableHeader(), BorderLayout.NORTH);
                jPanel.add(jTable, BorderLayout.CENTER);

                table.add(jPanel);

            });
        }


        table.setPreferredSize(new Dimension(panel.getWidth() - 12,(int)heigt[0]));
        JScrollPane scrollPane = SwingUtil.createScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(panel.getWidth() - 10,panel.getHeight() - 10));
        panel.add(scrollPane);
        panel.updateUI();

    }


    private boolean isPDM(File file){
        if(file != null) {
            if (file.isFile()) {
                String name = file.getName();
                String[] split = name.split("\\.");
                if (split[split.length - 1].equals("pdm"))
                    return true;
            }
        }
        return false;
    }

    class MyTable extends JTable {
        public MyTable(final Object[][] rowData, final Object[] columnNames) {
            super(rowData, columnNames);// 调用父类 构造方法
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // 重写 不允许编辑表格
        }
    }
}
