package view.page.implementes;

import utils.DateTools;
import utils.Layout.AfColumnLayout;
import utils.Layout.AfRowLayout;
import utils.SwingUtil;
import utils.TimeFormat;
import view.page.interfaces.Conversion;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


/**时间戳转换页面
 * @作者 朱志鹏
 * @时间 2021-06-21 下午2:27
 */
public class TimeConversion  implements Conversion {

    /***
     * v 1.1
     * @param panel
     */
    @Override
    public void createPage(JPanel panel) {
        panel.setLayout(new AfColumnLayout());
        JPanel onPanel = createOn(panel); // 创建上半部
        JPanel upPanel = createUp(panel); // 创建下半部
        panel.add(onPanel,"50%");
        panel.add(upPanel,"50%");
    }

    /***
     * 上半区域
     * @return
     */
    private JPanel createOn(JPanel parentPanel) {
        JPanel panel = SwingUtil.createPanel();
        panel.setLayout(new AfColumnLayout());


        JPanel labelPanel = SwingUtil.createPanel();
        labelPanel.setLayout(new AfColumnLayout());
        JLabel label = SwingUtil.createLabel("(北京)时间 ==> 时间戳(秒/毫秒)",Color.orange,new Font("楷体",Font.BOLD, 16));
        JLabel time1 = SwingUtil.createLabel("支持【HH:mm:ss】",Color.orange,new Font("楷体",Font.BOLD, 16));
        JLabel time2 = SwingUtil.createLabel("支持【yyyy-MM-dd】",Color.orange,new Font("楷体",Font.BOLD, 16));
        JLabel time3 = SwingUtil.createLabel("支持【yyyy-MM-dd HH:mm:ss】",Color.orange,new Font("楷体",Font.BOLD, 16));
        labelPanel.add(label,"25%");
        labelPanel.add(time1,"25%");
        labelPanel.add(time2,"25%");
        labelPanel.add(time3,"25%");



        JPanel jPanel = SwingUtil.createPanel();
        jPanel.setLayout(new AfRowLayout());
        JPanel left = SwingUtil.createPanel();
        left.setLayout(new AfRowLayout());
        JPanel laft_l = SwingUtil.createPanel();
        JPanel laft_r = SwingUtil.createPanel();
        JButton currentTime = SwingUtil.createButton("获取当前时间");
        left.add(laft_l,"15%");
        left.add(currentTime,"70%");
        left.add(laft_r,"15%");

        JPanel right = SwingUtil.createPanel();
        right.setLayout(new AfRowLayout());
        JButton clear = SwingUtil.createButton("清空");
        right.add(clear,"50%");
        right.add(SwingUtil.createPanel(),"50%");


        ArrayList<String> items = new ArrayList<>();
        items.add("秒");
        items.add("毫秒");
        JComboBox comboBox = SwingUtil.createComboBox(items);
        comboBox.setSelectedItem("毫秒");
        jPanel.add(left,"40%");
        jPanel.add(right,"40%");
        jPanel.add(comboBox,"20%");



        JPanel panel2 = SwingUtil.createPanel();
        panel2.setLayout(new AfRowLayout());
        JTextField inPut = SwingUtil.createText(180, 20);
        JButton button = SwingUtil.createButton("转换", 50, 20);
        JTextField outPut = SwingUtil.createText(180, 20);
        button.addActionListener(e -> time2Timestamp(parentPanel,comboBox,inPut,outPut));// 转换按钮
        currentTime.addActionListener(e->{ // 获取当期时间
            String string = DateTools.convert2String(new Date(), "yyyy-MM-dd HH:mm:ss");
            inPut.setText(string);
        });
        clear.addActionListener(e->{ // 清空按钮
            inPut.setText("");
            outPut.setText("");
        });
        panel2.add(inPut,"40%");
        panel2.add(button,"20%");
        panel2.add(outPut,"40%");


        panel.add(labelPanel,"35%");
        panel.add(SwingUtil.createPanel(),"10%");
        panel.add(jPanel,"15%");
        panel.add(panel2,"15%");
        panel.add(SwingUtil.createPanel(),"25%");
        return panel;
    }


    /***
     * 下半区域
     * @return
     */
    private JPanel createUp(JPanel parentPanel) {

        JPanel panel = SwingUtil.createPanel();
        panel.setLayout(new AfColumnLayout());

        JLabel label = SwingUtil.createLabel("时间戳(秒/毫秒) ==> 时间(北京)",Color.orange,new Font("楷体",Font.BOLD, 16));

        JPanel jPanel = SwingUtil.createPanel();
        jPanel.setLayout(new AfRowLayout());
        JPanel left = SwingUtil.createPanel();

        left.setLayout(new AfRowLayout());
        JPanel laft_l = SwingUtil.createPanel();
        JPanel laft_r = SwingUtil.createPanel();
        JButton currentTime = SwingUtil.createButton("获取当前时间戳");
        left.add(laft_l,"15%");
        left.add(currentTime,"70%");
        left.add(laft_r,"15%");


        JPanel right = SwingUtil.createPanel();
        right.setLayout(new AfRowLayout());
        JButton clear = SwingUtil.createButton("清空");
        right.add(clear,"100%");
//        right.add(SwingUtil.createPanel(),"50%");

        List<TimeFormat> formats = TimeFormat.getFormats();
        JComboBox comboBox = SwingUtil.createTimeFormetComboBox(formats);
        comboBox.setSelectedItem(formats.get(3));
        jPanel.add(left,"40%");
        jPanel.add(right,"20%");
        jPanel.add(comboBox,"40%");



        JPanel panel2 = SwingUtil.createPanel();
        panel2.setLayout(new AfRowLayout());
        JTextField inPut = SwingUtil.createText(180, 20);
        JButton button = SwingUtil.createButton("转换", 50, 20);
        JTextField outPut = SwingUtil.createText(180, 20);
        button.addActionListener(e -> timestamp2Time(parentPanel,comboBox,inPut,outPut)); // 转换按钮
        currentTime.addActionListener(e->{ //获取当前时间戳
            long time = new Date().getTime();
            inPut.setText(time+"");
        });
        clear.addActionListener(e->{ // 清空
            inPut.setText("");
            outPut.setText("");
        });
        panel2.add(inPut,"40%");
        panel2.add(button,"20%");
        panel2.add(outPut,"40%");

        JPanel version = SwingUtil.createPanel();

        version.setLayout(new BorderLayout());

        JLabel versionLabel = SwingUtil.createLabel("TimeConversion Version 1.0 from Callista");
        versionLabel.setForeground(Color.black);
        versionLabel.setFont(new Font("宋体",Font.BOLD,12));
        version.add(versionLabel,BorderLayout.CENTER);

        panel.add(label,"15%");
        panel.add(jPanel,"15%");
        panel.add(panel2,"15%");
        panel.add(version,"55%");
        return panel;
    }

    /***
     * 时间戳转时间格式
     * @param parentPanel
     * @param comboBox
     * @param inPut
     * @param outPut
     */
    private void timestamp2Time(JPanel parentPanel, JComboBox comboBox, JTextField input, JTextField outPut) {
        if(!input.getText().isEmpty()){

            TimeFormat item = (TimeFormat)comboBox.getSelectedItem();
            String text = input.getText();

            if(isNumber(text)){

                long aLong = Long.parseLong(text);
                if(text.length() == 10) {
                     aLong = Long.parseLong(text) * 1000;
                }

                String date = DateTools.convert2String(new Date(aLong), item.getFormat());

                outPut.setText(date);

            }else
                JOptionPane.showMessageDialog(parentPanel,"请正确输入时间","提示",JOptionPane.ERROR_MESSAGE);

        }else
            JOptionPane.showMessageDialog(parentPanel,"请输入时间","提示",JOptionPane.ERROR_MESSAGE);
    }

    private boolean isNumber(String text) {
        try {
            Long.parseLong(text);
            return true;
        }catch (Exception e){

            return false;
        }
    }


    /***
     * 时间 格式 转 时间戳
     * @param jPanel
     * @param box
     * @param input
     * @param outPut
     */
    private void time2Timestamp(JPanel jPanel, JComboBox box,JTextField input, JTextField outPut) {
        if(!input.getText().isEmpty()){
            String item = (String)box.getSelectedItem();
            String text = input.getText();
            if(!isLegalDate(text).isEmpty()){
                long time = DateTools.convert2Date(text,isLegalDate(text)).getTime();
                if(item.equals("秒"))
                    time /=  1000;
                outPut.setText(time  + "");
            }else
                JOptionPane.showMessageDialog(jPanel,"请正确输入时间","提示",JOptionPane.ERROR_MESSAGE);

        }else
            JOptionPane.showMessageDialog(jPanel,"请输入时间","提示",JOptionPane.ERROR_MESSAGE);

    }

    /***
     * 校验日期格式
     * @param text
     * @return
     */
    private String isLegalDate(String text) {
        if(isYMD(text)){
            return "yyyy-MM-dd";
        }else if(isHMS(text)){
            return "HH:mm:ss";
        }else if(isYMDHMS(text)){
            return "yyyy-MM-dd HH:mm:ss";
        }
        else
            return "";
    }


    private  boolean isYMDHMS( String sDate) {
            Pattern p = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1][0-9])|([2][0-4]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
            return p.matcher(sDate).matches();
    }

    public  boolean isYMD(String date){
        Pattern p = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$");
        return p.matcher(date).matches();
    }

    public  boolean isHMS(String time){
        Pattern p = Pattern.compile("((((0?[0-9])|([1][0-9])|([2][0-4]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        return p.matcher(time).matches();
    }



}
