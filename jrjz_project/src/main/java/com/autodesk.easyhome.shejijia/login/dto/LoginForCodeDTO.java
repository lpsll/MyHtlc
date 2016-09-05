package com.autodesk.easyhome.shejijia.login.dto;

import com.autodesk.easyhome.shejijia.common.utils.SecurityUtils;

/**
 * Created by Administrator on 2016/8/24.
 * 短信验证码认证授权请求

 uid：用户ID，默认为手机号码

 smsVerifyCode：短信验证码字符串

 timestamp：当前时间戳

 random：随机数

 sign：签名【生成规则 uid+smsVerifyCode+timestamp+random 后md5加密串】

 usertype:用户类型 1-普通用户 , 默认为1
 *
 *
 */
public class LoginForCodeDTO  {

    public String uid;
    private String smsVerifyCode;
    public String timestamp;
    public String random;
    public String sign;
    private int  usertype;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = SecurityUtils.MD5(sign);
    }

    public String getSmsVerifyCode() {
        return smsVerifyCode;
    }

    public void setSmsVerifyCode(String smsVerifyCode) {
        this.smsVerifyCode = smsVerifyCode;
    }


    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
}
