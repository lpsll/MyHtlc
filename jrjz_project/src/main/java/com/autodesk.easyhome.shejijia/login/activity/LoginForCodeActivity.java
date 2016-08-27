package com.autodesk.easyhome.shejijia.login.activity;

import android.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
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
import com.autodesk.easyhome.shejijia.mine.view.TimeButton;
import com.autodesk.easyhome.shejijia.register.entity.SmsVerifyEntity;
import com.htlc.jrjz.jrjz_project.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 验证码登录页面
 */
public class LoginForCodeActivity extends BaseTitleActivity {

    @Bind(R.id.et_login_phone)
    EditText etLoginPhone;
    @Bind(R.id.TimeButton_login)
    TimeButton TimeButtonLogin;
    @Bind(R.id.et_login_code)
    EditText etLoginCode;
    @Bind(R.id.tv_ok)
    TextView tvOk;
    @Bind(R.id.et_login_register)
    TextView etLoginRegister;

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

    }


    @OnClick({R.id.TimeButton_login, R.id.tv_ok, R.id.et_login_register})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.TimeButton_login:

                //验证电话号码
                boolean isValid = PhoneUtils.isPhoneNumberValid(etLoginPhone.getText().toString());
                if (!isValid) {
                    TimeButtonLogin.setLenght(0);
                    new AlertDialog.Builder(this).setTitle("请输入正确的电话号码!").setPositiveButton("确定", null).show();
                } else {
                    TimeButtonLogin.setLenght(60 * 1000);
                    //获取验证码
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
     * <p/>
     * uid：用户ID，默认为手机号码
     * <p/>
     * smsVerifyCode：短信验证码字符串
     * <p/>
     * timestamp：当前时间戳
     * <p/>
     * random：随机数
     * <p/>
     * sign：签名【生成规则 uid+smsVerifyCode+timestamp+random 后md5加密串】
     * <p/>
     * usertype:用户类型 1-普通用户 , 默认为1
     */
    private void loginForCode() {
        final String phone = etLoginPhone.getText().toString().trim();
        String code = etLoginCode.getText().toString().trim();
        //验证电话号码
        boolean isValid = PhoneUtils.isPhoneNumberValid(phone);
        if (!isValid) {
            new AlertDialog.Builder(this).setTitle("请输入正确的电话号码!").setPositiveButton("确定", null).show();
            return;
        }


        LoginForCodeDTO loginForCodeDTO = new LoginForCodeDTO();
        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();

        loginForCodeDTO.setUid(phone);
        loginForCodeDTO.setSmsVerifyCode(code);
        loginForCodeDTO.setTimestamp(time);
        loginForCodeDTO.setRandom(random);
        loginForCodeDTO.setSign(phone + code + time + random);
        loginForCodeDTO.setUsertype(AppConfig.COMMON_USER_TYPE); //默认为普通用户

        LogUtils.e("time---", "" + time);
        LogUtils.e("random---", "" + random);
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
                        AppContext.set(AppConfig.UID, phone);
                        AppContext.set(AppConfig.ACCESSTOKEN, result.getData().getAccessToken());
                        AppContext.set(AppConfig.IS_LOGIN, true);

                        //跳转到预约页面
//                        HomeUiGoto.gotoApt(LoginForCodeActivity.this);
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
        String time = TimeUtils.getSignTime();
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
