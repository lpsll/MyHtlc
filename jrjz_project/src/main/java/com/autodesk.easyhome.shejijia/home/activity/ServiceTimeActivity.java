package com.autodesk.easyhome.shejijia.home.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.home.entity.TimeEntity;
import com.autodesk.easyhome.shejijia.home.entity.TimeResult;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 服务时间页
 */
public class ServiceTimeActivity extends BaseTitleActivity {
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
    @Bind(R.id.data_01)
    TextView data01;
    @Bind(R.id.week_01)
    TextView week01;
    @Bind(R.id.tv_01)
    TextView tv01;
    @Bind(R.id.lin01)
    LinearLayout lin01;
    @Bind(R.id.data_02)
    TextView data02;
    @Bind(R.id.week_02)
    TextView week02;
    @Bind(R.id.tv_02)
    TextView tv02;
    @Bind(R.id.lin02)
    LinearLayout lin02;
    @Bind(R.id.data_03)
    TextView data03;
    @Bind(R.id.week_03)
    TextView week03;
    @Bind(R.id.tv_03)
    TextView tv03;
    @Bind(R.id.lin03)
    LinearLayout lin03;
    @Bind(R.id.data_04)
    TextView data04;
    @Bind(R.id.week_04)
    TextView week04;
    @Bind(R.id.tv_04)
    TextView tv04;
    @Bind(R.id.lin04)
    LinearLayout lin04;
    @Bind(R.id.data_05)
    TextView data05;
    @Bind(R.id.week_05)
    TextView week05;
    @Bind(R.id.tv_05)
    TextView tv05;
    @Bind(R.id.lin05)
    LinearLayout lin05;

    List<TimeEntity> data;
    private String time,mTd;
    @Override
    protected int getContentResId() {
        return R.layout.activity_servicetime;
    }

    @Override
    public void initView() {
        setTitleText("服务时间");
    }

    @Override
    public void initData() {
        reqTime();//服务时间
    }

    private void reqTime() {
        BaseDTO dto = new BaseDTO();
        CommonApiClient.time(this, dto, new CallBack<TimeResult>() {
            @Override
            public void onSuccess(TimeResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("服务时间成功");
                    bindTime(result);

                }

            }
        });
    }

    private void bindTime(TimeResult result) {
        data = result.getData();
        data01.setText(data.get(0).getShortDate());
        week01.setText(data.get(0).getWeek());
        data02.setText(data.get(1).getShortDate());
        week02.setText(data.get(1).getWeek());
        data03.setText(data.get(2).getShortDate());
        week03.setText(data.get(2).getWeek());
        data04.setText(data.get(3).getShortDate());
        week04.setText(data.get(3).getWeek());
        data05.setText(data.get(4).getShortDate());
        week05.setText(data.get(4).getWeek());
        mTd = data.get(0).getDate();
    }


    @OnClick({R.id.st_tv01,R.id.st_tv02,R.id.st_tv03,R.id.st_tv04,R.id.st_tv05,R.id.st_tv06,R.id.lin01, R.id.lin02, R.id.lin03, R.id.lin04, R.id.lin05})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin01:
                mTd = data.get(0).getDate();
                data01.setTextColor(getResources().getColor(R.color.navi));
                week01.setTextColor(getResources().getColor(R.color.navi));
                tv01.setVisibility(View.VISIBLE);
                data02.setTextColor(getResources().getColor(R.color.color_f9));
                week02.setTextColor(getResources().getColor(R.color.color_33));
                tv02.setVisibility(View.GONE);
                data03.setTextColor(getResources().getColor(R.color.color_f9));
                week03.setTextColor(getResources().getColor(R.color.color_33));
                tv03.setVisibility(View.GONE);
                data04.setTextColor(getResources().getColor(R.color.color_f9));
                week04.setTextColor(getResources().getColor(R.color.color_33));
                tv04.setVisibility(View.GONE);
                data05.setTextColor(getResources().getColor(R.color.color_f9));
                week05.setTextColor(getResources().getColor(R.color.color_33));
                tv05.setVisibility(View.GONE);

                break;
            case R.id.lin02:
                mTd = data.get(1).getDate();
                data01.setTextColor(getResources().getColor(R.color.color_f9));
                week01.setTextColor(getResources().getColor(R.color.color_33));
                tv01.setVisibility(View.GONE);
                data02.setTextColor(getResources().getColor(R.color.navi));
                week02.setTextColor(getResources().getColor(R.color.navi));
                tv02.setVisibility(View.VISIBLE);
                data03.setTextColor(getResources().getColor(R.color.color_f9));
                week03.setTextColor(getResources().getColor(R.color.color_33));
                tv03.setVisibility(View.GONE);
                data04.setTextColor(getResources().getColor(R.color.color_f9));
                week04.setTextColor(getResources().getColor(R.color.color_33));
                tv04.setVisibility(View.GONE);
                data05.setTextColor(getResources().getColor(R.color.color_f9));
                week05.setTextColor(getResources().getColor(R.color.color_33));
                tv05.setVisibility(View.GONE);
                break;
            case R.id.lin03:
                mTd = data.get(2).getDate();
                data01.setTextColor(getResources().getColor(R.color.color_f9));
                week01.setTextColor(getResources().getColor(R.color.color_33));
                tv01.setVisibility(View.GONE);
                data02.setTextColor(getResources().getColor(R.color.color_f9));
                week02.setTextColor(getResources().getColor(R.color.color_33));
                tv02.setVisibility(View.GONE);
                data03.setTextColor(getResources().getColor(R.color.navi));
                week03.setTextColor(getResources().getColor(R.color.navi));
                tv03.setVisibility(View.VISIBLE);
                data04.setTextColor(getResources().getColor(R.color.color_f9));
                week04.setTextColor(getResources().getColor(R.color.color_33));
                tv04.setVisibility(View.GONE);
                data05.setTextColor(getResources().getColor(R.color.color_f9));
                week05.setTextColor(getResources().getColor(R.color.color_33));
                tv05.setVisibility(View.GONE);
                break;
            case R.id.lin04:
                mTd = data.get(3).getDate();
                data01.setTextColor(getResources().getColor(R.color.color_f9));
                week01.setTextColor(getResources().getColor(R.color.color_33));
                tv01.setVisibility(View.GONE);
                data02.setTextColor(getResources().getColor(R.color.color_f9));
                week02.setTextColor(getResources().getColor(R.color.color_33));
                tv02.setVisibility(View.GONE);
                data03.setTextColor(getResources().getColor(R.color.color_f9));
                week03.setTextColor(getResources().getColor(R.color.color_33));
                tv03.setVisibility(View.GONE);
                data04.setTextColor(getResources().getColor(R.color.navi));
                week04.setTextColor(getResources().getColor(R.color.navi));
                tv04.setVisibility(View.VISIBLE);
                data05.setTextColor(getResources().getColor(R.color.color_f9));
                week05.setTextColor(getResources().getColor(R.color.color_33));
                tv05.setVisibility(View.GONE);
                break;
            case R.id.lin05:
                mTd = data.get(4).getDate();
                data01.setTextColor(getResources().getColor(R.color.color_f9));
                week01.setTextColor(getResources().getColor(R.color.color_33));
                tv01.setVisibility(View.GONE);
                data02.setTextColor(getResources().getColor(R.color.color_f9));
                week02.setTextColor(getResources().getColor(R.color.color_33));
                tv02.setVisibility(View.GONE);
                data03.setTextColor(getResources().getColor(R.color.color_f9));
                week03.setTextColor(getResources().getColor(R.color.color_33));
                tv03.setVisibility(View.GONE);
                data04.setTextColor(getResources().getColor(R.color.color_f9));
                week04.setTextColor(getResources().getColor(R.color.color_33));
                tv04.setVisibility(View.GONE);
                data05.setTextColor(getResources().getColor(R.color.navi));
                week05.setTextColor(getResources().getColor(R.color.navi));
                tv05.setVisibility(View.VISIBLE);
                break;
            case R.id.st_tv01:
                time = "08:00----10:00";
                mStTv01.setBackground(getResources().getDrawable(R.drawable.btn_blue_border));
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
                AppContext.set("serviceTime",mTd+time);
                setResult(12);
                finish();
                break;
            case R.id.st_tv02:
                time = "10:00----12:00";
                mStTv01.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv01.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv02.setBackground(getResources().getDrawable(R.drawable.btn_blue_border));
                mStTv02.setTextColor(getResources().getColor(R.color.navi));
                mStTv03.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv03.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv04.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv04.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv05.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv05.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv06.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv06.setTextColor(getResources().getColor(R.color.color_cc));
                AppContext.set("serviceTime",mTd+"  "+time);
                setResult(12);
                finish();
                break;
            case R.id.st_tv03:
                time = "12:00----14:00";
                mStTv01.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv01.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv02.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv02.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv03.setBackground(getResources().getDrawable(R.drawable.btn_blue_border));
                mStTv03.setTextColor(getResources().getColor(R.color.navi));
                mStTv04.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv04.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv05.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv05.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv06.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv06.setTextColor(getResources().getColor(R.color.color_cc));
                AppContext.set("serviceTime",mTd+"  "+time);
                setResult(12);
                finish();
                break;
            case R.id.st_tv04:
                time = "14:00----16:00";
                mStTv01.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv01.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv02.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv02.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv03.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv03.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv04.setBackground(getResources().getDrawable(R.drawable.btn_blue_border));
                mStTv04.setTextColor(getResources().getColor(R.color.navi));
                mStTv05.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv05.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv06.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv06.setTextColor(getResources().getColor(R.color.color_cc));
                AppContext.set("serviceTime",mTd+"  "+time);
                setResult(12);
                finish();
                break;
            case R.id.st_tv05:
                time = "16:00----18:00";
                mStTv01.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv01.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv02.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv02.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv03.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv03.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv04.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv04.setTextColor(getResources().getColor(R.color.color_cc));
                mStTv05.setBackground(getResources().getDrawable(R.drawable.btn_blue_border));
                mStTv05.setTextColor(getResources().getColor(R.color.navi));
                mStTv06.setBackground(getResources().getDrawable(R.drawable.btn_gray));
                mStTv06.setTextColor(getResources().getColor(R.color.color_cc));
                AppContext.set("serviceTime",mTd+"  "+time);
                setResult(12);
                finish();
                break;
            case R.id.st_tv06:
                time = "18:00----20:00";
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
                mStTv06.setBackground(getResources().getDrawable(R.drawable.btn_blue_border));
                mStTv06.setTextColor(getResources().getColor(R.color.navi));
                AppContext.set("serviceTime",mTd+"  "+time);
                setResult(12);
                finish();
                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
        }
    }
}
