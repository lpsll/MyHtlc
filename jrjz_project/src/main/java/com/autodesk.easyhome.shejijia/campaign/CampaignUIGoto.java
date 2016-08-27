package com.autodesk.easyhome.shejijia.campaign;

import android.app.Activity;
import android.content.Intent;

import com.autodesk.easyhome.shejijia.login.activity.LoginForPwdActivity;

/**
 * Created by Administrator on 2016/8/27.
 */
public class CampaignUIGoto {


    /**
     * 跳转到登录页(密码登录)
     * @param
     */
    public static final int LOFIN_REQUEST = 0x1100;
    public static void gotoLoginForPwd(Activity act) {

        Intent intent = new Intent(act, LoginForPwdActivity.class);
        act.startActivityForResult(intent, LOFIN_REQUEST);
    }


}
