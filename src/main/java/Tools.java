import com.formdev.flatlaf.FlatDarculaLaf;
import view.View;

import java.awt.*;

/**
 * @作者 朱志鹏
 * @时间 2021-06-20 23:36
 */
public class Tools {
    public static void main(String[] args) {
//       mac 顶部菜单栏 设置打开
        System.setProperty("apple.laf.useScreenMenuBar", "true");
//      把菜单 显示到顶部菜单栏
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Name");
        System.out.println("test");
        try {
            // 皮肤
            FlatDarculaLaf.install();
            // 线程 启动窗口
            EventQueue.invokeLater(() -> View.createGUI());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
