package com.autodesk.easyhome.shejijia.register.dto;

import java.io.Serializable;

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
public class RegisterDTO implements Serializable {
    public String uid;
    public String password;
    public String timestamp;
    public String random;
    public String smsverifycode;
    public String sign;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "RegisterDTO{" +
                "uid='" + uid + '\'' +
                ", password='" + password + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", random='" + random + '\'' +
                ", smsverifycode='" + smsverifycode + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
