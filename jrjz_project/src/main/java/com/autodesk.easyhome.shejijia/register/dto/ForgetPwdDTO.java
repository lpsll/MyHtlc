package com.autodesk.easyhome.shejijia.register.dto;

import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;
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
public class ForgetPwdDTO extends BaseDTO{


    private String smsverifycode;
    private String password;

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
        this.password = SecurityUtils.MD5(password);
    }
}
