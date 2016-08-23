package com.autodesk.easyhome.shejijia.register.bean;

/**
 * Created by Administrator on 2016/8/23.
 */
public class SmsVerifyCodeBean {

    public String uid;
    public Long timestamp;
    public int random;
    public String sign;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public int getRandom() {
        return random;
    }

    public void setRandom(int random) {
        this.random = random;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "SmsVerifyCodeBean{" +
                "uid='" + uid + '\'' +
                ", timestamp=" + timestamp +
                ", random=" + random +
                ", sign='" + sign + '\'' +
                '}';
    }
}
