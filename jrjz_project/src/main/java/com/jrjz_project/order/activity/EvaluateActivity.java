package com.jrjz_project.order.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.common.base.BaseTitleActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by John_Libo on 2016/8/17.
 */
public class EvaluateActivity extends BaseTitleActivity {
    @Bind(R.id.img01)
    ImageView mImg01;
    @Bind(R.id.img02)
    ImageView mImg02;
    @Bind(R.id.img03)
    ImageView mImg03;
    @Bind(R.id.img04)
    ImageView mImg04;
    @Bind(R.id.img05)
    ImageView mImg05;
    @Bind(R.id.img06)
    ImageView mImg06;
    @Bind(R.id.img07)
    ImageView mImg07;
    @Bind(R.id.img08)
    ImageView mImg08;
    @Bind(R.id.img09)
    ImageView mImg09;
    @Bind(R.id.img10)
    ImageView mImg10;
    @Bind(R.id.evaluate_btn)
    Button mEvaluateBtn;

    @Override
    protected int getContentResId() {
        return R.layout.activity_evaluate;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.img01, R.id.img02, R.id.img03, R.id.img04, R.id.img05, R.id.img06, R.id.img07, R.id.img08, R.id.img09, R.id.img10, R.id.evaluate_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img01:
                break;
            case R.id.img02:
                break;
            case R.id.img03:
                break;
            case R.id.img04:
                break;
            case R.id.img05:
                break;
            case R.id.img06:
                break;
            case R.id.img07:
                break;
            case R.id.img08:
                break;
            case R.id.img09:
                break;
            case R.id.img10:
                break;
            case R.id.evaluate_btn:
                break;
        }
    }
}
