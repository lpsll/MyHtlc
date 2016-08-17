package com.jrjz_project.mine.activity;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.common.base.BaseTitleActivity;

public class ChangePhoneActivity extends BaseTitleActivity {


    @Override
    public void initView() {
        setTitleText("换绑手机");

    }

    @Override
    public void initData() {

    }

    @Override
    protected int getContentResId() {
        return R.layout.activity_change_phone;
    }
}
