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

public class ForgetPwdActivity extends BaseTitleActivity {

    @Bind(R.id.et_forgetpwd_phone)
    EditText etForgetpwdPhone;
    @Bind(R.id.TimeButton_forgetpwd)
    TimeButton TimeButtonForgetpwd;
    @Bind(R.id.et_forgetpwd_code)
    EditText etForgetpwdCode;
    @Bind(R.id.et_forgetpwd_pwd)
    EditText etForgetpwdPwd;
    @Bind(R.id.et_forgetpwd_pwd_again)
    EditText etForgetpwdPwdAgain;
    @Bind(R.id.tv_ok)
    TextView tvOk;

    @Override
    protected int getContentResId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    public void initView() {
        setTitleText("忘记密码");

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.TimeButton_forgetpwd, R.id.tv_ok})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.TimeButton_forgetpwd:
                break;
            case R.id.tv_ok:
                break;
        }
    }
}
