package view.page.implementes;

import utils.SwingUtil;
import view.page.interfaces.Conversion;

import javax.swing.*;

/**JSON转换页面
 * @作者 朱志鹏
 * @时间 2021-06-21 下午2:27
 */
public class JSONConversion implements Conversion {

    public void createPage(JPanel panel){
        JLabel json = SwingUtil.createLabel("JSON");

        panel.add(json);
    }

}
