package com.autodesk.easyhome.shejijia.home.activity;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.alipay.PayResult;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.DialogUtils;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.RandomUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.order.dto.NewPaymentDTO;
import com.autodesk.easyhome.shejijia.order.entity.IntegralResult;
import com.autodesk.easyhome.shejijia.order.entity.OrderDetailResult;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 订单支付页
 */
public class OrderPaymentActivity extends BaseTitleActivity {
    @Bind(R.id.tv_project)
    TextView mTvProject;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_tel)
    TextView mTvTel;
    @Bind(R.id.tv_address)
    TextView mTvAddress;
    @Bind(R.id.home_fee)
    TextView mHomeFee;
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
    @Bind(R.id.tj_btn)
    Button mTjBtn;

    private static final int RQF_PAY = 1;
    private String mSelName,mSelPhone,mSelAddress,mPrice,mName,mOrderId;
    private String type;

    @Override
    protected int getContentResId() {
        return R.layout.activity_orderpayment;
    }

    @Override
    public void initView() {
        setTitleText("订单支付");
        mName = getIntent().getBundleExtra("bundle").getString("mName");
        mPrice = getIntent().getBundleExtra("bundle").getString("mPrice");
        mOrderId = getIntent().getBundleExtra("bundle").getString("orderId");
        mSelName = AppContext.get("mSelName","");
        mSelPhone = AppContext.get("mSelPhone","");
        mSelAddress = AppContext.get("mSelAddress","");
        mTvProject.setText(mName);
        mTvName.setText(mSelName);
        mTvTel.setText(mSelPhone);
        mTvAddress.setText(mSelAddress);
        mHomeFee.setText(mPrice);

    }

    @Override
    public void initData() {
        reqWx();//微信支付
        reqAlipayPay();//支付宝支付
    }

    private void reqAlipayPay() {
//        String info = getNewOrderInfo(data);//这个是订单信息
        // 这里根据签名方式对订单信息进行签名
//        String strsign = Rsa.sign(info, Keys.PRIVATE);
//        LogUtils.e("strsign----",""+strsign);
//        try {
//            // 仅需对sign 做URL编码
//            strsign = URLEncoder.encode(strsign, "UTF-8");
//            LogUtils.e("strsign----utf8",""+strsign);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        // 组装好参数
//        final String orderInfo = info + "&sign=\"" +strsign + "\"&" + getSignType();
//        LogUtils.e("orderInfo----",""+orderInfo);
//        Runnable payRunnable = new Runnable() {
//
//            @Override
//            public void run() {
//                // 构造PayTask 对象
//                PayTask alipay = new PayTask(OrderPaymentActivity.this);//支付宝接口了，支付宝现在把很多功能都封装
//                String result = alipay.pay(orderInfo,true);//返回的结果
//                LogUtils.e("result-----------", "result = " + result);
//                Message msg = new Message();
//                msg.what = RQF_PAY;
//                msg.obj = result;
//                mHandler.sendMessage(msg);
//            }
//        };
//        // 必须异步调用
//        Thread payThread = new Thread(payRunnable);
//        payThread.start();

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
                        LogUtils.e("RQF_PAY---","9000");
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(OrderPaymentActivity.this, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else if(TextUtils.equals(resultStatus, "6001")){
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(OrderPaymentActivity.this, "用户取消订单",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else if(TextUtils.equals(resultStatus, "6002")){
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(OrderPaymentActivity.this, "网络连接错误",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else if(TextUtils.equals(resultStatus, "4000")){
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(OrderPaymentActivity.this, "订单支付失败",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    break;
                default:
                    break;
            }
        };
    };
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

    //获得订单信息的方法
//    private String getNewOrderInfo(List<PaypayEntity> data) {
//
//
//        // 签约合作者身份ID
//        String orderInfo = "partner=" + "\"" + data.get(0).getPartnerID() + "\"";
//
//        // 签约卖家支付宝账号
//        orderInfo += "&seller_id=" + "\"" + data.get(0).getSellerAccount()+ "\"";
//
//        // 商户网站唯一订单号
//        orderInfo += "&out_trade_no=" + "\"" + data.get(0).getOrderNum() + "\"";
//
//        // 商品名称
//        orderInfo += "&subject=" + "\"" + data.get(0).getProductName() + "\"";
//
//        // 商品详情
//        orderInfo += "&body=" + "\"" + data.get(0).getProductName() + "\"";
//
////        // 商品金额
//        orderInfo += "&total_fee=" + "\"" + data.get(0).getAmount() + "\"";
//        // 商品金额
////        orderInfo += "&total_fee=" + "\"" + "0.01" + "\"";
//
//        // 服务器异步通知页面路径
//        orderInfo += "&notify_url=" + "\"" + AppConfig.BASE_URL+"/notify_url.aspx" + "\"";
//        // 服务接口名称， 固定值
//        orderInfo += "&service=\"mobile.securitypay.pay\"";
//
//        // 支付类型， 固定值
//        orderInfo += "&payment_type=\"1\"";
//
//        // 参数编码， 固定值
//        orderInfo += "&_input_charset=\"utf-8\"";
//
//        // 设置未付款交易的超时时间
//        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
//        // 取值范围：1m～15d。
//        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
//        // 该参数数值不接受小数点，如1.5h，可转换为90m。
//        orderInfo += "&it_b_pay=\"30m\"";
//
//        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
//        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";
//
//        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
//        orderInfo += "&return_url=\"m.alipay.com\"";
//
//        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
//        // orderInfo += "&paymethod=\"expressGateway\"";
//
//        return orderInfo;
//    }


    IWXAPI msgApi;
    private void reqWx() {
        msgApi = WXAPIFactory.createWXAPI(this, AppConfig.Wx_App_Id);
        msgApi.registerApp(AppConfig.Wx_App_Id);
        if (msgApi != null) {
            if (msgApi.isWXAppInstalled()) {
                String characterEncoding = "UTF-8";
//                mWxKey = data.get(0).getPrivateKey();
                PayReq req = new PayReq();
                req.appId = AppConfig.Wx_App_Id;
//                req.partnerId = data.get(0).getPartnerID();
//                req.prepayId = data.get(0).getPrepayid();
                req.packageValue = "Sign=WXPay";
                String time =  TimeUtils.genTimeStamp();
                String nonceStr = RandomUtils.generateString(10);
                req.nonceStr = nonceStr;;
                req.timeStamp = time;
                String str = "appid="+AppConfig.Wx_App_Id
                        +"&noncestr="+nonceStr
                        +"&package="+"Sign=WXPay"
//                        +"&partnerid="+data.get(0).getPartnerID()
//                        +"&prepayid="+data.get(0).getPrepayid()
                        +"&timestamp="+time;
//                String sing = str.trim().toString()+"&key="+mWxKey;
//                LogUtils.e("sing---------",sing);
//                req.sign = SecurityUtils.MD5(sing.trim().toString());
//                LogUtils.e("sign------------MD5", SecurityUtils.MD5(sing.trim().toString()));
                msgApi.sendReq(req);
            }
        }

    }

    private void reqPayment() {
        NewPaymentDTO dto = new NewPaymentDTO();
        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();
        dto.setTimestamp(time);
        dto.setRandom(random);
        dto.setAccessToken(AppContext.get("accessToken", ""));
        dto.setUid(AppContext.get("uid", ""));
        dto.setSign(AppContext.get("uid", "") + time + random);
        dto.setDealId(mOrderId);
        dto.setDealType(type);
        CommonApiClient.newPayment(this, dto, new CallBack<IntegralResult>() {
            @Override
            public void onSuccess(IntegralResult result) {
                if (AppConfig.NOTHING.equals(result.getCode())) {
                    DialogUtils.showPrompt(OrderPaymentActivity.this, "提示",result.getMsg(), "知道了");

                }
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("钱包支付成功");

                }

            }
        });
    }

    @OnClick({R.id.rl_qb, R.id.rl_wx, R.id.rl_zfb, R.id.tj_btn})
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
                    type = "SerivceBook";
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
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
        }
    }


}
