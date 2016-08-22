package com.autodesk.easyhome.shejijia.mine.activity;

import com.htlc.jrjz.jrjz_project.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;

/**
 * 意见反馈页
 */
public class FeedBackActivity extends BaseTitleActivity {



    @Override
    public void initView() {
        setTitleText("意见反馈");

    }

    @Override
    public void initData() {

    }

    @Override
    protected int getContentResId() {
        return R.layout.suggest_feedback;
    }
}
