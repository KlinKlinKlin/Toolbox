package view.page.implementes;

import org.json.JSONArray;
import org.json.JSONObject;
import utils.Layout.AfColumnLayout;
import utils.Layout.AfRowLayout;
import utils.SwingUtil;
import view.page.interfaces.Conversion;

import javax.swing.*;
import java.awt.*;

/**JSON转换页面
 * @作者 朱志鹏
 * @时间 2021-06-21 下午2:27
 */
public class JSONConversion implements Conversion {

    private  JTextArea left = SwingUtil.createTextArea();
    private  JTextArea right = SwingUtil.createTextArea();

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

        bntPanel.add(formatBtn,"25%");
        bntPanel.add(SwingUtil.createPanel(),"50%");
        bntPanel.add(beanBtn,"25%");


        panel.add(labelPanel,"30%");
        panel.add(bntPanel,"70%");
        return panel;
    }

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
