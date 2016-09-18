package com.autodesk.easyhome.shejijia.campaign.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.MainActivity;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.alipay.PayResult;
import com.autodesk.easyhome.shejijia.campaign.entity.ZfbTopUpEntity;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.DialogUtils;
import com.autodesk.easyhome.shejijia.common.utils.EditInputFilter;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.StringUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.common.utils.ToastUtils;
import com.autodesk.easyhome.shejijia.home.dto.WxDTO;
import com.autodesk.easyhome.shejijia.home.dto.ZfbDTO;
import com.autodesk.easyhome.shejijia.home.entity.WxEntity;
import com.autodesk.easyhome.shejijia.home.entity.WxResult;
import com.autodesk.easyhome.shejijia.mine.dto.zfbTopUpDTO;
import com.autodesk.easyhome.shejijia.order.entity.IntegralResult;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 充值页面
 */
public class TopUpActivity extends BaseTitleActivity {

    @Bind(R.id.et_top_up_chongzhi)
    EditText etTopUpChongzhi;
    @Bind(R.id.tv_top_up_ok)
    TextView tvTopUpOk;
    @Bind(R.id.tv_top_up_chongzhi)
    TextView tvTopUpChongzhi;
    @Bind(R.id.cb_topup_weixin)
    CheckBox cbTopupWX;
    @Bind(R.id.cb_topup_zhifubao)
    CheckBox cbTopupZFB;
    @Bind(R.id.rl_weixin)
    RelativeLayout rlWeixin;
    @Bind(R.id.rl_zhifubao)
    RelativeLayout rlZhifubao;

    private Intent mIntent;
    private String typeForTopUp; //判断是否是用户输入充值金额
    private String moneyForUserInput; //用户输入的充值金额


    @Override
    public void initView() {
        setTitleText("充值");
        //设置默认的充值方式
        cbTopupWX.setChecked(true);
        cbTopupZFB.setChecked(false);

        mIntent = getIntent();
        typeForTopUp = mIntent.getStringExtra("TypeForTopUp");
        if (!TextUtils.isEmpty(typeForTopUp)) {
            //当是固定的金额时
            if ("fixed".equals(typeForTopUp)) {
                tvTopUpChongzhi.setVisibility(View.VISIBLE);
                etTopUpChongzhi.setVisibility(View.GONE);

                moneyForUserInput = mIntent.getStringExtra("amount");
                tvTopUpChongzhi.setText(moneyForUserInput);
                //当需要用户输入金额时
            } else if ("WriteForUser".equals(typeForTopUp)) {
                tvTopUpChongzhi.setVisibility(View.GONE);
                etTopUpChongzhi.setVisibility(View.VISIBLE);
            }
        }
        setEditTextFilter();
        etTopUpChongzhi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String str = etTopUpChongzhi.getText().toString().trim();
//                if (str.indexOf('0') == 0) {
//                    Toast.makeText(TopUpActivity.this, "首位不能为0", Toast.LENGTH_SHORT).show();
//                    etTopUpChongzhi.setText("");
//                }
                if (str.indexOf('.') == 0) {
//                    Toast.makeText(TopUpActivity.this, "首位不能为.", Toast.LENGTH_SHORT).show();
                    etTopUpChongzhi.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void initData() {
    }


    @Override
    protected int getContentResId() {
        return R.layout.activity_top_up;
    }


    @OnClick({R.id.tv_top_up_ok, R.id.cb_topup_weixin, R.id.cb_topup_zhifubao, R.id.rl_weixin, R.id.rl_zhifubao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cb_topup_weixin:  //微信支付
                cbTopupWX.setChecked(true);
                cbTopupZFB.setChecked(false);
                break;
            case R.id.cb_topup_zhifubao: //支付宝支付
                cbTopupWX.setChecked(false);
                cbTopupZFB.setChecked(true);
                break;
            case R.id.tv_top_up_ok: //确定按钮 确定支付方式

                topUpEnsure();

                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
            case R.id.rl_weixin:
                cbTopupWX.setChecked(true);
                cbTopupZFB.setChecked(false);
                break;
            case R.id.rl_zhifubao:
                cbTopupWX.setChecked(false);
                cbTopupZFB.setChecked(true);
                break;
        }
    }


    /**
     * 确认充值
     */
    public void topUpEnsure() {
        if ("WriteForUser".equals(typeForTopUp)) {
            moneyForUserInput = etTopUpChongzhi.getText().toString().trim();
            moneyForUserInput = StringUtils.addTwoZero(moneyForUserInput);
            etTopUpChongzhi.setText(moneyForUserInput);

            if (TextUtils.isEmpty(moneyForUserInput)) {
                ToastUtils.showShort(TopUpActivity.this, "请输入充值金额！");
                return;
            }
            if (Double.parseDouble(moneyForUserInput) <= 0) {
                ToastUtils.showShort(TopUpActivity.this, "充值金额必须大于0！");
                return;
            }

            if (cbTopupWX.isChecked()) {
//                ToastUtils.showShort(TopUpActivity.this, "支付方式：微信支付,金额：" + moneyForUserInput);
                wxTopUp();

            } else if (cbTopupZFB.isChecked()) {
//                ToastUtils.showShort(TopUpActivity.this, "支付方式：支付宝支付,金额：" + moneyForUserInput);

                //调用支付宝充值的接口
                zfbTopUp();

            } else {
                ToastUtils.showShort(TopUpActivity.this, "未选择支付方式！");
            }
        }

        if ("fixed".equals(typeForTopUp)) {
            moneyForUserInput = tvTopUpChongzhi.getText().toString();

            if (cbTopupWX.isChecked()) {
//                ToastUtils.showShort(TopUpActivity.this, "支付方式：微信支付,金额：" + moneyForUserInput);

                wxTopUp();

            } else if (cbTopupZFB.isChecked()) {
//                ToastUtils.showShort(TopUpActivity.this, "支付方式：支付宝支付,金额：" + moneyForUserInput);

                //调用支付宝充值的接口
                zfbTopUp();

            } else {
                ToastUtils.showShort(TopUpActivity.this, "未选择支付方式！");
            }
        }


    }


    private String idealId;  //钱包支付接口返回，支付宝预支付要传回，订单号

    /**
     * 支付宝充值钱包接口
     * "accessToken": "",
     * "uid": "",
     * "timestamp": "",
     * "random": "",
     * "amount": 0,
     * "sign": ""
     */
    private void zfbTopUp() {

        zfbTopUpDTO dto = new zfbTopUpDTO();
        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();

        dto.setTimestamp(time);
        dto.setRandom(random);
        dto.setAccessToken(AppContext.get("accessToken", ""));
        dto.setUid(AppContext.get("uid", ""));
        dto.setSign(AppContext.get("uid", "") + time + random);
        double amount = Double.parseDouble(moneyForUserInput);
        dto.setAmount(amount);
//      dto.setAmount(0.01);
        CommonApiClient.zfbTopUp(this, dto, new CallBack<ZfbTopUpEntity>() {
            @Override
            public void onSuccess(ZfbTopUpEntity result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("钱包充值接口成功=========" + result.getMsg());
                    idealId = result.getData();
                    LogUtils.e("idealId---", "" + idealId);

//                    调支付宝预支付接口
                    reqZfbPayment();

                }
            }
        });
    }


    private void wxTopUp() {

        zfbTopUpDTO dto = new zfbTopUpDTO();
        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();

        dto.setTimestamp(time);
        dto.setRandom(random);
        dto.setAccessToken(AppContext.get("accessToken", ""));
        dto.setUid(AppContext.get("uid", ""));
        dto.setSign(AppContext.get("uid", "") + time + random);
        double amount = Double.parseDouble(moneyForUserInput);
        dto.setAmount(amount);



        CommonApiClient.zfbTopUp(this, dto, new CallBack<ZfbTopUpEntity>() {
            @Override
            public void onSuccess(ZfbTopUpEntity result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("钱包充值接口成功=========" + result.getMsg());
                    idealId = result.getData();
                    LogUtils.e("idealId---", "" + idealId);

                    reqWxPayment();


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

        dto.setDealId(idealId);
        dto.setDealType("RECHARGE");
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
    WxEntity data;

    private void reqWx(WxResult result) {
        data = result.getData();
        AppContext.set("wx_appId", data.getAppId());
        msgApi = WXAPIFactory.createWXAPI(this, data.getAppId());
        msgApi.registerApp(data.getAppId());

        if (msgApi != null) {
            if (msgApi.isWXAppInstalled()) {
//                String characterEncoding = "UTF-8";
//                mWxKey = data.getNonceStr();
                PayReq req = new PayReq();
                req.appId = data.getAppId();
                req.partnerId = data.getPartnerId();
                req.prepayId = data.getPrepayId();
                req.packageValue = "Sign=WXPay";
//                String time =  TimeUtils.genTimeStamp();
//                String nonceStr = RandomUtils.generateString(10);
                req.nonceStr = data.getNonceStr();
                ;
                req.timeStamp = data.getTimeStamp();
//                String str = "appid="+AppConfig.Wx_App_Id
//                        +"&noncestr="+nonceStr
//                        +"&package="+"Sign=WXPay"
//                        +"&partnerid="+data.getPartnerId()
//                        +"&prepayid="+data.getPrepayId()
//                        +"&timestamp="+time;
//                String sing = str.trim().toString()+"&key="+mWxKey;
//                LogUtils.e("sing---------",sing);
                req.sign = data.getSign();
                LogUtils.e("appId--", data.getAppId());
                LogUtils.e("partnerId--", data.getPartnerId());
                LogUtils.e("prepayId--", data.getPrepayId());
                LogUtils.e("packageValue--", "Sign=WXPay");
                LogUtils.e("nonceStr--", data.getNonceStr());
                LogUtils.e("timeStamp--", data.getTimeStamp());
                LogUtils.e("sign--", data.getSign());
                msgApi.sendReq(req);
            }
        }

    }

    /**
     * 设置editText的过滤
     */
    private void setEditTextFilter() {
        InputFilter[] filters = {new EditInputFilter(this)};
        etTopUpChongzhi.setFilters(filters);
    }


    /**
     * 支付宝支付相关
     */
    Handler mHandler = new Handler() {

        public void handleMessage(android.os.Message msg) {

            PayResult payResult = new PayResult((String) msg.obj);
            /**
             * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
             * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
             * docType=1) 建议商户依赖异步通知
             */
            LogUtils.e("payResult---", "" + payResult);
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
            LogUtils.e("resultInfo---", "" + resultInfo);
            String resultStatus = payResult.getResultStatus();
            LogUtils.e("resultStatus----", "" + resultStatus);
            // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
            switch (msg.what) {
                case RQF_PAY:
                    if (TextUtils.equals(resultStatus, "9000")) {
                        LogUtils.e("RQF_PAY---", "9000" + "支付宝支付成功");
                        setResult(1009);
                        //发送广播给我的页面更新数据
                        sendBroadcast(new Intent(AppConfig.TOPUP_RECIVER_ACTION));

                        //跳转到个人中心
                        ToastUtils.showShort(TopUpActivity.this,"充值成功");
                        DialogUtils.showPromptListen(TopUpActivity.this, "提示","充值成功！", "知道了",listener);

//                       new AlertDialog.Builder(TopUpActivity.this).setTitle("提示").setMessage("充值成功！").setPositiveButton("知道了", new DialogInterface.OnClickListener() {
//                           @Override
//                           public void onClick(DialogInterface dialog, int which) {
//
//                           }
//                       }).show();



                        finish();
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(TopUpActivity.this, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.equals(resultStatus, "6001")) {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(TopUpActivity.this, "用户取消订单",
                                    Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.equals(resultStatus, "6002")) {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(TopUpActivity.this, "网络连接错误",
                                    Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.equals(resultStatus, "4000")) {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(TopUpActivity.this, "订单支付失败",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    break;
                default:
                    break;
            }
        }

        ;
    };


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent2 = new Intent(TopUpActivity.this, MainActivity.class);
            intent2.putExtra("tag",3);
            TopUpActivity.this.startActivity(intent2);
        }
    };

    private static final int RQF_PAY = 1;
    private String infomation;  //调用支付宝所需信息

    private void reqAlipayPay() {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(TopUpActivity.this);//支付宝接口了，支付宝现在把很多功能都封装
                String result = alipay.pay(infomation, true);//返回的结果
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


    /**
     * 支付宝预支付接口
     * accessToken = "496e3cfe-ac43-4b40-a430-a23abb7343eb";
     * dealId = 37;
     * dealType = RECHARGE;
     * random = 3044;
     * sign = a28e761b5c1f008cbb64fa57c8010371;
     * timestamp = 1472996446540;
     * tradetype = APP;
     * uid = 13020017428;
     */
    private void reqZfbPayment() {
        ZfbDTO dto = new ZfbDTO();
        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();
        dto.setTimestamp(time);
        dto.setRandom(random);
        dto.setAccessToken(AppContext.get("accessToken", ""));
        dto.setUid(AppContext.get("uid", ""));
        dto.setSign(AppContext.get("uid", "") + time + random);

        dto.setDealId(idealId);
        dto.setDealType("RECHARGE");
        dto.setTradetype("APP");


        CommonApiClient.zfb(this, dto, new CallBack<IntegralResult>() {
            @Override
            public void onSuccess(IntegralResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("支付宝支付成功");
                    infomation = result.getData();
                    LogUtils.e("infomation---", "" + infomation);
                    reqAlipayPay();//支付宝支付

                }
            }
        });
    }
}
