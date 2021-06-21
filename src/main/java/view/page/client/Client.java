package view.page.client;

import view.page.interfaces.Conversion;
import view.page.interfaces.Pages;

import javax.swing.*; /**
 * @作者 朱志鹏
 * @时间 2021-06-21 下午2:42
 */
public class Client {

    private Pages page;


    public Client(Pages page) {
        this.page = page;
    }

    public  void createPage(JPanel panel, String menu) {
        page.createPage(panel,menu);
    }

    public static void createPage(Conversion conversion,JPanel panel){
        conversion.createPage(panel);
    }
}
