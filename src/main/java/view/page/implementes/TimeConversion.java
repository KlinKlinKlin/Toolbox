package view.page.implementes;

import utils.DateTools;
import utils.SwingUtil;
import view.page.interfaces.Conversion;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

/**时间戳转换页面
 * @作者 朱志鹏
 * @时间 2021-06-21 下午2:27
 */
public class TimeConversion  implements Conversion {

    public void createPage(JPanel panel){

        panel.setLayout(new BorderLayout());

        JLabel label = SwingUtil.createLabel("时间(北京)转时间戳(毫秒)", 0, 20);

        label.setForeground(Color.orange);

        JPanel jPanel = SwingUtil.createPanel();

        JTextField input = SwingUtil.createText(180, 30);

        JButton button = SwingUtil.createButton("转换",100,30);

        JTextField outPut = SwingUtil.createText(180, 30);

        button.addActionListener(e -> time2Timestamp(jPanel,input,outPut));

        jPanel.add(input);
        jPanel.add(button);
        jPanel.add(outPut);

        panel.add(label,BorderLayout.PAGE_START);
        panel.add(jPanel,BorderLayout.CENTER);
    }

    private void time2Timestamp(JPanel jPanel, JTextField input, JTextField outPut) {
        if(!input.getText().isEmpty()){
            String text = input.getText();
            if(!isLegalDate(text).isEmpty()){
                long time = DateTools.convert2Date(text,isLegalDate(text)).getTime();
                outPut.setText(time+"");
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
        }else if(isYDMHMS(text)){
            return "yyyy-MM-dd HH:mm:ss";
        }
        else
            return "";
    }


    private  boolean isYDMHMS( String sDate) {
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
