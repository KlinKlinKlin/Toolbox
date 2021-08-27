package view.page.implementes;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import utils.Layout.AfColumnLayout;
import utils.SwingUtil;
import view.page.interfaces.Conversion;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

/**PDF转换页面
 * @作者 朱志鹏
 * @时间 2021-06-21 下午2:27
 */
public class PDFConversion implements Conversion {

    public void createPage(JPanel panel){

        panel.setLayout(new AfColumnLayout());
        JPanel panel1 = SwingUtil.createPanel();
        JButton open = SwingUtil.createButton("打开");
        open.addActionListener(e->{

            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    if(f.isFile()){
                        return isPDF(f);
                    }
                    return true;
                }

                @Override
                public String getDescription() {
                    return ".pdf";
                }
            });
            jFileChooser.showOpenDialog(null);
            File file = jFileChooser.getSelectedFile();
            if(isPDF(file)){
                createWord(file);
            }
        });
        open.setPreferredSize(new Dimension(100,40));
        panel1.add(open);
        panel.add(SwingUtil.createPanel(),"40%");
        panel.add(panel1,"20%");
        panel.add(SwingUtil.createPanel(),"40%");

    }

    private boolean isPDF(File file){
        if(file != null) {
            if (file.isFile()) {
                String name = file.getName();
                String[] split = name.split("\\.");
                if (split[split.length - 1].equals("pdf"))
                    return true;
            }
        }
        return false;
    }

    private void createWord(File aFile){
        System.out.println(aFile.getPath());
        PdfDocument pdf = new PdfDocument();
        pdf.loadFromFile(aFile.getPath());
        String path = aFile.getParent();
        // 保存为Word格式
        String[] split = aFile.getName().split("\\.");
        path = path+"/"+split[0]+".doc";
        pdf.saveToFile(path, FileFormat.DOC);
    }

}
