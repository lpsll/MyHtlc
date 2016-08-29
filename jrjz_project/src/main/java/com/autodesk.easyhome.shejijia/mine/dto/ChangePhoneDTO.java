package com.autodesk.easyhome.shejijia.mine.dto;

import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;

/**
 * Created by Administrator on 2016/8/24.
 * 修改用户信息接口

 accessToken:访问授权码

 uid:用户ID，默认为原手机号码

 timestamp:当前时间戳

 random:随机数

 sign:签名【生成规则uid+timestamp+random 后md5加密串】

 newmobile:新手机号

 smsverifycode:短信验证码
 */
public class ChangePhoneDTO extends BaseDTO {

    private String newmobile;
    private String smsverifycode;


    public String getNewmobile() {
        return newmobile;
    }

    public void setNewmobile(String newmobile) {
        this.newmobile = newmobile;
    }

    public String getSmsverifycode() {
        return smsverifycode;
    }

    public void setSmsverifycode(String smsverifycode) {
        this.smsverifycode = smsverifycode;
    }
}
