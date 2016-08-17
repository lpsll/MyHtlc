package com.jrjz_project.home;

import android.content.Context;
import android.content.Intent;

import com.jrjz_project.home.activity.AddAddressActivity;
import com.jrjz_project.home.activity.AppointmentActivity;
import com.jrjz_project.home.activity.ClassificationActivity;
import com.jrjz_project.home.activity.OrderPaymentActivity;
import com.jrjz_project.home.activity.SelectAddressActivity;
import com.jrjz_project.home.activity.ServiceTimeActivity;

/**
 * Created by John_Libo on 2016/8/16.
 */
public class HomeUiGoto {


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
}
