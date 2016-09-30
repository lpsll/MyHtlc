package com.autodesk.easyhome.shejijia.common.utils;

import android.text.format.Time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by John_Libo on 2016/8/23.
 */
public class TimeUtils {
    /**
     * 获取系统时间
     */
    public static long getSignTime(){
        long t =System.currentTimeMillis();
        return t;


//        SimpleDateFormat  formatter  =  new SimpleDateFormat("yyyyMMddHHmmss");
//        Date curDate = new Date(System.currentTimeMillis());
//        String  str  =  formatter.format(curDate);
//        LogUtils.e("time---",""+System.currentTimeMillis());
//        return str;
    }
    /**
     * 获取系统时间
     */
    public static long getTime(){

        long t =System.currentTimeMillis();
        return t;
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
