package view.page.implementes;

import com.github.pagehelper.PageInfo;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.CollectionUtils;
import utils.Layout.AfColumnLayout;
import utils.Layout.AfRowLayout;
import utils.PageInfoUtils;
import utils.SwingUtil;
import view.page.dataProcessing.Constant;
import view.page.dataProcessing.jsonProcessing.JsonData;
import view.page.interfaces.Conversion;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**JSON转换页面
 * @作者 朱志鹏
 * @时间 2021-06-21 下午2:27
 */
public class JSONConversion implements Conversion {

    private  JTextArea left = SwingUtil.createTextArea();
    private  JTextArea right = SwingUtil.createTextArea();

    private JRadioButton dataBnt = SwingUtil.createJRadioButton("@data");// getting setting equals、canEqual、hashCode、toString
    private JRadioButton log4jBnt = SwingUtil.createJRadioButton("@Log4j");//log 的 log4j 日志对象
    private JRadioButton noArgsBnt = SwingUtil.createJRadioButton("@NoArgsConstructor");//无参的构造方法
    private JRadioButton allArgsBnt = SwingUtil.createJRadioButton("@AllArgsConstructor");//全参的构造方法
    private JRadioButton cleanupBnt = SwingUtil.createJRadioButton("@Cleanup");//关闭流
    private JRadioButton builderBnt = SwingUtil.createJRadioButton("@Builder");//构造者模式
    private JRadioButton accessorsBnt = SwingUtil.createJRadioButton("@Accessors");//链式
    private JTextField className = SwingUtil.createText(10);

    public void createPage(JPanel panel){
        panel.setLayout(new AfColumnLayout(2));

        JPanel up = createUp();//下半部

        JPanel on = createOn(panel); // 上半部

        panel.add(on,"10%");
        panel.add(up,"90%");
    }


    private JPanel createOn(JPanel parentPanel) {
        JPanel panel = SwingUtil.createPanel();

        panel.setLayout(new AfColumnLayout());

        JPanel labelPanel = SwingUtil.createPanel();
        labelPanel.setLayout(new AfRowLayout());

        labelPanel.add(SwingUtil.createPanel(),"10%");
        JLabel label = SwingUtil.createLabel("JSON转换");
        label.setFont(new Font("宋体",Font.BOLD,14));
        label.setForeground(Color.orange);
        labelPanel.add(label,"80%");
        labelPanel.add(SwingUtil.createPanel(),"10%");


        JPanel bntPanel = SwingUtil.createPanel();
        bntPanel.setLayout(new AfRowLayout());

        JButton formatBtn = SwingUtil.createButton("格式化");
        formatBtn.addActionListener(e -> format(parentPanel) );

        JButton beanBtn = SwingUtil.createButton("生成Java对象");
        beanBtn.addActionListener(e -> {
            if(!left.getText().isEmpty()){
                String text = left.getText();
                if(isJSON(text)){
                    setting(text);
                }else
                    JOptionPane.showMessageDialog(parentPanel,"请输入正确的JSON","JSON",JOptionPane.ERROR_MESSAGE);
            }else
                JOptionPane.showMessageDialog(parentPanel,"请输入JSON","JSON",JOptionPane.ERROR_MESSAGE);

        }); // 设置框

        bntPanel.add(formatBtn,"25%");
        bntPanel.add(SwingUtil.createPanel(),"50%");
        bntPanel.add(beanBtn,"25%");

        panel.add(labelPanel,"30%");
        panel.add(bntPanel,"70%");
        return panel;
    }

    private void setting(String text) {

        JPanel panel = SwingUtil.createPanel();
        panel.setPreferredSize(new Dimension(300,200));
        panel.setLayout(new AfColumnLayout());

        JPanel on = SwingUtil.createPanel();

        on.setLayout(new AfRowLayout());

        JLabel label = SwingUtil.createLabel("输入类名:");

        on.add(label,"20%");
        on.add(className,"40%");
        on.add(SwingUtil.createLabel("默认:JavaBean"),"40%");


        JPanel up = SwingUtil.createPanel();
        up.setLayout(new BorderLayout());
        JLabel aonntation = SwingUtil.createLabel("添加Lombok注解");
        aonntation.setForeground(Color.orange);

        JPanel panel1 = SwingUtil.createPanel();
        panel1.setLayout(new AfRowLayout());
        panel1.add(aonntation,"40%");panel1.add(SwingUtil.createPanel(),"60%");
        up.add(panel1,BorderLayout.PAGE_START);



        JPanel panel2 = SwingUtil.createPanel();
        panel2.setLayout(new AfRowLayout());
        panel2.add(dataBnt,"33%");
        panel2.add(log4jBnt,"33%");
        panel2.add(cleanupBnt,"33%");


        JPanel panel3 = SwingUtil.createPanel();
        panel3.setLayout(new AfRowLayout());
        panel3.add(builderBnt,"33%");
        panel3.add(noArgsBnt,"67%");

        JPanel panel4 = SwingUtil.createPanel();
        panel4.setLayout(new AfRowLayout());
        panel4.add( allArgsBnt,"60%");
        panel4.add( accessorsBnt,"40%");


        JPanel jPanel = SwingUtil.createPanel();
        jPanel.setLayout(new AfColumnLayout(2));
        jPanel.add(panel2,"30%");
        jPanel.add(panel3,"30%");
        jPanel.add(panel4,"40%");

        up.add(jPanel,BorderLayout.CENTER);


        panel.add(on,"15%");
        panel.add(up,"85%");

        /***
         * 确定返回 0 取消返回 2 点 x 返回 -1
         */
        int bean = JOptionPane.showConfirmDialog(SwingUtil.createPanel(),
                                                    panel,
                                              "Bean",
                                         JOptionPane.OK_CANCEL_OPTION,
                                       JOptionPane.PLAIN_MESSAGE);
        if(bean == 0) createJavaBean(text);
        closeStatus();


    }

    /***
     * 初始化值
     */
    private void closeStatus() {
        Constant.ARRAY = new ConcurrentHashMap<>();
        Constant.OBJ = new ConcurrentHashMap<>();
        dataBnt.setSelected(false);
        log4jBnt.setSelected(false);
        noArgsBnt.setSelected(false);
        cleanupBnt.setSelected(false);
        allArgsBnt.setSelected(false);
        builderBnt.setSelected(false);
        accessorsBnt.setSelected(false);
        className.setText("");
    }

    /***
     * 生成java对象
     * @param data
     */
    private void createJavaBean(String data) {
        LinkedHashMap<String,Boolean> map = exaractSelected();
        String text = className.getText();
        if(text.isEmpty())
            text = "JavaBean";
        try {

            List<String> bean =  new JsonData().processingData(data,map,text);
            resultPanel(bean);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"解析失败","JSON",JOptionPane.ERROR_MESSAGE);
        }
    }

    /***
     * 生成类 显示结果 有几个对象 显示几个窗口
     * @param bean
     */
    private void resultPanel(List<String> bean) {

        if(CollectionUtils.isNotEmpty(bean)){
            ArrayList<JTextArea> jTextAreas = new ArrayList<>();
            for (int i = 0; i < bean.size(); i++) {
                JTextArea textArea = SwingUtil.createTextArea();
                textArea.setPreferredSize(new Dimension(300,300));
                textArea.setText(bean.get(i));
                textArea.setForeground(Color.orange);
                jTextAreas.add(textArea);
            }

            JPanel panel = SwingUtil.createPanel();
            panel.setLayout(new AfColumnLayout());
            int size = jTextAreas.size() / 3;
            if(jTextAreas.size() % 3 > 0)
                size += 1;
            double x = 400;
            double percentage =  (x / (400 * size)) * 100;
//            panel.setPreferredSize(new Dimension(900,400*size));
            int pageSize = 0;
            for (int i = 1; i <= size; i++) {
                PageInfo<JTextArea> page = PageInfoUtils.getPage(jTextAreas, i, 3);
                List<JTextArea> list = page.getList();
                pageSize = list.size();
                JPanel jPanel = SwingUtil.createPanel();
                jPanel.setLayout(new AfRowLayout());
                list.forEach(e->{
                    JScrollPane scrollPane = SwingUtil.createScrollPane(e);
                    String string = "33%" ;
                    if(list.size() == 1)
                        string = "100%";
                    if(list.size() == 2)
                        string = "50%";
                    jPanel.add(scrollPane,string);
                });
                panel.add(jPanel,(int) percentage+"%");

            }
            JPanel panel1 = SwingUtil.createPanel();
            panel1.add(panel);
            JScrollPane scrollPane = SwingUtil.createScrollPane(panel);
            int y = 400;
            double width;
            if(size > 1)
                width = y * 3;
            else
                width = pageSize * y;
            panel.setPreferredSize(new Dimension((int) width,400*size));
            scrollPane.setPreferredSize(new Dimension((int) width,500));
            JOptionPane.showMessageDialog(null,scrollPane,"JSON",JOptionPane.PLAIN_MESSAGE);
        }
    }

    private LinkedHashMap<String, Boolean> exaractSelected() {
        LinkedHashMap<String, Boolean> map = new LinkedHashMap<>();
        map.put("dataBnt", dataBnt.isSelected());
        map.put("Log4jBnt", log4jBnt.isSelected());
        map.put("BuilderBnt", builderBnt.isSelected());
        map.put("CleanupBnt", cleanupBnt.isSelected());
        map.put("AccessorsBnt", accessorsBnt.isSelected());
        map.put("NoArgsConstructorBnt", noArgsBnt.isSelected());
        map.put("AllArgsConstructorBnt", allArgsBnt.isSelected());
        return map;
    }

    /***
     * 格式化json对象
     * @param parentPanel
     */
    private void format(JPanel parentPanel) {
        if(!left.getText().isEmpty()){
            String text = left.getText();
            if(isJSON(text)){
                text = text.trim();
                if (text.startsWith("{")) {
                    JSONObject jsonObject = new JSONObject(text);
                    String formatJson = jsonObject.toString(4);
                    resultPanel(formatJson);
                } else if (text.startsWith("[")) {
                    JSONArray jsonArray = new JSONArray(text);
                    String formatJson = jsonArray.toString(4);
                    resultPanel(formatJson);
                }
            }else
                JOptionPane.showMessageDialog(parentPanel,"请输入正确的JSON","JSON",JOptionPane.ERROR_MESSAGE);
        }else
            JOptionPane.showMessageDialog(parentPanel,"请输入JSON","JSON",JOptionPane.ERROR_MESSAGE);
    }

    /***
     * 显示结果
     * @param formatJson
     */
    private void resultPanel( String formatJson) {
        JTextArea area = SwingUtil.createTextArea();

        area.setText(formatJson);
        area.setTabSize(2);
        area.setForeground(Color.orange);
        JScrollPane scrollPane = SwingUtil.createScrollPane(area);

        JPanel panel = SwingUtil.createPanel();
        scrollPane.setPreferredSize(new Dimension(700,500));

        JOptionPane.showMessageDialog(panel,scrollPane,"JSON",JOptionPane.PLAIN_MESSAGE);
    }

    private JPanel createUp() {
        JPanel panel = SwingUtil.createPanel();
        panel.setLayout(new AfRowLayout(2));
        left.setTabSize(2);
        JScrollPane leftPanel = SwingUtil.createScrollPane(left);
        panel.add(leftPanel,"100%");
        return panel;
    }


    /***
     * 是否是合格的json字符串
     * @param text
     * @return
     */
    private boolean isJSON(String text) {
        try {
            text = text.trim();
            if(text.startsWith("{"))
                new JSONObject(text);
            else if (text.startsWith("["))
                new JSONArray(text);
            return true;
        }catch (Exception e){

            return false;
        }
    }

}
