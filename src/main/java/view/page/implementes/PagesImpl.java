package view.page.implementes;

import view.Menus;
import view.page.client.Client;
import view.page.interfaces.Conversion;
import view.page.interfaces.Pages;

import javax.swing.*;

/**
 * @作者 朱志鹏
 * @时间 2021-06-21 下午2:44
 */
public class PagesImpl implements Pages {

    private static String menus [] = Menus.MENU;

    private Conversion conversion;


    @Override
    public void createPage(JPanel panel,String menu) {

        if (menu.equals(menus[0])){
            Client.createPage(new TimeConversion(),panel);
        }else {
            switch (menu){
                case "JSON转换":
                                    Client.createPage(new JSONConversion(),panel); break;
                case "Mysql连接测试":
                                    Client.createPage(new MysqlConversion(),panel); break;
                case "Redis连接测试":
                                    Client.createPage(new RedisConversion(),panel); break;
                case "查看PDM文件":
                                    Client.createPage(new PDMConversion(),panel); break;
                case "PDF转Word":
                                    Client.createPage(new PDFConversion(),panel); break;
            }
        }
    }
}
