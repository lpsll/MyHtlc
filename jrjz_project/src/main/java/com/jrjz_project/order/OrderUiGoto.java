package com.jrjz_project.order;

import android.content.Context;
import android.content.Intent;

import com.jrjz_project.home.activity.ClassificationActivity;
import com.jrjz_project.home.activity.OrderPaymentActivity;
import com.jrjz_project.order.activity.CouponActivity;
import com.jrjz_project.order.activity.EvaluateActivity;
import com.jrjz_project.order.activity.OrderNewPaymentActivity;

/**
 * Created by John_Libo on 2016/8/17.
 */
public class OrderUiGoto {
    /**
     * 跳转到订单的订单支付页
     * @param context
     */

    public static void gotoOrderNew(Context context){
        Intent intent = new Intent(context, OrderNewPaymentActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到评价页
     * @param context
     */

    public static void gotoEvaluate(Context context){
        Intent intent = new Intent(context, EvaluateActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到优惠券页
     * @param context
     */

    public static void gotoCoupon(Context context){
        Intent intent = new Intent(context, CouponActivity.class);
        context.startActivity(intent);
    }
}
