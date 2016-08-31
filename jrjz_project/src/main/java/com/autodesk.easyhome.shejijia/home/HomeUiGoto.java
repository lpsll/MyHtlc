package com.autodesk.easyhome.shejijia.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.autodesk.easyhome.shejijia.MainActivity;
import com.autodesk.easyhome.shejijia.home.activity.AddAddressActivity;
import com.autodesk.easyhome.shejijia.home.activity.AppointmentActivity;
import com.autodesk.easyhome.shejijia.home.activity.ClassificationActivity;
import com.autodesk.easyhome.shejijia.home.activity.ModifyAddressActivity;
import com.autodesk.easyhome.shejijia.home.activity.OrderPaymentActivity;
import com.autodesk.easyhome.shejijia.home.activity.ProjectDetailsActivity;
import com.autodesk.easyhome.shejijia.home.activity.SelectAddressActivity;
import com.autodesk.easyhome.shejijia.home.activity.ServiceTimeActivity;
import com.autodesk.easyhome.shejijia.login.activity.LoginForCodeActivity;
import com.autodesk.easyhome.shejijia.login.activity.LoginForPwdActivity;
import com.autodesk.easyhome.shejijia.register.activity.ForgetPwdActivity;
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
     * @param bundle
     */

    public static void gotoApt(Context context, Bundle bundle){
        Intent intent = new Intent(context, AppointmentActivity.class);
        intent.putExtra("bundle",bundle);
        context.startActivity(intent);
    }

    /**
     * 跳转到订单支付页
     * @param context
     * @param bundle
     */

    public static void gotoOrder(Context context, Bundle bundle){
        Intent intent = new Intent(context, OrderPaymentActivity.class);
        intent.putExtra("bundle",bundle);
        context.startActivity(intent);
    }

    /**
     * 跳转到常用地址页
     * @param context
     */
    public static final int SELECT_REQUEST = 010;
    public static void gotoSelect(Activity act){
        Intent intent = new Intent(act, SelectAddressActivity.class);
        act.startActivityForResult(intent,SELECT_REQUEST);
    }

    /**
     * 跳转到服务时间页
     * @param act
     */

    public static void gotoSt(Activity act){
        Intent intent = new Intent(act, ServiceTimeActivity.class);
        act.startActivityForResult(intent,SELECT_REQUEST);
    }

    /**
     * 跳转到项目详情
     * @param context
     */

    public static void gotoProjectDetails(Context context){
        Intent intent = new Intent(context, ProjectDetailsActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到新增地址页
     * @param context
     */
    public static final int ADDRSS_REQUEST = 001;
    public static void gotoAddress(Activity act){
        Intent intent = new Intent(act, AddAddressActivity.class);
        act.startActivityForResult(intent,ADDRSS_REQUEST);
    }

    /**
     * 跳转到修改地址页
     * @param context
     * @param b
     */
    public static final int MODIFY_REQUEST = 002;
    public static void gotoModify(Activity act, Bundle b){
        Intent intent = new Intent(act, ModifyAddressActivity.class);
        intent.putExtra("bundle",b);
        act.startActivityForResult(intent,MODIFY_REQUEST);
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

    /**
     * 跳转到登录页(密码登录)
     * @param
     */
    public static final int LOFIN_REQUEST = 0x1100;
    public static void gotoLoginForPwd(Activity act) {

        Intent intent = new Intent(act, LoginForPwdActivity.class);
        act.startActivityForResult(intent, LOFIN_REQUEST);
    }

    /**
     * 跳转到登录页(验证码登录)
     * @param
     */
    public static void gotoLoginForCode(Context context) {
        Intent intent = new Intent(context, LoginForCodeActivity.class);
        context.startActivity(intent);
    }


    /**
     * 跳转到登录页(验证码登录)
     * @param
     */
    public static void gotoForgetPwd(Context context) {
        Intent intent = new Intent(context, ForgetPwdActivity.class);
        context.startActivity(intent);
    }
}
