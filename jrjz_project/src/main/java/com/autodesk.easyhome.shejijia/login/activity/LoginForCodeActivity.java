package com.autodesk.easyhome.shejijia.login.activity;

import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.PhoneUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.common.utils.ToastUtils;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.autodesk.easyhome.shejijia.login.dto.LoginForCodeDTO;
import com.autodesk.easyhome.shejijia.login.entity.LoginEntity;
import com.autodesk.easyhome.shejijia.mine.view.TimeCountUtil;
import com.autodesk.easyhome.shejijia.register.entity.SmsVerifyEntity;

import butterknife.Bind;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/**
 * 验证码登录页面
 */
public class LoginForCodeActivity extends BaseTitleActivity {

    @Bind(R.id.et_login_phone)
    EditText etLoginPhone;
    @Bind(R.id.TimeButton_login)
    Button TimeButtonLogin;
    @Bind(R.id.et_login_code)
    EditText etLoginCode;
    @Bind(R.id.tv_ok)
    TextView tvOk;
    @Bind(R.id.et_login_register)
    TextView etLoginRegister;

    private TimeCountUtil timeCountUtil;

    @Override
    protected int getContentResId() {
        return R.layout.activity_login_for_code;
    }

    @Override
    public void initView() {
        setTitleText("登录");

    }

    @Override
    public void initData() {
        timeCountUtil = new TimeCountUtil(this, 60000, 1000, TimeButtonLogin);
    }


    @OnClick({R.id.TimeButton_login, R.id.tv_ok, R.id.et_login_register})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.TimeButton_login:

                //验证电话号码
                boolean isValid = PhoneUtils.isPhoneNumberValid(etLoginPhone.getText().toString());
                if (!isValid) {
                    new AlertDialog.Builder(this).setTitle("温馨提示").setMessage("请输入正确的电话号码!").setPositiveButton("确定", null).show();
                } else {
                    //获取验证码
                    timeCountUtil.start();
                    getSmsVerifyCode();
                }
                break;
            case R.id.tv_ok:
                //验证码登录
                loginForCode();
                break;

            case R.id.et_login_register:
                //跳转到注册页面
                HomeUiGoto.gotoRegister(LoginForCodeActivity.this);
                finish();
                break;

        }
    }

    /**
     * 使用验证码登录
     * uid：用户ID，默认为手机号码
     * smsVerifyCode：短信验证码字符串
     * timestamp：当前时间戳
     * random：随机数
     * sign：签名【生成规则 uid+smsVerifyCode+timestamp+random 后md5加密串】
     * usertype:用户类型 1-普通用户 , 默认为1
     */
    private void loginForCode() {
        final String phone = etLoginPhone.getText().toString().trim();
        final String code = etLoginCode.getText().toString().trim();
        //验证电话号码
        boolean isValid = PhoneUtils.isPhoneNumberValid(phone);
        if (!isValid) {
            new AlertDialog.Builder(this).setTitle("温馨提示").setMessage("请输入正确的电话号码!").setPositiveButton("确定", null).show();
            return;
        }

        LoginForCodeDTO loginForCodeDTO = new LoginForCodeDTO();
        long time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();

        loginForCodeDTO.setUid(phone);
        loginForCodeDTO.setSmsVerifyCode(code);
        loginForCodeDTO.setTimestamp(time);
        loginForCodeDTO.setRandom(random);
        loginForCodeDTO.setSign(phone + code + time + random);
        loginForCodeDTO.setUsertype(AppConfig.COMMON_USER_TYPE); //默认为普通用户

        CommonApiClient.loginForCode(this, loginForCodeDTO, new CallBack<LoginEntity>() {
            @Override
            public void onSuccess(LoginEntity result) {
                if (result != null) {
                    LogUtils.e("result========" + result.getMsg());
                    if (AppConfig.SUCCESS.equals(result.getCode())) {
                        LogUtils.e("登录成功");
                        ToastUtils.showShort(LoginForCodeActivity.this, "登录成功");
                        LogUtils.e("用户令牌======" + result.getData().getAccessToken());

                        //保存用户信息
                        AppContext.set("uid", phone);
                        AppContext.set("code", code);
                        AppContext.set("accessToken", result.getData().getAccessToken());
                        AppContext.set("IS_LOGIN", true);

                        //登录成功后设置极光推送的别名和tag
                        JPushInterface.setAlias(LoginForCodeActivity.this, etLoginPhone.getText().toString(), null);

                        setResult(1001);
                        finish();
                    }
                }
            }
        });
    }

    /**
     * 获取验证码
     */
    private void getSmsVerifyCode() {
        BaseDTO dto = new BaseDTO();
        long time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();
        dto.setRandom(random);
        dto.setTimestamp(time);
        dto.setUid(etLoginPhone.getText().toString());
        dto.setSign(etLoginPhone.getText().toString() + time + random);
        LogUtils.e("time---", "" + time);
        LogUtils.e("random---", "" + random);
        CommonApiClient.verifyCode(this, dto, new CallBack<SmsVerifyEntity>() {
            @Override
            public void onSuccess(SmsVerifyEntity result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("获取验证码成功");
                    LogUtils.e("result---------", "" + result);
                }
            }
        });
    }
}
