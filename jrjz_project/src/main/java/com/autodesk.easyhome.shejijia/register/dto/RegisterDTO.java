package com.autodesk.easyhome.shejijia.register.dto;

import com.autodesk.easyhome.shejijia.common.utils.SecurityUtils;

/**
 * Created by Administrator on 2016/8/24.
 * 用户注册接口

 uid:用户ID，默认为手机号码

 password:用户密码，MD5加密

 timestamp:当前时间戳

 random:随机数

 smsverifycode:短信验证码

 sign:签名
 */
public class RegisterDTO  {


    public String uid;
    public String password;
    public String timestamp;
    public String random;
    public String smsverifycode;
    public String sign;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = SecurityUtils.MD5(password);
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

    public String getSmsverifycode() {
        return smsverifycode;
    }

    public void setSmsverifycode(String smsverifycode) {
        this.smsverifycode = smsverifycode;
    }

    public String getSign() {
        return sign;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSign(String sign) {
        this.sign = SecurityUtils.MD5(sign);
    }
}
