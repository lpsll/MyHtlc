package com.autodesk.easyhome.shejijia.order.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.base.SimplePage;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.common.utils.UIHelper;
import com.autodesk.easyhome.shejijia.home.dto.AddAddressDTO;
import com.autodesk.easyhome.shejijia.home.entity.AddAddressResult;
import com.autodesk.easyhome.shejijia.order.OrderUiGoto;
import com.autodesk.easyhome.shejijia.order.dto.NewPaymentDTO;
import com.autodesk.easyhome.shejijia.order.dto.OrderDetailDTO;
import com.autodesk.easyhome.shejijia.order.entity.IntegralResult;
import com.autodesk.easyhome.shejijia.order.entity.OrderDetailResult;
import com.autodesk.easyhome.shejijia.order.entity.OrderDetailsEntity;

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
    private String mId;
    OrderDetailsEntity data;
    private String total,type,mPoint,mCoupon;
    boolean integral = false;
    boolean coupon = false;

    @Override
    protected int getContentResId() {
        return R.layout.activity_ordernew;
    }

    @Override
    public void initView() {
        setTitleText("订单支付");
        mId = getIntent().getBundleExtra("bundle").getString("id");
    }

    @Override
    public void initData() {
        reqOrderDetail();
    }

    private void reqOrderDetail() {
        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setId(mId);
        CommonApiClient.orderDetail(this, dto, new CallBack<OrderDetailResult>() {
            @Override
            public void onSuccess(OrderDetailResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("获取订单详情成功");
                    setResult(result);

                }

            }
        });
    }

    private void setResult(OrderDetailResult result) {
        data = result.getData();
        total = data.getTotalFee();
        mTvProject.setText(data.getServiceName());
        mTvName.setText(data.getCustName());
        mTvTel.setText(data.getPhone());
        mTvAddress.setText(data.getAddress());
        mTvMoney.setText(total);
        mTvOne.setText("服务费： "+data.getHomeVisitFee());
        mTvTwo.setText("材料费： "+data.getMaterialFee());
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
                if(mPlaceCbQb.isChecked()){
                    type = "HomeService";
                    reqPayment();
                }
                else if(mPlaceCbWx.isChecked()){
                    type = "";
                    reqPayment();
                }
                else if(mPlaceCbZfb.isChecked()){
                    type = "";
                    reqPayment();
                }
                break;
            case R.id.rl_jf:
                Bundle bundle = new Bundle();
                bundle.putString("total",mTvMoney.getText().toString());
                OrderUiGoto.gotoIntegral(this,bundle);
                break;
            case R.id.rl_yhj:
                Bundle b = new Bundle();
                b.putString("ServiceId",data.getServiceId());
                b.putString("total",mTvMoney.getText().toString());
                UIHelper.showFragmentResult(this, SimplePage.SERVICE_COUPON,b);
                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
        }
    }

    private void reqPayment() {
        NewPaymentDTO dto = new NewPaymentDTO();
        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();
        dto.setTimestamp(time);
        dto.setRandom(random);
        dto.setAccessToken(AppContext.get("accessToken",""));
        dto.setUid(AppContext.get("uid",""));
        dto.setSign(AppContext.get("uid","")+time+random);
        dto.setDealId(data.getOrderId());
        dto.setDealType(type);
        if(integral){
            dto.setPoints(mPoint);
        }
        if(coupon){
            dto.setCouponids(mCoupon);
        }
        CommonApiClient.newPayment(this, dto, new CallBack<IntegralResult>() {
            @Override
            public void onSuccess(IntegralResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("订单之钱包支付成功");

                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == OrderUiGoto.INTEGAL_REQUEST) {

            if(TextUtils.isEmpty(AppContext.get("Integral",""))){
                return;
            }else {
                integral = true;
                String tol = AppContext.get("Integral","");
                String rule = AppContext.get("rule","");
                double t1 = Double.parseDouble(mTvMoney.getText().toString());
                double t2 = Double.parseDouble(tol);
                double t3 = Double.parseDouble(rule);
                LogUtils.e("t2---",""+t2);
                LogUtils.e("t3---",""+t3);
                mPoint = String.valueOf(t2*t3);
                LogUtils.e("mPoint--",""+mPoint);
                if(t1>t2){
                    mTvMoney.setText(String.valueOf(t1-t2));
                }else {
                    mTvMoney.setText("0");
                }
            }

        }

        if (requestCode == UIHelper.SEND_REQUEST) {
            if(TextUtils.isEmpty(AppContext.get("couponMenoy",""))){
                return;
            }else {
            coupon = true;
            mCoupon = AppContext.get("couponMenoy","");
            double t1 = Double.parseDouble(mTvMoney.getText().toString());
            double t2 = Double.parseDouble(mCoupon);
            if(t1>t2){
                mTvMoney.setText(String.valueOf(t1-t2));
            }else {
                mTvMoney.setText("0");
            }
            }
        }




    }
}
