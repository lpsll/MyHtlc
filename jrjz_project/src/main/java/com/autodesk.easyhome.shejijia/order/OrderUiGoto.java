package com.autodesk.easyhome.shejijia.order;

import android.content.Context;
import android.content.Intent;

import com.autodesk.easyhome.shejijia.order.activity.EvaluateActivity;
import com.autodesk.easyhome.shejijia.order.activity.OrderNewPaymentActivity;

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


}
