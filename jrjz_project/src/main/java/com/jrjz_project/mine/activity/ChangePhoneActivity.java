package com.jrjz_project.mine.activity;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.common.base.BaseTitleActivity;
import com.jrjz_project.mine.view.TimeButton;

public class ChangePhoneActivity extends BaseTitleActivity {

    private TimeButton TimeButton_mine_changePhone;


    @Override
    public void initView() {
        setTitleText("换绑手机");

        TimeButton_mine_changePhone = (TimeButton)findViewById(R.id.TimeButton_mine_changePhone);
        TimeButton_mine_changePhone.setTextAfter("s后再次获取").setTextBefore("获取验证码").setLenght(15 * 1000);
    }

    @Override
    public void initData() {

    }

    @Override
    protected int getContentResId() {
        return R.layout.activity_change_phone;
    }
}
