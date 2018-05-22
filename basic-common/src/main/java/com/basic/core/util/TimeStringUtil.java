package com.basic.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: wangzw
 * @Date: 2017/10/16 9:43
 * @Description: 客户端显示时间
 * 如果与当前时间差别小于一天，则自动用**秒(分，小时)前，如果大于一天则用format规定的格式显示
 * @Version: 1.0
 */
public class TimeStringUtil {
    //格式化数据
    public final static String FORMAT_MM_DD_HH_MM = "MM-dd HH:mm";

    public static String showWebTime(Date updateTime) {
        String returnTimeVal;
        long nowTimeLong = System.currentTimeMillis();
        long updateTimeLong = updateTime.getTime();
        long result = Math.abs(nowTimeLong - updateTimeLong);
        if(result < 60000){//一分钟内
            long seconds = result / 1000;
            returnTimeVal = seconds == 0 ? "刚刚" : (seconds+"秒前");
            return returnTimeVal;
        }else if (result >= 60000 && result < 3600000){//一小时内
            long seconds = result / 60000;
            returnTimeVal = seconds + "分钟前";
            return returnTimeVal;
        }else if (result >= 3600000 && result < 86400000){//一天内
            long seconds = result / 3600000;
            returnTimeVal = seconds + "小时前";
            return returnTimeVal;
        }else{//日期格式
            SimpleDateFormat df = new SimpleDateFormat(FORMAT_MM_DD_HH_MM);
            returnTimeVal = df.format(updateTime).toString();
            return returnTimeVal;
        }
    }

}
