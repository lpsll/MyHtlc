package com.autodesk.easyhome.shejijia.register.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;
import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.PhoneUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.common.utils.ToastUtils;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.autodesk.easyhome.shejijia.mine.activity.H5Activity;
import com.autodesk.easyhome.shejijia.mine.view.TimeCountUtil;
import com.autodesk.easyhome.shejijia.register.dto.RegisterDTO;
import com.autodesk.easyhome.shejijia.register.entity.SmsVerifyEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

public class RegisterActivity extends BaseTitleActivity {

    @Bind(R.id.et_register_phone)
    EditText etRegisterPhone;
    @Bind(R.id.et_register_code)
    EditText etRegisterCode;
    @Bind(R.id.et_register_password)
    EditText etRegisterPassword;
    @Bind(R.id.et_register_password_again)
    EditText etRegisterPasswordAgain;
    @Bind(R.id.tv_ok)
    TextView tvOk;
    @Bind(R.id.tv_register_login)
    TextView tvRegisterLogin;

    @Bind(R.id.TimeButton_register)
    Button TimeButtonRegister;
    @Bind(R.id.tv_register_protocol)
    TextView tvRegisterProtocol;

    private TimeCountUtil timeCountUtil;

    @Override
    protected int getContentResId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        setTitleText("新用户注册");
        //对密码格式验证
//        pwdVerify();
    }

    /**
     * 对密码格式验证
     */
    private void pwdVerify() {
        etRegisterPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                } else {
                    String pwd = etRegisterPassword.getText().toString();
                    String reg = "^[a-zA-Z0-9]{6,20}$";
                    boolean isMatches = pwd.matches(reg);
                    if (!isMatches) {
                        new AlertDialog.Builder(RegisterActivity.this).setTitle("温馨提示").setMessage("密码格式为6位以上字母或数字!").setPositiveButton("确定", null).show();
                        etRegisterPassword.setText("");
                        etRegisterPassword.setFocusable(true);
                    }
                }
            }
        });
    }


    @Override
    public void initData() {
        timeCountUtil = new TimeCountUtil(this, 60000, 1000, TimeButtonRegister);
    }


    @OnClick({R.id.tv_register_protocol, R.id.TimeButton_register, R.id.tv_ok, R.id.tv_register_login})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.TimeButton_register:
                //验证电话号码
                boolean isValid = PhoneUtils.isPhoneNumberValid(etRegisterPhone.getText().toString());
                if (!isValid) {
                    new AlertDialog.Builder(this).setTitle("温馨提示").setMessage("请输入正确的电话号码!").setPositiveButton("确定", null).show();
                } else {
                    //获取验证码
                    timeCountUtil.start();
                    getSmsVerifyCode();
                }
                break;
            case R.id.tv_ok:
                //验证
                dataVerify();
                break;

            case R.id.tv_register_login:
                //跳转到用户名密码登录页面
                HomeUiGoto.gotoLoginForPwd(RegisterActivity.this);
                finish();
                break;

            case R.id.tv_register_protocol:
                Intent intent = new Intent(this, H5Activity.class);
                intent.putExtra("url", AppConfig.PROTOCOL);
                intent.putExtra("title", "居然家政使用协议");
                startActivity(intent);
                break;
        }

    }


    /**
     * 验证两次密码是否一致
     */
    private void dataVerify() {
        String phone = etRegisterPhone.getText().toString().trim();
        String pwd = etRegisterPassword.getText().toString().trim();
        String pwdAgain = etRegisterPasswordAgain.getText().toString().trim();
        //手机号码格式验证
        boolean valid = PhoneUtils.isPhoneNumberValid(phone);
        if (!valid) {
            new AlertDialog.Builder(this).setMessage("请输入正确的电话号码!").setTitle("温馨提示").setPositiveButton("确定", null).show();
            return;
        }

        //密码非空验证
        if (TextUtils.isEmpty(pwd)) {
            new AlertDialog.Builder(this).setMessage("密码不能为空!").setTitle("温馨提示").setPositiveButton("确定", null).show();
            return;
        }

        //密码格式验证
//        boolean isMatches = pwd.matches(AppConfig.PWD_REG);
//        if (!isMatches) {
//            new AlertDialog.Builder(RegisterActivity.this).setTitle("温馨提示").setMessage("密码格式为6位以上字母或数字!").setPositiveButton("确定", null).show();
//            etRegisterPassword.setText("");
//            return;
//        }

        //两次密码一致验证
        if (!pwd.equals(pwdAgain)) {
            //进行注册操作
            new AlertDialog.Builder(this).setMessage("两次密码不一致!").setTitle("温馨提示").setPositiveButton("确定", null).show();
            return;
        }
        //注册操作
        register();
    }


    /**
     * 用户注册
     */
    private void register() {

        RegisterDTO registerDTO = new RegisterDTO();
        long time = TimeUtils.getSignTime();
        LogUtils.e("date---",""+time);
        String random = TimeUtils.genNonceStr();
        registerDTO.setUid(etRegisterPhone.getText().toString());
        registerDTO.setPassword(etRegisterPassword.getText().toString());
        registerDTO.setTimestamp(time);
        registerDTO.setRandom(random);
        registerDTO.setSmsverifycode(etRegisterCode.getText().toString());
        registerDTO.setSign(etRegisterPhone.getText().toString() + registerDTO.getPassword() + time + random);
        CommonApiClient.register(this, registerDTO, new CallBack<BaseEntity>() {
            @Override
            public void onSuccess(BaseEntity result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("注册成功");
                    ToastUtils.showShort(RegisterActivity.this, "注册成功");
                    LogUtils.e("result---", "" + result);

                    //跳转到登录页面
                    HomeUiGoto.gotoLoginForPwd(RegisterActivity.this);
                    finish();
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
        LogUtils.e("date---",""+time);
        String random = TimeUtils.genNonceStr();
        dto.setRandom(random);
        dto.setTimestamp(time);
        dto.setUid(etRegisterPhone.getText().toString());
        dto.setSign(etRegisterPhone.getText().toString() + time + random);
        CommonApiClient.verifyCode(this, dto, new CallBack<SmsVerifyEntity>() {
            @Override
            public void onSuccess(SmsVerifyEntity result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("获取验证码成功");
                    ToastUtils.showShort(RegisterActivity.this, "获取验证码成功");
                    LogUtils.e("result---------", "" + result);
                }
            }
        });
    }

}
