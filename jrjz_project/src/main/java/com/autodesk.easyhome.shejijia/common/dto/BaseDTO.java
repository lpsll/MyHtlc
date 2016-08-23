package com.autodesk.easyhome.shejijia.common.dto;

import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.SecurityUtils;

import java.io.Serializable;

/**
 * DTO的基类
 */

public class BaseDTO implements Serializable {
    public String uid;
    public String timestamp;
    public String random;
    public String sign;

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
        LogUtils.e("setSign--",""+SecurityUtils.md5(sign));
        this.sign = SecurityUtils.md5(sign);
    }


}
