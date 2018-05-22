package com.basic.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @Author: wangzw
 * @Date: 2017/10/7 14:53
 * @Description: 编号工具类
 * @Version: 1.0
 */
public class SeriesNumUtil {

    private static String[] lowercase = {"a","b","c","d","e","f","g","h","i","j","k",
            "l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

    private static String[] capital = {"A","B","C","D","E","F","G","H","I","J","K",
            "L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    private static String[] number = {"1","2","3","4","5","6","7","8","9","0"};

    private static String[] sign = {"~","!","@","#","$","%","^","&","*","(",")","_","+","`","-","=",
            "{","}","|",":","<",">","?","[","]",";","'",",",".","/"};

    /**
     * @author: wangzw
     * @description: 随机字符串
     * @version: 1.0
     * @date: 2017/11/28 16:27
     */
    public static String getRandomString(int type,int length) {
        if(type<0 || type>5){
            return null;
        }
        List<String> dataList = new ArrayList<>();
        if(type>=1){
            dataList.addAll(Arrays.asList(lowercase));
        }
        if(type>=2){
            dataList.addAll(Arrays.asList(capital));
        }
        if(type>=3){
            dataList.addAll(Arrays.asList(number));
        }
        if(type>=4){
            dataList.addAll(Arrays.asList(sign));
        }
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i=0;i<length;i++){
            stringBuffer.append(dataList.get(random.nextInt(dataList.size())));
        }
        return stringBuffer.toString();
    }

    /**
     * @Author: wangzw
     * @Description: 不够位数的在前面补0，保留num的长度位数字
     * @Version: 1.0
     * @Date: 2017/10/7 14:53
     */
    public static String getSeriesNum(int currentNum,int length){
        return String.format("%0" + length + "d", currentNum + 1);
    }

    /**
     * @Author: wangzw
     * @Description: 随机32位
     * @Version: 1.0
     * @Date: 2017/10/18 10:28
     */
    public static String get32UUID(){
        return UUID.randomUUID().toString();
    }


    /**
     * @Author: wangzw
     * @Description: md5加密
     * @Version: 1.0
     * @Date: 2017/10/18 11:03
     */
    public static String getMd5AddNumber(String o,String code){
        try {
            o = o+code;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(o.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0){
                    i += 256;
                }
                if (i < 16){
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();

        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * @Author: wangzw
     * @Description: 生成验证码随机6位
     * @Version: 1.0
     * @Date: 2017/10/18 10:55
     */
    public static String getSmsCode(){
        return String.valueOf((int)((Math.random()*9+1)*100000));
    }

    public static void main(String[] args) {
        System.out.println(getMd5AddNumber("juzifenqi","wang"));
    }
}
