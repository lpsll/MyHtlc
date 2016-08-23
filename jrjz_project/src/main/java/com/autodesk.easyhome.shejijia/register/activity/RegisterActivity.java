package com.autodesk.easyhome.shejijia.register.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.mine.view.TimeButton;
import com.htlc.jrjz.jrjz_project.R;

import butterknife.Bind;
import butterknife.ButterKnife;
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
    @Bind(R.id.et_register_login)
    TextView etRegisterLogin;

    String url = "http://101.200.167.130:8080/jrjz-api/swagger/index.html#!/api/user/getSmsVerifyCode";
    @Bind(R.id.TimeButton_register)
    TimeButton TimeButtonRegister;

    @Override
    protected int getContentResId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        setTitleText("新用户注册");

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick(R.id.TimeButton_register)
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case  R.id.TimeButton_register:
                //获取验证码
                getSmsVerifyCode();
        
                break;
        }

    }

    /**
     * 获取验证码
     */
    private void getSmsVerifyCode() {


    }


}
