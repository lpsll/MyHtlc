package com.autodesk.easyhome.shejijia.register.dto;

import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;

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
public class RegisterDTO extends BaseDTO {
    public String password;
    public String smsverifycode;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmsverifycode() {
        return smsverifycode;
    }

    public void setSmsverifycode(String smsverifycode) {
        this.smsverifycode = smsverifycode;
    }



}
