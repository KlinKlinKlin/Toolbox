package utils;



import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateTools {
    /**
     * @Expanation: 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     * @Author 朱志鹏
     * @param nowTime 判断的时间
     * @param startTime 区间开始时间
     * @param endTime   区间结束时间
     * @Return boolean
     * @Date 2020/12/4 14:45
     */
    public static boolean isBetweenDate(Date nowTime, Date startTime, Date endTime) {

        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 返回当前时间的上一个月的 开始和结束时间
     *
     * @param date
     * @return
     */
    public static List<Date> jianDays(Date date) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        int year = DateTools.getYear(date);
        int month = DateTools.getMonth(date);
        int daysByMonth = DateTools.getDaysByMonth(date);
        calendar1.set(year, month - 2, 1);
        calendar2.set(year, month - 2, daysByMonth);

        return Arrays.asList(calendar1.getTime(), calendar2.getTime());
    }

    public static String toString(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static String toString(Date date) {
        return toString(date, "yyyy-MM-dd");
    }

    public static String toStringC(Date date) {
        return toString(date, "yyyy年MM月dd日");
    }


    public static String toString2(Date date) {
        if(date != null){
            return toString(date, "yyyy-MM-dd");
        }
        return "无";
    }


    public static String toStringHMS(Date date) {
        return toString(date, "yyyy-MM-dd HH:mm:ss");
    }

    //获取当天的00:00:00
    public static Date getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    //获取当天的23:59:59
    public static Date getnowEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    /**
     * 把日期字符串格式化成日期类型
     * @param dateStr
     * @param format
     * @return
     */
    public static Date convert2Date(String dateStr, String format) {
        SimpleDateFormat simple = new SimpleDateFormat(format);
        try {
            simple.setLenient(false);
            return simple.parse(dateStr);
        } catch (Exception e) {
            return  null;
        }
    }

    /**
     * 把字符串格式化成 yyyy-MM-dd
     * @param data
     * @return
     */
    public static Date convertDateHMS(String data){
        return convert2Date(data,"yyyy-MM-dd");
    }

    /**
     * 把日期类型格式化成字符串
     * @param date
     * @param format
     * @return
     */
    public static String convert2String(Date date, String format) {
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            return formater.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 转sql的time格式
     * @param date
     * @return
     */
    public static java.sql.Timestamp convertSqlTime(Date date){
        java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
        return timestamp;
    }

    /**
     * 转sql的日期格式
     * @param date
     * @return
     */
    public static java.sql.Date convertSqlDate(Date date){
        java.sql.Date Datetamp = new java.sql.Date(date.getTime());
        return Datetamp;
    }



    /**
     * 获取当前日期
     * @param
     * @return
     */
    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * 获取当前日期
     * @param
     * @return
     */
    public static String getCurrentDate2() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }


    /**
     * 获取当前日期
     * @param format
     * @return
     */
    public static String getCurrentDate(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * 获取时间戳
     * @return
     */
    public static long getTimestamp()
    {
        return System.currentTimeMillis();
    }

    /**
     * 获取月份的天数
     * @param year
     * @param month
     * @return
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取日期的年
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取日期的月
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期的日
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取日期的时
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR);
    }

    /**
     * 获取日期的分种
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获取日期的秒
     * @param date
     * @return
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 获取星期几
     * @param date
     * @return
     */
    public static int getWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek-1;
    }

    /**
     * 获取哪一年共有多少周
     * @param year
     * @return
     */
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        return getWeekNumOfYear(c.getTime());
    }

    /**
     * 取得某天是一年中的多少周
     * @param date
     * @return
     */
    public static int getWeekNumOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 取得某天所在周的第一天
     * @param
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        return c.getTime();
    }

    /**
     * 取得某天所在周的最后一天
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
        return c.getTime();
    }

    /**
     * 取得某年某周的第一天 对于交叉:2008-12-29到2009-01-04属于2008年的最后一周,2009-01-05为2009年第一周的第一天
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar calFirst = Calendar.getInstance();
        calFirst.set(year, 0, 7);
        Date firstDate = getFirstDayOfWeek(calFirst.getTime());

        Calendar firstDateCal = Calendar.getInstance();
        firstDateCal.setTime(firstDate);

        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, firstDateCal.get(Calendar.DATE));

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, (week - 1) * 7);
        firstDate = getFirstDayOfWeek(cal.getTime());

        return firstDate;
    }

    /**
     * 取得某年某周的最后一天 对于交叉:2008-12-29到2009-01-04属于2008年的最后一周, 2009-01-04为
     * 2008年最后一周的最后一天
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        Calendar calLast = Calendar.getInstance();
        calLast.set(year, 0, 7);
        Date firstDate = getLastDayOfWeek(calLast.getTime());

        Calendar firstDateCal = Calendar.getInstance();
        firstDateCal.setTime(firstDate);

        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, firstDateCal.get(Calendar.DATE));

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, (week - 1) * 7);
        Date lastDate = getLastDayOfWeek(cal.getTime());

        return lastDate;
    }


    private static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(calendarField, amount);
            return c.getTime();
        }
    }

    /*
     * 1则代表的是对年份操作， 2是对月份操作， 3是对星期操作， 5是对日期操作， 11是对小时操作， 12是对分钟操作， 13是对秒操作，
     * 14是对毫秒操作
     */

    /**
     * 增加年
     * @param date
     * @param amount
     * @return
     */
    public static Date addYears(Date date, int amount) {
        return add(date, 1, amount);
    }

    /**
     * 增加月
     * @param date
     * @param amount
     * @return
     */
    public static Date addMonths(Date date, int amount) {
        return add(date, 2, amount);
    }

    /**
     * 增加周
     * @param date
     * @param amount
     * @return
     */
    public static Date addWeeks(Date date, int amount) {
        return add(date, 3, amount);
    }

    /**
     * 增加天
     * @param date
     * @param amount
     * @return
     */
    public static Date addDays(Date date, int amount) {
        return add(date, 5, amount);
    }

    /**
     * 增加时
     * @param date
     * @param amount
     * @return
     */
    public static Date addHours(Date date, int amount) {
        return add(date, 11, amount);
    }

    /**
     * 增加分
     * @param date
     * @param amount
     * @return
     */
    public static Date addMinutes(Date date, int amount) {
        return add(date, 12, amount);
    }



    /**
     * 增加秒
     * @param date
     * @param amount
     * @return
     */
    public static Date addSeconds(Date date, int amount) {
        return add(date, 13, amount);
    }

    /**
     * 增加毫秒
     * @param date
     * @param amount
     * @return
     */
    public static Date addMilliseconds(Date date, int amount) {
        return add(date, 14, amount);
    }



    /**
     * time差
     * @param before
     * @param after
     * @return
     */
    public static long diffTimes(Date before, Date after){
        return after.getTime() - before.getTime();
    }

    /**
     * 秒差
     * @param before
     * @param after
     * @return
     */
    public static long diffSecond(Date before, Date after){
        return (after.getTime() - before.getTime())/1000;
    }

    /**
     * 分种差
     * @param before
     * @param after
     * @return
     */
    public static int diffMinute(Date before, Date after){
        return (int)(after.getTime() - before.getTime())/1000/60;
    }

    /**
     * 时差
     * @param before
     * @param after
     * @return
     */
    public static int diffHour(Date before, Date after){
        return (int)(after.getTime() - before.getTime())/1000/60/60;
    }

    /**
     * 天数差
     * @param
     * @param
     * @return
     */
    public static int diffDay(Date before, Date after) {
        return Integer.parseInt(String.valueOf(((after.getTime() - before.getTime()) / 86400000)));
    }

    /**
     * 月差
     * @param before
     * @param after
     * @return
     */
    public static int diffMonth(Date before, Date after){
        int monthAll=0;
        int yearsX = diffYear(before,after);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(before);
        c2.setTime(after);
        int monthsX = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        monthAll=yearsX*12+monthsX;
        int daysX =c2.get(Calendar.DATE) - c1.get(Calendar.DATE);
        if(daysX>0){
            monthAll=monthAll+1;
        }
        return monthAll;
    }

    /**
     * 年差
     * @param before
     * @param after
     * @return
     */
    public static int diffYear(Date before, Date after) {
        return getYear(after) - getYear(before);
    }

    /**
     * 设置23:59:59
     * @param date
     * @return
     */
    public static Date setEndDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

/*    public static java.sql.Timestamp setEndDay(Date date,int day,int hour){
    	Calendar calendar = Calendar.getInstance();
    	 calendar.setTime(date);
    	if (day>0){
    		addDays(date,day);
    	}

    	 calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 59);
        java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTime().getTime());
        return timestamp;
    }*/

    /**
     * 设置00:00:00
     * @param date
     * @return
     */
    public static Date setStartDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        return calendar.getTime();
    }

//    public static void main(String[] args) {
//    	System.out.println(setEndDay(new Date(),1,23));
//    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year)
    {

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }


    /**
     * 获取某年的最后一天
     */
    public static Date getYearLast(int year)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH,11);
        calendar.set(Calendar.DAY_OF_MONTH,31);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }


    /**
     * 两个时间比较
     *
     * @param before 开始时间
     * @return Date
     */
    public static List<Date> computeYear(Date before, Date last){

        int yearLength = diffYear(before, last);
        List<Date> years = new ArrayList<>();
        years.add(before);
        if(yearLength > 0){
            int yearBegin = getYear(before);
            for (int i = 1; i <= yearLength; i++) {
                Date yearTime = getYearFirst(yearBegin + i);

                Date date = DateTools.addDays(yearTime, -1);

                years.add(date);
                years.add(yearTime);
            }
        }
        years.add(last);
        return years;
    }

    /**
     * @Expanation: 两个时间比较 返回 小的时间
     * @Author
     * @param data1
     * @param data2
     * @Return java.util.Date
     */
    public static Date compare(Date data1,Date data2)
    {
       if(data1.before(data2))
           return data1;
       else if(data1.after(data2))
           return data2;
       else
           return data1;
    }
    /**
     *
     * @param before
     * @param jifeitianshu 计费天数
     * @return
     */
    public static List<Date> computeYear(Date before,int jifeitianshu){
        Date endDate = addDays(before, jifeitianshu);
        return computeYear(before,endDate);
    }

    /**
     *
     * @param before
     * @param jifeitianshu 计费月数
     * @return
     */
    public static List<Date> computeYearWithMonths(Date before,int jifeitianshu){
        Date endDate = addMonths(before, jifeitianshu);
        return computeYear(before,endDate);
    }

    /**
     *字符串的日期格式的计算
     */
    public static int daysBetween(String smdate,String bdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate 较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate,Date bdate) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));

    }

    /**
     * 计算两个时间段内重叠多少天
     * @param start   开始时间1
     * @param end     结束时间1
     * @param yStart  开始时间2
     * @param yEnd    结束时间2
     * @return
     */
    public static int timesOverlap(Date start,Date end,Date yStart,Date yEnd) throws ParseException {
        if(start.getTime()<=yEnd.getTime() && end.getTime()>=yStart.getTime()){
            if(start.getTime() <= yStart.getTime() && end.getTime() >= yEnd.getTime()){
                return daysBetween(yStart,yEnd)+1;
            }

            if(start.getTime() <= yStart.getTime()){
                return daysBetween(yStart,end)+1;
            } else {
                return daysBetween(start,yEnd)+1;
            }
        }
        return 0;
    }


    public static Map<String,Date> getTimesOverlapDay(Date start,Date end,Date yStart,Date yEnd) throws ParseException{
        Map<String,Date> map = new HashMap<>();
        if(start.getTime()<=yEnd.getTime() && end.getTime()>=yStart.getTime()){
            if(start.getTime() <= yStart.getTime() && end.getTime() >= yEnd.getTime()){
                map.put("start",yStart);
                map.put("end",yEnd);
                return map;
            }

            if(start.getTime() <= yStart.getTime()){
                map.put("start",yStart);
                map.put("end",end);
                return map;
            } else {
                map.put("start",start);
                map.put("end",yEnd);
                return map;
            }
        }
        return null;
    }




    public static int timesOverlapByMonth(Date start,Date end,Date yStart,Date yEnd) throws ParseException {
        if(start.getTime()<=yEnd.getTime() && end.getTime()>=yStart.getTime()){
            if(start.getTime() <= yStart.getTime() && end.getTime() >= yEnd.getTime()){
                return diffMonth2(yStart,yEnd);
            }

            if(start.getTime() <= yStart.getTime()){
                return diffMonth2(yStart,end);
            } else {
                if(end.getTime() <= yEnd.getTime()){
                    return diffMonth2(start,end);
                }
                return diffMonth2(start,yEnd);
            }
        }
        return 0;
    }

    /**
     * 获取当前时间的月份有多少天
     * @param date
     * @return
     */
    public static int getDaysByMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当年的第一天
     * @return
     */
    public static Date getCurrYearFirst(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年的最后一天
     * @return
     */
    public static Date getCurrYearLast(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }


    /**
     * 获取某季度的第一天和最后一天
     */
    public static Map<String,String> getCurrQuarter(int year,int num) {
        Map<String,String> map = new HashMap<>();
        String str = "";
        // 设置本年的季
        Calendar quarterCalendar = null;
        switch (num) {
            case 1: // 本年到现在经过了一个季度，在加上前4个季度
                quarterCalendar = Calendar.getInstance();
                quarterCalendar.set(Calendar.YEAR,year);
                quarterCalendar.set(Calendar.MONTH, 3);
                quarterCalendar.set(Calendar.DATE, 1);
                quarterCalendar.add(Calendar.DATE, -1);
                str = DateTools.toString(quarterCalendar.getTime(), "yyyy-MM-dd");
                map.put("start",str.substring(0, str.length() - 5) + "01-01");
                map.put("end",str);
                break;
            case 2: // 本年到现在经过了二个季度，在加上前三个季度
                quarterCalendar = Calendar.getInstance();
                quarterCalendar.set(Calendar.YEAR,year);
                quarterCalendar.set(Calendar.MONTH, 6);
                quarterCalendar.set(Calendar.DATE, 1);
                quarterCalendar.add(Calendar.DATE, -1);
                str = DateTools.toString(quarterCalendar.getTime(), "yyyy-MM-dd");
                map.put("start",str.substring(0, str.length() - 5) + "04-01");
                map.put("end",str);
                break;
            case 3:// 本年到现在经过了三个季度，在加上前二个季度
                quarterCalendar = Calendar.getInstance();
                quarterCalendar.set(Calendar.YEAR,year);
                quarterCalendar.set(Calendar.MONTH, 9);
                quarterCalendar.set(Calendar.DATE, 1);
                quarterCalendar.add(Calendar.DATE, -1);
                str = DateTools.toString(quarterCalendar.getTime(), "yyyy-MM-dd");
                map.put("start",str.substring(0, str.length() - 5) + "07-01");
                map.put("end",str);
                break;
            case 4:// 本年到现在经过了四个季度，在加上前一个季度
                quarterCalendar = Calendar.getInstance();
                quarterCalendar.set(Calendar.YEAR,year);
                str = DateTools.toString(quarterCalendar.getTime(), "yyyy-MM-dd");
                map.put("start",str.substring(0, str.length() - 5) + "10-01");
                map.put("end",str.substring(0, str.length() - 5) + "12-31");
                break;
        }
        return map;
    }




    public static void main(String[] args) {
        try {
            // System.out.println(daysBetween("2020-06-01","2021-05-31"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            Date time = addMonths(new Date(),-12);
            Date ks = convert2Date("2020-01","yyyy-MM");
//
//            Date tz = convert2Date("2020-10-10","yyyy-MM-dd");
//            Date end1 = convert2Date("2020-12-31","yyyy-MM-dd");
            System.out.println(toString(time));
//            diffTimes
//            System.out.println(toString(addDays(ks,31)));
            System.out.println(sdf.format(addMonths(ks,23)));
//            System.out.println(sdf.format(getCurrYearFirst()));
//            System.out.println(sdf.format(getCurrYearLast()));
//            System.out.println(diffMonth2(ks,end));
//            System.out.println(sdf.format(addMonths(begin,1)));
//            System.out.println(diffTimes(ks,tz));
//            System.out.println(diffMonth2(begin,begin1));
//            System.out.println(toString(addMonths(begin,1)));
//            System.out.println(diffDay(end1,tz));
//            System.out.println(toString(addDays(begin,10)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 月差
     * @param before
     * @param after
     * @return
     */
    public static int diffMonth2(Date before, Date after){
        after = DateTools.addDays(after,1);

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(before);
        c2.setTime(after);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值
        int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if (month1 < month2 || month1 == month2 && day1 < day2)
            yearInterval--;
        // 获取月数差值
        int monthInterval = (month1 + 12) - month2;
        if (day1 < day2)
            monthInterval--;
        monthInterval %= 12;
        int monthsDiff = Math.abs(yearInterval * 12 + monthInterval);
        return monthsDiff;
    }


    /**
     * 返回日期上个月的开始和结束时间
     * @param date
     * @return
     */
    public static List<Date> jianDays2(Date date) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.clear();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.clear();

        int year = DateTools.getYear(date);
        int month = DateTools.getMonth(date);
        calendar1.set(year, month -2 , 1);

        int lastDay = calendar1.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar2.set(year, month - 2 , lastDay);

        return Arrays.asList(calendar1.getTime(), calendar2.getTime());
    }
    /**
     * 获取指定日期的下一个月的开始和结束时间
     * @param date
     * @return
     */
    public static List<Date> nextMonth(Date date) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.clear();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.clear();

        int year = DateTools.getYear(date);
        int month = DateTools.getMonth(date);
        calendar1.set(year, month , 1);

        int lastDay = calendar1.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar2.set(year, month , lastDay);

        return Arrays.asList(calendar1.getTime(), calendar2.getTime());
    }

    /**
     * 日期减一天
     * @param date
     * @return
     */
    public static Date reduceOneDay(Date date) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        calendar1.add(calendar1.DATE, -1);

        return calendar1.getTime();
    }




    public static boolean belongCalendar(Date time, Date from, Date to) {
        Calendar date = Calendar.getInstance();
        date.setTime(time);
        Calendar after = Calendar.getInstance();
        after.setTime(from);
        Calendar before = Calendar.getInstance();
        before.setTime(to);
        if (date.after(after) && date.before(before)) {
            return true;
        } else {
            return false;
        }
    }

    public static List<Map<String, Date>> splitDatesToMap(String start, String end) {
        List<Map<String,Date>> list = new ArrayList<>();
        Date startTime = DateTools.convertDateHMS(start);
        Date endTime = DateTools.convertDateHMS(end);
        int diffMonth = DateTools.diffMonth(startTime, endTime);
        int cha =  diffMonth + 1;
        for (int i = 0; i < cha; i++) {
            Map<String,Date> map = new HashMap<>();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startTime);
            calendar.set(Calendar.MONTH, DateTools.getMonth(startTime) -  1);
            calendar.set(Calendar.DAY_OF_MONTH,1);
            int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(startTime);
            calendar2.set(Calendar.MONTH, DateTools.getMonth(startTime) - 1);
            calendar2.set(Calendar.DAY_OF_MONTH,lastDay);
            Date startTemp = calendar.getTime();
            Date endTemp = calendar2.getTime();
            if(i == 0){
                startTemp = startTime;
            }else if(i == cha - 1){
                endTemp = endTime;
            }
            map.put("start",startTemp);
            map.put("end",endTemp);
            startTime = DateTools.addMonths(startTime,1);
            list.add(map);
        }
        return list;
    }

    public static boolean before(Date date1,Date date2) {

        return date1.getTime() <= date2.getTime();
    }

    public static boolean after(Date date1,Date date2) {

        return date1.getTime() >= date2.getTime();
    }
    /**
     *
     * @param nowTime   当前时间
     * @param startTime	开始时间
     * @param endTime   结束时间
     * @return
     * @author sunran   判断当前时间在时间区间内
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断两个时间是否是同一个月
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameMonth(Date date1,Date date2){
        if(getYear(date1) != getYear(date2)){
            return false;
        }

        if(getMonth(date1) != getMonth(date2)){
            return false;
        }
        return true;
    }

    /**
     * 获取某年某月第一天日期
     *
     * @return Date
     */
    public static Date getYearByMonthFirst(Date date){

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, getYear(date));
        calendar.set(Calendar.MONTH, getMonth(date)-1);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }


    /**
     * 获取某年某月最后一天日期
     *
     * @return Date
     */
    public static Date getYearByMonthLast(Date date){

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, getYear(date));
        calendar.set(Calendar.MONTH, getMonth(date));
        Date currYearFirst = calendar.getTime();
        currYearFirst = addDays(currYearFirst,-1);
        return currYearFirst;
    }



    /**
     * @Expanation: 计算月差
     * @Author 朱志鹏
     * @param before  开始时间
     * @param after   结束时间
     * @param x      付款周期
     * @Return double 返回小数   整数部分是月  小数部分是天数
     * @Date 2021/4/20 11:15
     */
    public static BigDecimal getMonthPercent(Date before, Date after, int x){

        Date date = DateTools.addMonths(before, x);
        // 需要的 月份
        Date date1 = DateTools.addDays(date, -1);
        if(date1.after(after))
        {// 不满一个周期 需要 拆出来几月 0 几天
            int a = 0;
            for (int i = 0; i <= x; i++) {
                Date date2 = DateTools.addMonths(before, i);
                date2 = DateTools.addDays(date2,-1);
                if(date2.before(after)) {
                    // 小于当前周期日期
                    if(date2.getYear() == after.getYear() && date2.getMonth() == after.getMonth()){
                        int day = diffDay(date2, after);
                        return new BigDecimal(i + "." + day).setScale(day <= 9 ? 1 : 2);
                    }
                    continue;
                }
                else if(date2.getTime() == after.getTime())// 相等
                    return new BigDecimal(i + "").setScale( i <= 9 ? 1 : 2);
                else { // 大于当前周期日期 说明 开始时间加上 i 个月后的结束时间比 周期结束时间大了 并且不再同一个月
                    int i1 = DateTools.diffDay(after, date2);
                    int daysByMonth = DateTools.getDaysByMonth(after);
                    return new BigDecimal(i-1+"."+(daysByMonth-i1)).setScale(daysByMonth-i1 <= 9 ? 1 : 2);
                }
            }
            return new BigDecimal("0.00").setScale(2);

        }else if(date1.getTime() == after.getTime()){
            // 相等 满月
            return new BigDecimal(x+"").setScale(2);
        }
        return new BigDecimal("0.00").setScale(2);
    }


    public static String getMaturityDay(Date payTime) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowTime = sdf.format(new Date());
        String payTimeStr = sdf.format(payTime);
        long diff = 0L;
            long time1 = sdf.parse(payTimeStr).getTime();
            long time2 = sdf.parse(nowTime).getTime();
            diff = (time1 - time2) / (1000 * 60 * 60 * 24);
            if (diff >= 0) {
                diff++;
            }
        String diffStr = diff == 0 ? "" : String.valueOf(diff);
        return diffStr;
    }

    /***
     * 根据 时间周期 拆分成自然月
     */
    // 根据账单周期 拆分自然月
    public static List<Times> splitTime(Date startTime, Date endTime){
        ArrayList<Times> times = new ArrayList<>();
        int startYear = getYear(startTime);
        int endYear = getYear(endTime);
        if(startYear == endYear ){
            createNaturalMonth(times,startTime,endTime);
        }
        else {
            // 年差
            int year = diffYear(startTime, endTime);
            Date date = null;
            for (int i = 0; i < year; i++) {
                date = addYears(startTime, i);
                Date yearLast = getYearLast(getYear(date));
                Date overDate = null ; // 过度时间
                if(i != 0){
                    String dateStr = DateTools.toString(getYearFirst(getYear(date)));
                    overDate = DateTools.convertDateHMS(dateStr);
                }else{
                    String dateStr = DateTools.toString(startTime);
                    overDate = DateTools.convertDateHMS(dateStr);
                }
                createNaturalMonth(times,overDate,yearLast);
            }
            Date lastYear = addYears(date, 1);
            Date secondDate = getYearFirst(getYear(lastYear));
            createNaturalMonth(times,secondDate,endTime);
        }
        if(CollectionUtils.isNotEmpty(times)) {
            Comparator<Times> year = Comparator.comparing(Times::getYear);
            Comparator<Times> month = Comparator.comparing(Times::getMonth);
        // 先以年升序排列，再按照月升序排列
            times.sort(year.thenComparing(month));
        }
        return times;
    }

    private static void createNaturalMonth(ArrayList<Times> times,Date startTime, Date endTime){
        int startYear = getYear(startTime);
        int endYear = getYear(endTime);
        int startMonth = getMonth(startTime);
        int endMonth = getMonth(endTime);
        if(startMonth == endMonth){
            int day = diffDay(startTime,endTime) + 1;
            Times time = new Times();
            time.setMonth(startMonth).setDay(day).setYear(startYear);
            times.add(time);
        }else {
            int month = endMonth - startMonth - 1;
            for (int i = 1; i <= month; i++) {
                Date date = addMonths(startTime, i);
                Times otherTime = new Times();
                otherTime.setYear(startYear)
                        .setMonth(getMonth(date))
                        .setDay(getDaysByMonth(date));
                times.add(otherTime);
            }
            int daysByMonth = getDaysByMonth(startTime);
            int day = getDay(startTime);
            Times first = new Times();
            first.setDay(daysByMonth - day + 1)
                    .setMonth(startMonth)
                    .setYear(startYear);
            times.add(first);

            int days = getDaysByMonth(endTime);
            int endDay = getDay(endTime);
            Times last = new Times();
            last.setDay(days - (days - endDay))
                    .setMonth(endMonth)
                    .setYear(startYear);
            times.add(last);
        }
    }

}
