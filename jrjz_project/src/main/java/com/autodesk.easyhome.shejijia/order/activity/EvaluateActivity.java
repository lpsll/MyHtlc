package com.autodesk.easyhome.shejijia.order.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 评价页
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
        setTitleText("评价");
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.img01, R.id.img02, R.id.img03, R.id.img04, R.id.img05, R.id.img06, R.id.img07, R.id.img08, R.id.img09, R.id.img10, R.id.evaluate_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img01:
                mImg01.setBackground(getResources().getDrawable(R.drawable.shi_03));
                mImg02.setBackground(getResources().getDrawable(R.drawable.fou_03));
                break;
            case R.id.img02:
                mImg02.setBackground(getResources().getDrawable(R.drawable.shi_03));
                mImg01.setBackground(getResources().getDrawable(R.drawable.fou_03));
                break;
            case R.id.img03:
                mImg03.setBackground(getResources().getDrawable(R.drawable.shi_03));
                mImg04.setBackground(getResources().getDrawable(R.drawable.fou_03));
                break;
            case R.id.img04:
                mImg04.setBackground(getResources().getDrawable(R.drawable.shi_03));
                mImg03.setBackground(getResources().getDrawable(R.drawable.fou_03));
                break;
            case R.id.img05:
                mImg05.setBackground(getResources().getDrawable(R.drawable.shi_03));
                mImg06.setBackground(getResources().getDrawable(R.drawable.fou_03));
                break;
            case R.id.img06:
                mImg06.setBackground(getResources().getDrawable(R.drawable.shi_03));
                mImg05.setBackground(getResources().getDrawable(R.drawable.fou_03));
                break;
            case R.id.img07:
                mImg07.setBackground(getResources().getDrawable(R.drawable.shi_03));
                mImg08.setBackground(getResources().getDrawable(R.drawable.fou_03));
                break;
            case R.id.img08:
                mImg08.setBackground(getResources().getDrawable(R.drawable.shi_03));
                mImg07.setBackground(getResources().getDrawable(R.drawable.fou_03));
                break;
            case R.id.img09:
                mImg09.setBackground(getResources().getDrawable(R.drawable.shi_03));
                mImg10.setBackground(getResources().getDrawable(R.drawable.fou_03));
                break;
            case R.id.img10:
                mImg10.setBackground(getResources().getDrawable(R.drawable.shi_03));
                mImg09.setBackground(getResources().getDrawable(R.drawable.fou_03));
                break;
            case R.id.evaluate_btn:
                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
        }
    }
}
