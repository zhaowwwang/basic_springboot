package com.basic.core.util;

/**
 * @Author: wangzw
 * @Date: 2017/10/16 9:58
 * @Description: 根据经纬度计算距离
 * @Version: 1.0
 */
public class LocationUtil {

    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * @Author: wangzw
     * @Description: 通过经纬度获取距离(单位：千米)
     * @param latitude1 数据库纬度
     * @param longitude1 数据库经度
     * @param latitude2 当前纬度
     * @param longitude2 当前经度
     * @Version: 1.0
     * @Date: 2017/10/16 9:59
     */
    public static double getDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        double radLat1 = rad(latitude1);
        double radLat2 = rad(latitude2);
        double latDiffer = radLat1 - radLat2;
        double lngDiffer = rad(longitude1) - rad(longitude2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(latDiffer / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(lngDiffer / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        return s;
    }

    public static void main(String[] args) {
        //山东济南
        double latitude1=36.675265;
        double longitude1=116.898031;
        //北京和泰大厦
        double latitude2=40.004422;
        double longitude2=116.440857;

        double distance = getDistance(latitude1, longitude1, latitude2, longitude2);
        System.out.println(distance);
    }
}
