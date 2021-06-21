package utils;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @作者 朱志鹏
 * @时间 2021-06-20 下午3:35
 */
public class SwingUtil {

    /***
     * 创建一个窗口
     * @param name 窗口title
     * @param w 长度
     * @param h 宽度
     * @return
     */
    public static JFrame createJFrame(String name,int w,int h){

        JFrame jFrame = new JFrame(name);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.setResizable(false);
        
        Map<String,Integer> size = calcSystemSize(w,h);

//        HashMap<String, Integer> size = new HashMap<>(2);
//
//        size.put("x",2000);
//        size.put("y",300);

        jFrame.setBounds(size.get("x"),size.get("y"),w,h);

        return jFrame;

    }

    /***
     *计算 桌面大小 让窗口居中
     * @return
     */
    private static Map<String, Integer> calcSystemSize(int x1 ,int x2) {

        HashMap<String, Integer> map = new HashMap<>(2);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        double height = screenSize.getHeight();
        double width = screenSize.getWidth();

        BigDecimal w = new BigDecimal(width + "");
        BigDecimal h = new BigDecimal(height + "");

        String x = w.subtract(new BigDecimal(x1 + "")).divide(new BigDecimal(2 + ""), 0, BigDecimal.ROUND_DOWN).toPlainString();
        String y = h.subtract(new BigDecimal(x2 + "")).divide(new BigDecimal(2 + ""), 0, BigDecimal.ROUND_DOWN).toPlainString();

        map.put("x",Integer.parseInt(x));
        map.put("y",Integer.parseInt(y));

        return map;
    }

    /***
     * 创建一个面板
     * @return
     */
    public static JPanel createPanel(){
        return new JPanel();
    }


    /***
     * 创建一个按钮
     * @param name 显示
     * @return
     */
    public static JButton createButton(String name){
        JButton jButton = new JButton(name);
        return jButton;
    }

    /***
     * 创建一个指定大小的按钮
     * @param name
     * @param x
     * @param y
     * @return
     */
    public static JButton createButton(String name,int x,int y){
        JButton button = createButton(name);
        button.setPreferredSize(new Dimension(x,y));
        return button;
    }


    /***
     * 创建一个 标签
     * @param label
     * @return
     */
    public static JLabel createLabel(String label){
        return new JLabel(label,JLabel.CENTER);
    }

    /***
     * 创建一个带字体颜色的标签
     * @param label
     * @return
     */
    public static JLabel createLabel(String label,Color color){
        JLabel jLabel = createLabel(label);
        jLabel.setForeground(color);
        return jLabel;
    }

    /***
     * 创建一个带字体颜色字体的标签
     * @param label
     * @return
     */
    public static JLabel createLabel(String label,Color color,Font font){
        JLabel jLabel = createLabel(label);
        jLabel.setForeground(color);
        jLabel.setFont(font);
        return jLabel;
    }

    /***
     * 创建一个指定大小的标签
     * @param label
     * @return
     */
    public static JLabel createLabel(String label,int x ,int y){
        JLabel label1 = createLabel(label);
        label1.setPreferredSize(new Dimension(x,y));
        return label1;
    }

    /***
     * 创建一个指定字符长度的输入框
     * @param x
     * @return
     */
    public static JTextField createText(int x){
        JTextField field = new JTextField(x);
        return field;
    }

    /***
     * 创建一个指定长宽的输入框
     * @param x
     * @return
     */
    public static JTextField createText(int x,int y){
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(x,y));
        return field;
    }

    /***
     * 创建一个string下拉框
     * @param list
     * @return
     */
    public static JComboBox createComboBox(List<String> list){
        JComboBox<String> box = new JComboBox<>();
        if(CollectionUtils.isNotEmpty(list)){
            list.forEach(e-> box.addItem(e));
        }
        return box;
    }

    /***
     * 创建一个string下拉框
     * @param list
     * @return
     */
    public static JComboBox createTimeFormetComboBox(List<TimeFormat> list){
        JComboBox<TimeFormat> box = new JComboBox<>();
        if(CollectionUtils.isNotEmpty(list)){
            list.forEach(e-> box.addItem(e));
        }
        return box;
    }
}
