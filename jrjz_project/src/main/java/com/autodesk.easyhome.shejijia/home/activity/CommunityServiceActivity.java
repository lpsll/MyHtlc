package com.autodesk.easyhome.shejijia.home.activity;

import android.view.View;

import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;

/**
 * 社区服务
 */
public class CommunityServiceActivity extends BaseTitleActivity {
    @Override
    protected int getContentResId() {
        return R.layout.activity_communityservice;
    }

    @Override
    public void initView() {
        setTitleText("社区服务");

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
            default:
                break;
        }
        super.onClick(v);
    }
}
