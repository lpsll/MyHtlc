package com.jrjz_project.mine.activity;

import android.os.Bundle;
import android.widget.EditText;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.common.base.BaseTitleActivity;
import com.jrjz_project.mine.view.TimeButton;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChangePhoneActivity extends BaseTitleActivity {

    @Bind(R.id.username_txt)
    EditText usernameTxt;
    private TimeButton TimeButton_mine_changePhone;


    @Override
    public void initView() {
        setTitleText("换绑手机");

        TimeButton_mine_changePhone = (TimeButton) findViewById(R.id.TimeButton_mine_changePhone);
        TimeButton_mine_changePhone.setTextAfter("s后再次获取").setTextBefore("获取验证码").setLenght(60 * 1000);

    }

    @Override
    public void initData() {

    }

    @Override
    protected int getContentResId() {
        return R.layout.activity_change_phone;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
