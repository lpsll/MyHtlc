package com.jrjz_project.home.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.common.base.BaseTitleActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by John_Libo on 2016/8/17.
 */
public class AddAddressActivity extends BaseTitleActivity {
    @Bind(R.id.et_name)
    EditText mEtName;
    @Bind(R.id.et_phone)
    EditText mEtPhone;
    @Bind(R.id.tv_city)
    TextView mTvCity;
    @Bind(R.id.lin01)
    LinearLayout mLin01;
    @Bind(R.id.tv_are)
    TextView mTvAre;
    @Bind(R.id.lin02)
    LinearLayout mLin02;
    @Bind(R.id.tv_huan)
    TextView mTvHuan;
    @Bind(R.id.lin03)
    LinearLayout mLin03;
    @Bind(R.id.et_address)
    EditText mEtAddress;
    @Bind(R.id.add_btn)
    Button mAddBtn;

    @Override
    protected int getContentResId() {
        return R.layout.activity_addaddress;
    }

    @Override
    public void initView() {
        setTitleText("新增地址");

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.lin01, R.id.lin02, R.id.lin03, R.id.add_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin01:
                break;
            case R.id.lin02:
                break;
            case R.id.lin03:
                break;
            case R.id.add_btn:
                break;
        }
    }
}
