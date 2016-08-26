package com.autodesk.easyhome.shejijia.home.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.htlc.jrjz.jrjz_project.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 项目详情页
 */
public class ProjectDetailsActivity extends BaseTitleActivity {
    @Bind(R.id.img)
    ImageView mImg;
    @Bind(R.id.pd_tv01)
    TextView mPdTv01;
    @Bind(R.id.jg_tv01)
    TextView mJgTv01;
    @Bind(R.id.jg_tv02)
    TextView mJgTv02;
    @Bind(R.id.jg_tv03)
    TextView mJgTv03;
    @Bind(R.id.jg_tv04)
    TextView mJgTv04;
    @Bind(R.id.tv_sv)
    TextView mTvSv;

    @Override
    protected int getContentResId() {
        return R.layout.activity_projectdetails;
    }

    @Override
    public void initView() {
        setTitleText("项目详情");
    }

    @Override
    public void initData() {

    }


}
