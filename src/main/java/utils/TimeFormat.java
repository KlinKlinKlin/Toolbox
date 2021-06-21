package utils;

import java.util.ArrayList;
import java.util.List;

/** 时间戳转时间用
 * @作者 朱志鹏
 * @时间 2021-06-21 下午11:36
 */
public class TimeFormat {
    private String name;
    private String format;
    private static final String item [] = {"年","年-月","年/月","年-月-日","年/月/日","年-月-日 时:分:秒","年/月/日 时/分/秒"};
    private static final String itemfFormat [] = {"yyyy","yyyy-MM","yyyy/MM","yyyy-MM-dd","yyyy/MM/dd","yyyy-MM-dd HH:mm:ss","yyyy/MM/dd HH/mm/ss"};


    public String getName() {
        return name;
    }

    private TimeFormat setName(String name) {
        this.name = name;
        return this;
    }

    public String getFormat() {
        return format;
    }

    private TimeFormat setFormat(String format) {
        this.format = format;
        return this;
    }

    public  static List<TimeFormat> getFormats(){
        ArrayList<TimeFormat> formats = new ArrayList<>();

        for (int i = 0; i < item.length; i++) {
            formats.add(new TimeFormat().setName(item[i]).setFormat(itemfFormat[i]));
        }
        return formats;
    }

    @Override
    public String toString() {
        return  this.name ;
    }
}
