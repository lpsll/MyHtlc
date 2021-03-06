package com.autodesk.easyhome.shejijia.order;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.autodesk.easyhome.shejijia.order.activity.EvaluateActivity;
import com.autodesk.easyhome.shejijia.order.activity.IntegralActivity;
import com.autodesk.easyhome.shejijia.order.activity.OrderNewPaymentActivity;
import com.autodesk.easyhome.shejijia.order.activity.ServiceCouponActivity;

/**
 * Created by John_Libo on 2016/8/17.
 */
public class OrderUiGoto {
    /**
     * 跳转到订单的订单支付页
     * @param context
     * @param bundle
     */

    public static void gotoOrderNew(Context context, Bundle bundle){
        Intent intent = new Intent(context, OrderNewPaymentActivity.class);
        intent.putExtra("bundle",bundle);
        context.startActivity(intent);
    }

    /**
     * 跳转到评价页
     * @param context
     * @param bundle
     */

    public static void gotoEvaluate(Context context, Bundle bundle){
        Intent intent = new Intent(context, EvaluateActivity.class);
        intent.putExtra("bundle",bundle);
        context.startActivity(intent);
    }
    public static final int EVALUATE_REQUEST = 01100;
    public static void gotoEvaluateStrat(Activity context, Bundle bundle){
        Intent intent = new Intent(context, EvaluateActivity.class);
        intent.putExtra("bundle",bundle);
        context.startActivityForResult(intent,EVALUATE_REQUEST);
    }

    /**
     * 跳转到积分页
     * @param context
     */
    public static final int INTEGAL_REQUEST = 0110;
    public static void gotoIntegral(Activity act, Bundle bundle){
        Intent intent = new Intent(act, IntegralActivity.class);
        intent.putExtra("bundle",bundle);
        act.startActivityForResult(intent,INTEGAL_REQUEST);
    }

    /**
     * 跳转到订单之优惠券页
     * @param context
     */
    public static final int SC_REQUEST = 0112;
    public static void gotoServiceCoupon(Activity act, Bundle bundle){
        Intent intent = new Intent(act, ServiceCouponActivity.class);
        intent.putExtra("bundle",bundle);
        act.startActivityForResult(intent,SC_REQUEST);
    }


}
