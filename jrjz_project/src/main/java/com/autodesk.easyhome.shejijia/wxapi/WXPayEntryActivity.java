package com.autodesk.easyhome.shejijia.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.MainActivity;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.utils.DialogUtils;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    // 实现IWXAPIEventHandler接口，微信发送的请求将回调到onReq方法，发送到微信请求的响应结果将回调到onResp方法
    // 在WXEntryActivity中将接收到的intent及实现了IWXAPIEventHandler接口的对象传递给IWXAPI接口的handleIntent方法，示例如下图
    // 当微信发送请求到你的应用，将通过IWXAPIEventHandler接口的onReq方法进行回调，类似的，应用请求微信的响应结果将通过onResp回调


    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        api = WXAPIFactory.createWXAPI(this, AppContext.get("wx_appId",""));
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            LogUtils.e("支付---","errCode----"+resp.errCode+"getType---"+resp.getType() );
            String message = "";
            if (resp.errCode == 0) {
                LogUtils.e("支付成功---",""+resp.errCode);
                message = "支付成功";
                if(AppContext.get("WXFlag","").equals("1")){
                    //跳转到个人中心
//                        ToastUtils.showShort(TopUpActivity.this,"充值成功");
                    DialogUtils.showPromptListen(WXPayEntryActivity.this, "提示","充值成功！", "知道了",listener);

                }else {
                    Intent intent2 = new Intent(WXPayEntryActivity.this, MainActivity.class);
                    intent2.putExtra("tag", 1);
                    WXPayEntryActivity.this.startActivity(intent2);
                }


            } else {
                String error = resp.errStr;
                int errcode = resp.errCode;
                message = "支付失败";
                LogUtils.e("支付失败---","error----"+error+"errcode："+errcode);
            }
            WXPayEntryActivity.this.finish();
            Toast.makeText(WXPayEntryActivity.this, message, Toast.LENGTH_SHORT).show();

        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent2 = new Intent(WXPayEntryActivity.this, MainActivity.class);
            intent2.putExtra("tag",3);
            WXPayEntryActivity.this.startActivity(intent2);
        }
    };
}