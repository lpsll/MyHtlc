package com.autodesk.easyhome.shejijia.order.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.MainActivity;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.alipay.PayResult;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.base.SimplePage;
import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.DialogUtils;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.common.utils.UIHelper;
import com.autodesk.easyhome.shejijia.home.dto.WxDTO;
import com.autodesk.easyhome.shejijia.home.dto.ZfbDTO;
import com.autodesk.easyhome.shejijia.home.entity.WxEntity;
import com.autodesk.easyhome.shejijia.home.entity.WxResult;
import com.autodesk.easyhome.shejijia.mine.entity.UserDetailResult;
import com.autodesk.easyhome.shejijia.order.OrderUiGoto;
import com.autodesk.easyhome.shejijia.order.dto.NewPaymentDTO;
import com.autodesk.easyhome.shejijia.order.dto.OrderDetailDTO;
import com.autodesk.easyhome.shejijia.order.entity.IntegralResult;
import com.autodesk.easyhome.shejijia.order.entity.OrderDetailResult;
import com.autodesk.easyhome.shejijia.order.entity.OrderDetailsEntity;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

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
    private String total;
    private String type;
    private String mPoint;
    private String mCoupon;
    private String money;
    boolean integral = false;
    boolean coupon = false;
    private String mT1;
    private String mT2;
    int tot,inte;
    private static final int RQF_PAY = 1;
    private String infomation,mWxKey,mOrderId;
    double balance;

    @Override
    protected int getContentResId() {
        return R.layout.activity_ordernew;
    }

    @Override
    public void initView() {
        setTitleText("订单支付");
        tot =0;
        tot =0;
        mId = getIntent().getBundleExtra("bundle").getString("id");
    }

    @Override
    public void initData() {
        reqOrderDetail();//订单详情
        getUserDetail();//获取余额
    }

    private void getUserDetail() {
        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();

        BaseDTO baseDTO = new BaseDTO();
        baseDTO.setAccessToken(AppContext.get("accessToken", ""));
        baseDTO.setUid(AppContext.get("uid", ""));
        baseDTO.setTimestamp(time);
        baseDTO.setRandom(random);
        baseDTO.setSign(AppContext.get("uid", "") + time + random);

        CommonApiClient.getUserDetail(this, baseDTO, new CallBack<UserDetailResult>() {
            @Override
            public void onSuccess(UserDetailResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("获取用户信息成功=====" + result.getData().toString());

                    balance = result.getData().getBalance();


                }
            }
        });
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
        mOrderId = data.getOrderId();
        total = data.getTotalFee();
        mTvProject.setText(data.getServiceName());
        mTvName.setText(data.getCustName());
        mTvTel.setText(data.getPhone());
        mTvAddress.setText(data.getAddress());
        if(AppContext.get("statu",false)){
            mTvMoney.setText(data.getTotalAmount());
            mTvOne.setVisibility(View.GONE);
            mTvTwo.setVisibility(View.GONE);
        }else {
            mTvMoney.setText(total);
            mTvOne.setVisibility(View.VISIBLE);
            mTvTwo.setVisibility(View.VISIBLE);
            mTvOne.setText("服务费： "+data.getHomeVisitFee());
            mTvTwo.setText("材料费： "+data.getMaterialFee());
        }

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
                    mTjBtn.setEnabled(false);
                    if(AppContext.get("statu",false)){
                        type = "SerivceBook";
                    }else {
                        type = "HomeService";
                    }

                    if(balance<Double.parseDouble(mTvMoney.getText().toString())){
                        DialogUtils.showPrompt(this, "提示","您的余额不足，钱包无法支付！", "知道了");
                    }else {
                        reqPayment();//钱包支付
                    }

                }
                else if(mPlaceCbWx.isChecked()){
                    if(mTvMoney.getText().toString().equals("0")||mTvMoney.getText().toString().equals("0.00")){
                        DialogUtils.showPrompt(this, "提示","您的付款金额为0，只能使用钱包支付！", "知道了");
                    }
                    else {
                        mTjBtn.setEnabled(false);
                        if(AppContext.get("statu",false)){
                            type = "SerivceBook";
                        }else {
                            type = "HomeService";
                        }
                        reqWxPayment();//微信预支付
                    }

                }
                else if(mPlaceCbZfb.isChecked()){
                    if(mTvMoney.getText().toString().equals("0")||mTvMoney.getText().toString().equals("0.00")){
                        DialogUtils.showPrompt(this, "提示","您的付款金额为0，只能使用钱包支付！", "知道了");
                    }
                    else {

                        mTjBtn.setEnabled(false);
                        if(AppContext.get("statu",false)){
                            type = "SerivceBook";
                        }else {
                            type = "HomeService";
                        }
                    reqZfbPayment();//支付宝预支付
                    }
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
            dto.setCouponids(mCoupon.replace(".00",""));
        }
        CommonApiClient.newPayment(this, dto, new CallBack<IntegralResult>() {
            @Override
            public void onSuccess(IntegralResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("订单之钱包支付成功");
                    Intent intent2 = new Intent(OrderNewPaymentActivity.this, MainActivity.class);
                    intent2.putExtra("tag",1);
                    OrderNewPaymentActivity.this.startActivity(intent2);

                }

            }
        });
    }

    private void reqWxPayment() {
        WxDTO dto = new WxDTO();
        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();
        dto.setTimestamp(time);
        dto.setRandom(random);
        dto.setAccessToken(AppContext.get("accessToken", ""));
        dto.setUid(AppContext.get("uid", ""));
        dto.setSign(AppContext.get("uid", "") + time + random);

        dto.setDealId(mOrderId);
        dto.setDealType(type);
        dto.setTradetype("APP");
        dto.setCode("");

        CommonApiClient.wx(this, dto, new CallBack<WxResult>() {
            @Override
            public void onSuccess(WxResult result) {
//                if (AppConfig.NOTHING.equals(result.getCode())) {
//                    DialogUtils.showPrompt(OrderPaymentActivity.this, "提示",result.getMsg(), "知道了");
//
//                }
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("微信预支付成功");
                    reqWx(result);

                }

            }
        });
    }

    IWXAPI msgApi;
    WxEntity mData;
    private void reqWx(WxResult result) {
        mData = result.getData();
        AppContext.set("wx_appId",mData.getAppId());
        msgApi = WXAPIFactory.createWXAPI(this, mData.getAppId());
        msgApi.registerApp(mData.getAppId());

        if (msgApi != null) {
            if (msgApi.isWXAppInstalled()) {
//                String characterEncoding = "UTF-8";
//                mWxKey = data.getNonceStr();
                PayReq req = new PayReq();
                req.appId = mData.getAppId();
                req.partnerId = mData.getPartnerId();
                req.prepayId = mData.getPrepayId();
                req.packageValue = "Sign=WXPay";
//                String time =  TimeUtils.genTimeStamp();
//                String nonceStr = RandomUtils.generateString(10);
                req.nonceStr = mData.getNonceStr();;
                req.timeStamp = mData.getTimeStamp();
//                String str = "appid="+AppConfig.Wx_App_Id
//                        +"&noncestr="+nonceStr
//                        +"&package="+"Sign=WXPay"
//                        +"&partnerid="+data.getPartnerId()
//                        +"&prepayid="+data.getPrepayId()
//                        +"&timestamp="+time;
//                String sing = str.trim().toString()+"&key="+mWxKey;
//                LogUtils.e("sing---------",sing);
                req.sign = mData.getSign();
                LogUtils.e("appId--",mData.getAppId());
                LogUtils.e("partnerId--",mData.getPartnerId());
                LogUtils.e("prepayId--",mData.getPrepayId());
                LogUtils.e("packageValue--","Sign=WXPay");
                LogUtils.e("nonceStr--",mData.getNonceStr());
                LogUtils.e("timeStamp--",mData.getTimeStamp());
                LogUtils.e("sign--",mData.getSign());
                msgApi.sendReq(req);
                mTjBtn.setEnabled(true);
            }
        }

    }

    private void reqZfbPayment() {
        ZfbDTO dto = new ZfbDTO();
        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();
        dto.setTimestamp(time);
        dto.setRandom(random);
        dto.setAccessToken(AppContext.get("accessToken", ""));
        dto.setUid(AppContext.get("uid", ""));
        dto.setSign(AppContext.get("uid", "") + time + random);

        dto.setDealId(mOrderId);
        dto.setDealType(type);
        dto.setTradetype("APP");

        CommonApiClient.zfb(this, dto, new CallBack<IntegralResult>() {
            @Override
            public void onSuccess(IntegralResult result) {
//                if (AppConfig.NOTHING.equals(result.getCode())) {
//                    DialogUtils.showPrompt(OrderPaymentActivity.this, "提示",result.getMsg(), "知道了");
//
//                }
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("支付宝支付成功");
                    infomation = result.getData();
                    LogUtils.e("infomation---",""+infomation);
                    reqAlipayPay();//支付宝支付

                }

            }
        });
    }

    private void reqAlipayPay() {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(OrderNewPaymentActivity.this);//支付宝接口了，支付宝现在把很多功能都封装
                String result = alipay.pay(infomation,true);//返回的结果
                LogUtils.e("result-----------", "result = " + result);
                Message msg = new Message();
                msg.what = RQF_PAY;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }

    Handler mHandler = new Handler() {

        public void handleMessage(android.os.Message msg) {

            PayResult payResult = new PayResult((String) msg.obj);
            /**
             * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
             * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
             * docType=1) 建议商户依赖异步通知
             */
            LogUtils.e("payResult---",""+payResult);
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
            LogUtils.e("resultInfo---",""+resultInfo);
            String resultStatus = payResult.getResultStatus();
            LogUtils.e("resultStatus----",""+resultStatus);
            // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
            switch (msg.what) {
                case RQF_PAY:
                    if (TextUtils.equals(resultStatus, "9000")) {
                        LogUtils.e("RQF_PAY---","9000"+"支付宝支付成功");
                        Intent intent2 = new Intent(OrderNewPaymentActivity.this, MainActivity.class);
                        intent2.putExtra("tag",1);
                        OrderNewPaymentActivity.this.startActivity(intent2);
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(OrderNewPaymentActivity.this, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else if(TextUtils.equals(resultStatus, "6001")){
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(OrderNewPaymentActivity.this, "用户取消订单",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else if(TextUtils.equals(resultStatus, "6002")){
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(OrderNewPaymentActivity.this, "网络连接错误",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else if(TextUtils.equals(resultStatus, "4000")){
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(OrderNewPaymentActivity.this, "订单支付失败",
                                    Toast.LENGTH_SHORT).show();
                        }
                        mTjBtn.setEnabled(true);
                    }

                    break;
                default:
                    break;
            }
        };
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == OrderUiGoto.INTEGAL_REQUEST) {

            if(TextUtils.isEmpty(AppContext.get("Integral",""))){
                return;
            }else {
                integral = true;
                if(inte==0){
                    inte=1;
                    money = AppContext.get("Integral","");
                    String rule = AppContext.get("rule","");
                    double t1 = Double.parseDouble(mTvMoney.getText().toString());
                    double t2 = Double.parseDouble(money);
                    double t3 = Double.parseDouble(rule);
                    mPoint = String.valueOf(t2*t3);
                    if(t1>t2){
                        mTvMoney.setText(String.valueOf(t1-t2));
                    }else {
                        mTvMoney.setText("0");
                    }
                }else {
                    double t1 = Double.parseDouble(mTvMoney.getText().toString())+Double.parseDouble(money);
                    money = AppContext.get("Integral","");
                    double t2 = Double.parseDouble(money);
                    String rule = AppContext.get("rule","");
                    double t3 = Double.parseDouble(rule);
                    mPoint = String.valueOf(t2*t3);
                    if(t1>t2){
                        mTvMoney.setText(String.valueOf(t1-t2));
                    }else {
                        mTvMoney.setText("0");
                    }

                }

            }

        }

        if (requestCode == UIHelper.SEND_REQUEST) {
            if(TextUtils.isEmpty(AppContext.get("couponMenoy",""))){
                return;
            }else {
                coupon = true;
                if(tot==0){
                    tot =1;
                    mCoupon = AppContext.get("couponMenoy","");
                    double t1 = Double.parseDouble(mTvMoney.getText().toString());
                    double t2 = Double.parseDouble(mCoupon);
                    if(t1>t2){
                        mT1 = String.valueOf(t1-t2);
                        mTvMoney.setText(mT1);
                    }else {
                        mTvMoney.setText("0");
                    }
                }else {
                    double d1 = Double.parseDouble(mTvMoney.getText().toString())+Double.parseDouble(mCoupon);
                    mCoupon =AppContext.get("couponMenoy","");
                    double d2 = Double.parseDouble(mCoupon);
                    if(d1>d2){
                        mT2 = String.valueOf(d1-d2);
                        mTvMoney.setText(mT2);
                    }else {
                        mTvMoney.setText("0");
                    }
                }

            }
        }




    }
}
