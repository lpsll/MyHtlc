package com.autodesk.easyhome.shejijia.common.http;

import android.app.Activity;

import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;
import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;
import com.autodesk.easyhome.shejijia.login.dto.LoginDTO;
import com.autodesk.easyhome.shejijia.login.dto.LoginForCodeDTO;
import com.autodesk.easyhome.shejijia.login.entity.LoginEntity;
import com.autodesk.easyhome.shejijia.register.dto.ForgetPwdDTO;
import com.autodesk.easyhome.shejijia.register.dto.RegisterDTO;
import com.autodesk.easyhome.shejijia.register.entity.SmsVerifyEntity;

/**
 * Created by John_Libo on 2016/8/15.
 */
public class CommonApiClient extends BaseApiClient{

    /**
     * 获取短信验证码
     * @param act
     * @param dto
     * @param callback
     */
    public static void verifyCode(Activity act, BaseDTO
            dto, CallBack<SmsVerifyEntity> callback) {
        AsyncCallBack<SmsVerifyEntity> asyncCallBack = new AsyncCallBack<>(
                act, callback, SmsVerifyEntity.class);
        post(getAbsoluteUrl("user/getSmsVerifyCode"), dto,
                asyncCallBack);
    }

    /**
     * 注册
     * @param act
     * @param dto
     * @param callback
     */
    public static void register(Activity act, RegisterDTO
            dto, CallBack<BaseEntity> callback) {
        AsyncCallBack<BaseEntity> asyncCallBack = new AsyncCallBack<>(
                act, callback, BaseEntity.class);
        post(getAbsoluteUrl("user/userRegister"), dto,
                asyncCallBack);
    }

    /**
     * 用户名密码登录
     * @param act
     * @param dto
     * @param callback
     */
    public static void login(Activity act, LoginDTO
            dto, CallBack<LoginEntity> callback) {
        AsyncCallBack<LoginEntity> asyncCallBack = new AsyncCallBack<>(
                act, callback, LoginEntity.class);
        post(getAbsoluteUrl("user/getAccessToken"), dto,
                asyncCallBack);
    }

    /**
     * 验证码登录
     * @param act
     * @param dto
     * @param callback
     */
    public static void loginForCode(Activity act, LoginForCodeDTO
            dto, CallBack<LoginEntity> callback) {
        AsyncCallBack<LoginEntity> asyncCallBack = new AsyncCallBack<>(
                act, callback, LoginEntity.class);
        post(getAbsoluteUrl("user/smsVerifyCodeAuth"), dto,
                asyncCallBack);
    }

    /**
     * 忘记密码
     * @param act
     * @param dto
     * @param callback
     */
    public static void forgetPwd(Activity act, ForgetPwdDTO
            dto, CallBack<BaseEntity> callback) {
        AsyncCallBack<BaseEntity> asyncCallBack = new AsyncCallBack<>(
                act, callback, BaseEntity.class);
        post(getAbsoluteUrl("user/forgotPassword"), dto,
                asyncCallBack);
    }


}
