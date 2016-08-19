package com.jrjz_project.home.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.common.base.BaseTitleActivity;
import com.jrjz_project.common.base.SimplePage;
import com.jrjz_project.common.utils.UIHelper;
import com.jrjz_project.home.HomeUiGoto;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 预约页
 */
public class AppointmentActivity extends BaseTitleActivity {
    @Bind(R.id.add_tv01)
    TextView mAddTv01;
    @Bind(R.id.add_tv02)
    TextView mAddTv02;
    @Bind(R.id.add_tv03)
    TextView mAddTv03;
    @Bind(R.id.add_tv04)
    TextView mAddTv04;
    @Bind(R.id.lin_address)
    LinearLayout mLinAddress;
    @Bind(R.id.tv_project)
    TextView mTvProject;
    @Bind(R.id.lin_time)
    LinearLayout mLinTime;
    @Bind(R.id.et_describe)
    EditText mEtDescribe;
    @Bind(R.id.apt_img)
    ImageView mAptImg;
    @Bind(R.id.img_ljd)
    ImageView mImgLjd;
    @Bind(R.id.tv_money)
    TextView mTvMoney;
    @Bind(R.id.apt_btn)
    Button mAptBtn;

    @Override
    protected int getContentResId() {
        return R.layout.activity_appointment;
    }

    @Override
    public void initView() {
        setTitleText("预约");

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.lin_address, R.id.lin_time, R.id.apt_img, R.id.img_ljd, R.id.apt_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_address:
//                UIHelper.showFragment(this, SimplePage.SELECT_ADDRESS);//常用地址
                HomeUiGoto.gotoSelect(this);
                break;
            case R.id.lin_time:
                HomeUiGoto.gotoSt(this);
                break;
            case R.id.apt_img:
                break;
            case R.id.img_ljd:
                break;
            case R.id.apt_btn:
                HomeUiGoto.gotoOrder(this);
                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
        }
    }
}
