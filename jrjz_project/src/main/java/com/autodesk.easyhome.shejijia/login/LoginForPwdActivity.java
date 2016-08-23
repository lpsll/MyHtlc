package com.autodesk.easyhome.shejijia.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.htlc.jrjz.jrjz_project.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 密码登录页面
 */
public class LoginForPwdActivity extends BaseTitleActivity {

    @Bind(R.id.et_login_phone)
    EditText etLoginPhone;
    @Bind(R.id.et_login_pwd)
    EditText etLoginPwd;
    @Bind(R.id.et_login_forgetpwd)
    TextView etLoginForgetpwd;
    @Bind(R.id.tv_ok)
    TextView tvOk;
    @Bind(R.id.et_login_register)
    TextView etLoginRegister;

    @Override
    protected int getContentResId() {
        return R.layout.activity_login_for_pwd;
    }

    @Override
    public void initView() {

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

    @OnClick({R.id.et_login_forgetpwd, R.id.tv_ok, R.id.et_login_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_login_forgetpwd:
                break;
            case R.id.tv_ok:
                break;
            case R.id.et_login_register:
                break;
        }
    }
}
