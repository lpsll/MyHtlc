package com.autodesk.easyhome.shejijia.order.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.order.entity.IntegralResult;

import java.math.BigDecimal;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 积分页
 */
public class IntegralActivity extends BaseTitleActivity {
    @Bind(R.id.tv_integral)
    TextView mTvIntegral;
    @Bind(R.id.tv_total)
    TextView mTvTotal;
    @Bind(R.id.btn_pre)
    Button mBtnPre;
    private String total,menoy;
    double tal,men,yuan;
    double d;

    @Override
    protected int getContentResId() {
        return R.layout.activity_integral;
    }

    @Override
    public void initView() {
        setTitleText("积分");
        total = getIntent().getBundleExtra("bundle").getString("total");
        tal = Double.parseDouble(total);
        LogUtils.e("tal---",""+tal);
    }

    @Override
    public void initData() {
        reqRule();//积分规则
    }

    private void reqRule() {
        BaseDTO dto = new BaseDTO();
        CommonApiClient.rule(this, dto, new CallBack<IntegralResult>() {
            @Override
            public void onSuccess(IntegralResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("积分规则成功");
                    menoy = result.getData();
                    men = Double.parseDouble(menoy);
                    LogUtils.e("men---",""+men);
                    reqIntegral();//积分
                }

            }
        });
    }

    private void reqIntegral() {
        BaseDTO dto = new BaseDTO();
        dto.setUid(AppContext.get("uid",""));
        CommonApiClient.integral(this, dto, new CallBack<IntegralResult>() {
            @Override
            public void onSuccess(IntegralResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("积分成功");
                    mTvIntegral.setText("用户积分： "+result.getData());
                    yuan = Double.parseDouble(result.getData());
                    LogUtils.e("yuan---",""+yuan);
                    if((yuan/men)<tal){
                        BigDecimal b = new BigDecimal(yuan/men);
                        d = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

                        mTvTotal.setText("当前用户有"+result.getData()+"积分可转换为"+d+"元");
//                        AppContext.set("Integral",String.valueOf(yuan/men));
//                        AppContext.set("rule",menoy);
                    }else {
                        mTvTotal.setText("当前用户有"+tal*men+"积分可转换为"+tal+"元");
//                        AppContext.set("Integral",total);
//                        AppContext.set("rule",menoy);
                    }
                }
            }
        });
    }





    @OnClick(R.id.btn_pre)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pre:
                if((yuan/men)<tal){
                        AppContext.set("Integral",String.valueOf(d));
                        AppContext.set("rule",menoy);
                }else {
                        AppContext.set("Integral",total);
                        AppContext.set("rule",menoy);
                }
                setResult(1010);
                finish();
                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
        }
    }
}
