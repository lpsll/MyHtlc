package com.autodesk.easyhome.shejijia.login.dto;

import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.SecurityUtils;

/**
 * Created by Administrator on 2016/8/24.
 * 认证授权请求
 * uid：用户ID，默认为手机号码
 * password：用户密码 md5加密32位字符串
 * timestamp：当前时间戳
 * random：随机数
 * sign：签名【生成规则 uid+password+timestamp+random 后md5加密串】
 * usertype:用户类型 1-普通用户 , 默认为1
 */
public class LoginDTO  {

    public String password;
    public String timestamp;
    public String random;
    public String sign;
    public int usertype;

    public String uid;

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



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        LogUtils.e("pwd--",""+SecurityUtils.md5(password));
        this.password = SecurityUtils.md5(password);
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
}
