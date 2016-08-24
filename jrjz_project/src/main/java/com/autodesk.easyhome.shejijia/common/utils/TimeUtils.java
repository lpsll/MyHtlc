package com.autodesk.easyhome.shejijia.common.utils;

import java.util.Random;

/**
 * Created by John_Libo on 2016/8/23.
 */
public class TimeUtils {

    public static String getSignTime(){
        long t=System.currentTimeMillis();
        String st=(t+"").substring(0,11).trim();
        return st;
    }

    /**
     * timeStamp时间戳
     */
    public static String genTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /**
     * nonceStr随机数
     */
    public static String genNonceStr() {
        Random random = new Random();
        return String.valueOf(random.nextInt(10000));
    }

}
