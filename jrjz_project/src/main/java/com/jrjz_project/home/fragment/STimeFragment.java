package com.jrjz_project.home.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.common.base.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by John_Libo on 2016/8/17.
 */
public class STimeFragment extends BaseFragment {
    @Bind(R.id.st_tv01)
    TextView mStTv01;
    @Bind(R.id.st_tv02)
    TextView mStTv02;
    @Bind(R.id.st_tv03)
    TextView mStTv03;
    @Bind(R.id.st_tv04)
    TextView mStTv04;
    @Bind(R.id.st_tv05)
    TextView mStTv05;
    @Bind(R.id.st_tv06)
    TextView mStTv06;

    private static final String TYPE = "type";
    private int type;

    @Override
    protected void retry() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_st;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.st_tv01, R.id.st_tv02, R.id.st_tv03, R.id.st_tv04, R.id.st_tv05, R.id.st_tv06})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.st_tv01:
                mStTv01.setBackground(getResources().getDrawable(R.drawable.btn_blue));
                mStTv01.setTextColor(getResources().getColor(R.color.navi));
                mStTv02.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv02.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv03.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv03.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv04.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv04.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv05.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv05.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv06.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv06.setTextColor(getResources().getColor(R.color.color_cc));
                break;
            case R.id.st_tv02:
                mStTv01.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv01.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv02.setBackground(getResources().getDrawable(R.drawable.btn_blue));
                mStTv02.setTextColor(getResources().getColor(R.color.navi));
                mStTv03.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv03.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv04.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv04.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv05.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv05.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv06.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv06.setTextColor(getResources().getColor(R.color.color_cc));
                break;
            case R.id.st_tv03:
                mStTv01.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv01.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv02.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv02.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv03.setBackground(getResources().getDrawable(R.drawable.btn_blue));
                mStTv03.setTextColor(getResources().getColor(R.color.navi));
                mStTv04.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv04.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv05.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv05.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv06.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv06.setTextColor(getResources().getColor(R.color.color_cc));
                break;
            case R.id.st_tv04:
                mStTv01.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv01.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv02.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv02.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv03.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv03.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv04.setBackground(getResources().getDrawable(R.drawable.btn_blue));
                mStTv04.setTextColor(getResources().getColor(R.color.navi));
                mStTv05.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv05.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv06.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv06.setTextColor(getResources().getColor(R.color.color_cc));
                break;
            case R.id.st_tv05:
                mStTv01.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv01.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv02.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv02.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv03.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv03.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv04.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv04.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv05.setBackground(getResources().getDrawable(R.drawable.btn_blue));
                mStTv05.setTextColor(getResources().getColor(R.color.navi));
                mStTv06.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv06.setTextColor(getResources().getColor(R.color.color_cc));
                break;
            case R.id.st_tv06:
                mStTv01.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv01.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv02.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv02.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv03.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv03.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv04.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv04.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv05.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv05.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv06.setBackground(getResources().getDrawable(R.drawable.btn_blue));
                mStTv06.setTextColor(getResources().getColor(R.color.navi));
                break;
        }
    }

    public static STimeFragment newInstance(int type) {
        STimeFragment fragment = new STimeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);//传递Type
        fragment.setArguments(bundle);
        return fragment;
    }
}
