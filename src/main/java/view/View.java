package view;

import utils.SwingUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @作者 朱志鹏
 * @时间 2021-06-20 23:37
 */
public class View {

    private static final String MENU [] = {"时间戳转换","JSON转换","Mysql连接测试","Redis连接测试","查看PDM文件"};
    public static void createGUI() {
        // 创建一个 窗口
        JFrame frame = SwingUtil.createJFrame("小工具", 500, 500);
        // 底层容器
        Container contentPane = frame.getContentPane(); // 窗口容器
        // 一个面板
        JPanel pages = SwingUtil.createPanel();
        // 设置这个面板需要的内容
        createPage(pages);
        // 把面板添加到底层容器
        contentPane.add(pages);
        // 创建菜单
        createMenus(frame,pages);
        // 打开显示
        frame.setVisible(true);//打开显示
    }

    /***
     * 设置 多级菜单 一个菜单对应一个页面
     * @param frame
     * @param pages
     */
    private static void createMenus(JFrame frame, JPanel pages) {

        JMenuBar jMenuBar = new JMenuBar();

        JMenu jMenu = new JMenu("菜单");
        for (int i = 0; i < MENU.length; i++) {
            JMenuItem jMenuItem = new JMenuItem(MENU[i]);
            jMenuItem.addActionListener(e->showPage(e,pages));
            jMenu.add(jMenuItem);
        }

        jMenuBar.add(jMenu);

        frame.setJMenuBar(jMenuBar);


    }

// 点击事件 回调 设置
    private static void showPage(ActionEvent e, JPanel pages) {
        CardLayout layout = (CardLayout) pages.getLayout();
        layout.show(pages,  e.getActionCommand());

    }

    // 塞入 几个页面 卡片布局  一个跌一个
    private static void createPage(JPanel pages) {
        pages.setLayout(new CardLayout());

        for (int i = 0; i < MENU.length; i++) {

            JPanel panel = SwingUtil.createPanel();

            panel.add(SwingUtil.createLabel(MENU[i]));

            pages.add(MENU[i],panel);
        }
    }
}