package com.autodesk.easyhome.shejijia.order.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.base.SimplePage;
import com.autodesk.easyhome.shejijia.common.utils.UIHelper;
import com.autodesk.easyhome.shejijia.order.OrderUiGoto;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 订单之订单支付
 */
public class OrderNewPaymentActivity extends BaseTitleActivity {
    @Bind(R.id.tv_project)
    TextView mTvProject;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_tel)
    TextView mTvTel;
    @Bind(R.id.tv_address)
    TextView mTvAddress;
    @Bind(R.id.tv_money)
    TextView mTvMoney;
    @Bind(R.id.tv_one)
    TextView mTvOne;
    @Bind(R.id.tv_two)
    TextView mTvTwo;
    @Bind(R.id.place_cb_qb)
    CheckBox mPlaceCbQb;
    @Bind(R.id.rl_qb)
    RelativeLayout mRlQb;
    @Bind(R.id.place_cb_wx)
    CheckBox mPlaceCbWx;
    @Bind(R.id.rl_wx)
    RelativeLayout mRlWx;
    @Bind(R.id.place_cb_zfb)
    CheckBox mPlaceCbZfb;
    @Bind(R.id.rl_zfb)
    RelativeLayout mRlZfb;
    @Bind(R.id.rl_jf)
    RelativeLayout mJf;
    @Bind(R.id.rl_yhj)
    RelativeLayout mYhj;
    @Bind(R.id.tj_btn)
    Button mTjBtn;
    @Override
    protected int getContentResId() {
        return R.layout.activity_ordernew;
    }

    @Override
    public void initView() {
        setTitleText("订单支付");

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.rl_qb, R.id.rl_wx, R.id.rl_zfb, R.id.tj_btn, R.id.rl_jf, R.id.rl_yhj})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_qb:
                mPlaceCbQb.setChecked(true);
                mPlaceCbWx.setChecked(false);
                mPlaceCbZfb.setChecked(false);
                break;
            case R.id.rl_wx:
                mPlaceCbQb.setChecked(false);
                mPlaceCbWx.setChecked(true);
                mPlaceCbZfb.setChecked(false);
                break;
            case R.id.rl_zfb:
                mPlaceCbQb.setChecked(false);
                mPlaceCbWx.setChecked(false);
                mPlaceCbZfb.setChecked(true);
                break;
            case R.id.tj_btn:
                break;
            case R.id.rl_jf:
                break;
            case R.id.rl_yhj:
                Bundle b = new Bundle();

                UIHelper.showFragment(this, SimplePage.SERVICE_COUPON,b);
//                OrderUiGoto.gotoCoupon(this);
                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
        }
    }
}
