package com.autodesk.easyhome.shejijia.home;

import android.content.Context;
import android.content.Intent;

import com.autodesk.easyhome.shejijia.MainActivity;
import com.autodesk.easyhome.shejijia.home.activity.AddAddressActivity;
import com.autodesk.easyhome.shejijia.home.activity.AppointmentActivity;
import com.autodesk.easyhome.shejijia.home.activity.ClassificationActivity;
import com.autodesk.easyhome.shejijia.home.activity.OrderPaymentActivity;
import com.autodesk.easyhome.shejijia.home.activity.SelectAddressActivity;
import com.autodesk.easyhome.shejijia.home.activity.ServiceTimeActivity;
import com.autodesk.easyhome.shejijia.register.activity.RegisterActivity;

/**
 * Created by John_Libo on 2016/8/16.
 */
public class HomeUiGoto {

    /**
     * 跳转到首页
     * @param context
     */

    public static void gotoMain(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


    /**
     * 跳转到预约页
     * @param context
     */

    public static void gotoApt(Context context){
        Intent intent = new Intent(context, AppointmentActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到订单支付页
     * @param context
     */

    public static void gotoOrder(Context context){
        Intent intent = new Intent(context, OrderPaymentActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到常用地址页
     * @param context
     */

    public static void gotoSelect(Context context){
        Intent intent = new Intent(context, SelectAddressActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到服务时间页
     * @param context
     */

    public static void gotoSt(Context context){
        Intent intent = new Intent(context, ServiceTimeActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到新增地址页
     * @param context
     */

    public static void gotoAddress(Context context){
        Intent intent = new Intent(context, AddAddressActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到分类页
     * @param context
     */

    public static void gotoCf(Context context){
        Intent intent = new Intent(context, ClassificationActivity.class);
        context.startActivity(intent);
    }

    /**
     *
     * @param context
     * 跳转到注册页面
     */
    public static void gotoRegister(Context context){
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }
}
