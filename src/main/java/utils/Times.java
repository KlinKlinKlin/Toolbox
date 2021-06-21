package utils;

/**用于 账单周期拆分成 自然月
 * @作者 朱志鹏
 * @时间 2021-05-11 12:01
 */
public class Times {
    private int year;// 年份
    private int month; // 当前几月
    private int day; // 多少天
    private String totalAmount; // 总价


    public String getYearString(){
        return getYear()+"";
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public Times setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Times setYear(int year) {
        this.year = year;
        return this;
    }

    public int getMonth() {
        return month;
    }

    public Times setMonth(int month) {
        this.month = month;
        return this;
    }

    public int getDay() {
        return day;
    }

    public Times setDay(int day) {
        this.day = day;
        return this;
    }

}
