package com.basic.core.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: wangzw
 * @Description: 日期格式工具
 * @Version: 1.0
 * @Date: 2017/5/9 16:46
 */
public class DateUtils {

    public final static String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public final static String FORMAT_YYYYMMDD = "yyyyMMdd";
    public final static String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public final static String FORMAT_YYYYMMDDHHMMSS="yyyyMMddHHmmss";
    public final static String FORMAT_YYYYMMDD_STR ="yyyy年MM月dd日";

    private static SimpleDateFormat simpleDateFormat = null;
    /**
     * @Author: wangzw
     * @Description: 获取昨天日期
     * @Version: 1.0
     * @Date: 2017/2/17 11:38
     */
    public static Date getYesterdayDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * @Author: wangzw
     * @Description: 获取昨天日期
     * @Version: 1.0
     * @Date: 2017/2/17 11:39
     */
    public static String getYesterdayStr() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return new SimpleDateFormat(DateUtils.FORMAT_YYYY_MM_DD).format(cal.getTime());
    }

    /**
     * @Author: wangzw
     * @Description: 获取明天日期
     * @Version: 1.0
     * @Date: 2017/2/17 11:41
     */
    public static String getTomorrowStr() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        return new SimpleDateFormat(DateUtils.FORMAT_YYYY_MM_DD).format(cal.getTime());
    }

    public static String getTomorrowYYYYMMDD() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        return new SimpleDateFormat(DateUtils.FORMAT_YYYYMMDD).format(cal.getTime());
    }


    /**
     * @Author: wangzw
     * @Description: 获取系统当前时间
     * @Version: 1.0
     * @Date: 2017/2/17 11:41
     */
    public static String getSysCurDateTime() {
        simpleDateFormat = new SimpleDateFormat(DateUtils.FORMAT_YYYY_MM_DD_HH_MM_SS);
        return simpleDateFormat.format(new Date());
    }

    /**
     * @Author: wangzw
     * @Description: 格式化时间
     * @Version: 1.0
     * @Date: 2017/2/17 11:42
     */
    public static String formatDateToStr(String format, Date date) {
        if (date == null) {
            return "";
        }
        simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * @Author: wangzw
     * @Description: 获取系统当前时间数字形式
     * @Version: 1.0
     * @Date: 2017/2/17 11:42
     */
    public static String getNumberDateTime() {
        simpleDateFormat = new SimpleDateFormat(DateUtils.FORMAT_YYYYMMDDHHMMSS);
        return simpleDateFormat.format(new Date());
    }

    /**
     * @Author: wangzw
     * @Description: 日期string转Date
     * @Version: 1.0
     * @Date: 2017/2/20 17:16
     */
    public static Date getDateFromStr(String time){
        try{
            if(time.length()>10){
                simpleDateFormat = new SimpleDateFormat(DateUtils.FORMAT_YYYY_MM_DD_HH_MM_SS);
            }else{
                simpleDateFormat = new SimpleDateFormat(DateUtils.FORMAT_YYYY_MM_DD);
            }
            return simpleDateFormat.parse(time);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: wangzw
     * @Description: 获取系统当前日期,YYYY_MM_DD格式
     * @Version: 1.0
     * @Date: 2017/2/17 11:43
     */
    public static String getSysCurFmtDate() {
        simpleDateFormat = new SimpleDateFormat(DateUtils.FORMAT_YYYY_MM_DD);
        return simpleDateFormat.format(new Date());
    }

    /**
     * @Author: wangzw
     * @Description: 获取YYYYMMDD格式
     * @Version: 1.0
     * @Date: 2017/2/17 11:55
     */
    public static String getDateFormatS(Date date) {
        simpleDateFormat = new SimpleDateFormat(DateUtils.FORMAT_YYYYMMDD);
        return simpleDateFormat.format(date);
    }

    /**
     * @Author: wangzw
     * @Description: 获取YYYY-MM-DD HH:mm:ss格式
     * @Version: 1.0
     * @Date: 2017/2/17 11:55
     */
    public static String getDateTime(Date date) {
        simpleDateFormat = new SimpleDateFormat(DateUtils.FORMAT_YYYY_MM_DD_HH_MM_SS);
        return simpleDateFormat.format(date);
    }

    /**
     * @Author: wangzw
     * @Description: 获取YYYY年MM月DD日格式
     * @Version: 1.0
     * @Date: 2017/2/17 11:55
     */
    public static String getFmtChineseDate(Date date) {
        simpleDateFormat = new SimpleDateFormat(DateUtils.FORMAT_YYYYMMDD_STR);
        return simpleDateFormat.format(date);
    }

    /**
     * @Author: wangzw
     * @Description: 获取YYYY-MM-DD格式
     * @Version: 1.0
     * @Date: 2017/2/17 11:47
     */
    public static String getFmtDate(Date date) {
        simpleDateFormat = new SimpleDateFormat(DateUtils.FORMAT_YYYY_MM_DD);
        return simpleDateFormat.format(date);
    }

    /**
     * @Author: wangzw
     * @Description: 获得两个日期相差的天数
     * @Version: 1.0
     * @Date: 2017/2/17 11:56
     */
    public static int daysBetween(Date early, Date late) {
        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        // 设置时间为0时
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        caled.set(Calendar.HOUR_OF_DAY, 0);
        caled.set(Calendar.MINUTE, 0);
        caled.set(Calendar.SECOND, 0);
        // 得到两个日期相差的天数
        int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;
        return days;
    }

    /**
     * @Author: wangzw
     * @Description: 日期加减几个月
     * @Version: 1.0
     * @Date: 2017/2/17 11:56
     */
    public static Date addMonths(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    /**
     * @Author: wangzw
     * @Description: 日期加减几天
     * @Version: 1.0
     * @Date: 2017/2/17 13:11
     */
    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * @Author: wangzw
     * @Description: 得到两个日期之间的所有日期，逗号隔开
     * @Version: 1.0
     * @Date: 2017/2/21 10:29
     */
    public static String getStrBetweenDates(String startTime,String endTime){
        StringBuilder stringBuilder = new StringBuilder();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(DateUtils.getDateFromStr(startTime));
        tempStart.add(Calendar.DAY_OF_YEAR, 1);
        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(DateUtils.getDateFromStr(endTime));
        stringBuilder.append(getDateFormatS(getDateFromStr(startTime))).append(",");
        while (tempStart.before(tempEnd)) {
            stringBuilder.append(DateUtils.getDateFormatS(tempStart.getTime()));
            stringBuilder.append(",");
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        stringBuilder.append(getDateFormatS(getDateFromStr(endTime)));
        return stringBuilder.toString();
    }

    /**
     * @Author: wangzw
     * @Description: 得到两个日期之间的所有日期
     * @Version: 1.0
     * @Date: 2017/2/21 10:29
     */
    public static List<String> getListBetweenDates(String startTime, String endTime){
        List<String> dateList = new ArrayList<String>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(DateUtils.getDateFromStr(startTime));
        tempStart.add(Calendar.DAY_OF_YEAR, 1);
        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(DateUtils.getDateFromStr(endTime));
        dateList.add(getDateFormatS(getDateFromStr(startTime)));
        while (tempStart.before(tempEnd)) {
            dateList.add(DateUtils.getDateFormatS(tempStart.getTime()));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        dateList.add(getDateFormatS(getDateFromStr(endTime)));
        return dateList;
    }

    /**
     * @Author: wangzw
     * @Description: 得到下月的第一天
     * @Version: 1.0
     * @Date: 2017/6/22 17:23
     */
    public static String getFirstDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date date = calendar.getTime();
        return getFmtDate(date);
    }

    /**
     * @Author: wangzw
     * @Description: 得到下月的最后一天
     * @Version: 1.0
     * @Date: 2017/6/22 17:23
     */
    public static String getLastDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date date = calendar.getTime();
        return getFmtDate(date);
    }


    public static void main(String[] args){
        System.out.println(DateUtils.getStrBetweenDates("2017-02-01","2017-02-03"));

        int daysBetween = DateUtils.daysBetween(DateUtils.getDateFromStr("2018-01-08 09:08:32"),new Date());
        System.out.println(daysBetween);


        System.out.println(DateUtils.getDateTime(DateUtils.addDays(new Date(),-2)));
    }
}
