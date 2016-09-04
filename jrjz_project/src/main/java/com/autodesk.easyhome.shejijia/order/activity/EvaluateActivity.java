package com.autodesk.easyhome.shejijia.order.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.DialogUtils;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.order.dto.EvaluateDTO;
import com.autodesk.easyhome.shejijia.order.entity.IntegralResult;

import butterknife.Bind;
import butterknife.ButterKnife;
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
    @Bind(R.id.et)
    EditText mEt;
    @Bind(R.id.evaluate_btn)
    Button mEvaluateBtn;
    @Bind(R.id.lin01)
    LinearLayout lin01;
    @Bind(R.id.lin02)
    LinearLayout lin02;
    @Bind(R.id.lin03)
    LinearLayout lin03;
    @Bind(R.id.lin04)
    LinearLayout lin04;
    @Bind(R.id.lin05)
    LinearLayout lin05;
    @Bind(R.id.lin06)
    LinearLayout lin06;
    @Bind(R.id.lin07)
    LinearLayout lin07;
    @Bind(R.id.lin08)
    LinearLayout lin08;
    @Bind(R.id.lin09)
    LinearLayout lin09;
    @Bind(R.id.lin10)
    LinearLayout lin10;
    private String tv1, tv2, tv3, tv4, tv5;
    private String mId;

    @Override
    protected int getContentResId() {
        return R.layout.activity_evaluate;
    }

    @Override
    public void initView() {
        setTitleText("评价");
        mId = getIntent().getBundleExtra("bundle").getString("mId");
    }

    @Override
    public void initData() {
        tv1 = "ture";
        tv2 = "ture";
        tv3 = "ture";
        tv4 = "ture";
        tv5 = "ture";
    }

    private void reqEvaluate() {
        EvaluateDTO dto = new EvaluateDTO();
        dto.setAccessToken(AppContext.get("accessToken", ""));
        dto.setOrderId(mId);
        dto.setChargeforIs(tv5);
        dto.setOpinion(mEt.getText().toString());
        dto.setProblemIs(tv3);
        dto.setStatementIs(tv4);
        dto.setTimeIs(tv1);
        dto.setServiceIs(tv2);
        CommonApiClient.evaluate(this, dto, new CallBack<IntegralResult>() {
            @Override
            public void onSuccess(IntegralResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("评价成功");
                    finish();

                }

            }
        });
    }



    @OnClick({R.id.lin01, R.id.lin02, R.id.lin03, R.id.lin04, R.id.lin05, R.id.lin06, R.id.lin07, R.id.lin08, R.id.lin09, R.id.lin10, R.id.evaluate_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin01:
                tv1 = "ture";
                mImg01.setBackground(getResources().getDrawable(R.drawable.shi_03));
                mImg02.setBackground(getResources().getDrawable(R.drawable.fou_03));
                break;
            case R.id.lin02:
                tv1 = "false";
                mImg01.setBackground(getResources().getDrawable(R.drawable.fou_03));
                mImg02.setBackground(getResources().getDrawable(R.drawable.shi_03));
                break;
            case R.id.lin03:
                tv2 = "ture";
                mImg03.setBackground(getResources().getDrawable(R.drawable.shi_03));
                mImg04.setBackground(getResources().getDrawable(R.drawable.fou_03));
                break;
            case R.id.lin04:
                tv2 = "false";
                mImg03.setBackground(getResources().getDrawable(R.drawable.fou_03));
                mImg04.setBackground(getResources().getDrawable(R.drawable.shi_03));
                break;
            case R.id.lin05:
                tv3 = "ture";
                mImg05.setBackground(getResources().getDrawable(R.drawable.shi_03));
                mImg06.setBackground(getResources().getDrawable(R.drawable.fou_03));
                break;
            case R.id.lin06:
                tv3 = "false";
                mImg05.setBackground(getResources().getDrawable(R.drawable.fou_03));
                mImg06.setBackground(getResources().getDrawable(R.drawable.shi_03));
                break;
            case R.id.lin07:
                tv4 = "ture";
                mImg07.setBackground(getResources().getDrawable(R.drawable.shi_03));
                mImg08.setBackground(getResources().getDrawable(R.drawable.fou_03));
                break;
            case R.id.lin08:
                tv4 = "false";
                mImg07.setBackground(getResources().getDrawable(R.drawable.fou_03));
                mImg08.setBackground(getResources().getDrawable(R.drawable.shi_03));
                break;
            case R.id.lin09:
                tv5 = "ture";
                mImg09.setBackground(getResources().getDrawable(R.drawable.shi_03));
                mImg10.setBackground(getResources().getDrawable(R.drawable.fou_03));
                break;
            case R.id.lin10:
                tv5 = "false";
                mImg09.setBackground(getResources().getDrawable(R.drawable.fou_03));
                mImg10.setBackground(getResources().getDrawable(R.drawable.shi_03));
                break;
            case R.id.evaluate_btn:
                if (TextUtils.isEmpty(mEt.getText().toString())) {
                    DialogUtils.showPrompt(this, "提示", "请提交意见！", "知道了");
                } else {
                    reqEvaluate();
                }

                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
        }
    }
}
