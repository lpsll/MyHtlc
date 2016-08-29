package com.autodesk.easyhome.shejijia.register.dto;

import com.autodesk.easyhome.shejijia.common.utils.SecurityUtils;

/**
 * Created by Administrator on 2016/8/24.
 * 忘记密码接口
 * <p/>
 * uid:用户ID，默认为手机号码
 * <p/>
 * timestamp:当前时间戳
 * <p/>
 * random:随机数
 * <p/>
 * smsverifycode:短信验证码
 * <p/>
 * password:新密码，MD5加密
 * <p/>
 * sign:签名【生成规则 uid+password(MD5)+timestamp+random 后md5加密串】
 */
public class ForgetPwdDTO {

    public String uid;
    public String timestamp;
    public String random;
    public String sign;
    private String smsverifycode;
    private String password;


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
        this.sign = SecurityUtils.md5(sign);
    }


    public String getSmsverifycode() {
        return smsverifycode;
    }

    public void setSmsverifycode(String smsverifycode) {
        this.smsverifycode = smsverifycode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = SecurityUtils.md5(password);
    }
}
