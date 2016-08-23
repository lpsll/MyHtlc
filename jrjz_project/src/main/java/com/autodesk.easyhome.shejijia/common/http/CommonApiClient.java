package com.autodesk.easyhome.shejijia.common.http;

import android.app.Activity;

import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;
import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

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
            dto, CallBack<BaseEntity> callback) {
        AsyncCallBack<BaseEntity> asyncCallBack = new AsyncCallBack<>(
                act, callback, BaseEntity.class);
        post(getAbsoluteUrl("user/getSmsVerifyCode"), dto,
                asyncCallBack);
    }



}
