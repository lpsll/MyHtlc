package com.autodesk.easyhome.shejijia.common.http;

import android.app.Activity;

import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;
import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;
import com.autodesk.easyhome.shejijia.home.entity.CarouselResult;
import com.autodesk.easyhome.shejijia.home.entity.ClassificationResult;
import com.autodesk.easyhome.shejijia.home.entity.FullServiceResult;
import com.autodesk.easyhome.shejijia.home.entity.ServiceResult;
import com.autodesk.easyhome.shejijia.login.dto.LoginDTO;
import com.autodesk.easyhome.shejijia.login.dto.LoginForCodeDTO;
import com.autodesk.easyhome.shejijia.login.entity.LoginEntity;
import com.autodesk.easyhome.shejijia.mine.dto.ChangePhoneDTO;
import com.autodesk.easyhome.shejijia.mine.dto.FeedBackDTO;
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
        post(getAbsoluteUrl("/user/getSmsVerifyCode"), dto,
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
        post(getAbsoluteUrl("/user/userRegister"), dto,
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
        post(getAbsoluteUrl("/user/getAccessToken"), dto,
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
        post(getAbsoluteUrl("/user/smsVerifyCodeAuth"), dto,
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
        post(getAbsoluteUrl("/user/forgotPassword"), dto,
                asyncCallBack);
    }

    /**
     * 换绑手机
     * @param act
     * @param dto
     * @param callback
     */
    public static void changePhone(Activity act, ChangePhoneDTO
            dto, CallBack<BaseEntity> callback) {
        AsyncCallBack<BaseEntity> asyncCallBack = new AsyncCallBack<>(
                act, callback, BaseEntity.class);
        post(getAbsoluteUrl("/user/changeMobile"), dto,
                asyncCallBack);
    }

    /**
     * 轮播图
     * @param act
     * @param dto
     * @param callback
     */
    public static void carousel(Activity act, BaseDTO
            dto, CallBack<CarouselResult> callback) {
        AsyncCallBack<CarouselResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, CarouselResult.class);
        get(getAbsoluteUrl("/banner/bannerlist"), dto,
                asyncCallBack);
    }

    /**
     * 分类
     * @param act
     * @param dto
     * @param callback
     */
    public static void classification(Activity act, BaseDTO
            dto, CallBack<ClassificationResult> callback) {
        AsyncCallBack<ClassificationResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, ClassificationResult.class);
        get(getAbsoluteUrl("/service/allServicesByClass"), dto,
                asyncCallBack);
    }


    /**
     * 全部服务类别
     * @param act
     * @param dto
     * @param callback
     */
    public static void fullService(Activity act, BaseDTO
            dto, CallBack<FullServiceResult> callback) {
        AsyncCallBack<FullServiceResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, FullServiceResult.class);
        get(getAbsoluteUrl("/service/allServiceClasses"), dto,
                asyncCallBack);
    }

    /**
     * 制定服务类
     * @param act
     * @param dto
     * @param callback
     */
    public static void service(Activity act, BaseDTO
            dto, CallBack<ServiceResult> callback) {
        AsyncCallBack<ServiceResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, ServiceResult.class);
        get(getAbsoluteUrl("/service/indexServices"), dto,
                asyncCallBack);
    }

    /**
     * 意见反馈
     * @param act
     * @param dto
     * @param callback
     */
    public static void feedBack(Activity act, FeedBackDTO
            dto, CallBack<BaseEntity> callback) {
        AsyncCallBack<BaseEntity> asyncCallBack = new AsyncCallBack<>(
                act, callback, BaseEntity.class);
        post(getAbsoluteUrl("/feedback/add"), dto,
                asyncCallBack);
    }



}
