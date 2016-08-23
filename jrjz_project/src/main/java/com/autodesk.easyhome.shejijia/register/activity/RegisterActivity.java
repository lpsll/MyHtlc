package com.autodesk.easyhome.shejijia.register.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.utils.MD5;
import com.autodesk.easyhome.shejijia.common.utils.StringUtils;
import com.autodesk.easyhome.shejijia.mine.view.TimeButton;
import com.autodesk.easyhome.shejijia.register.bean.SmsVerifyCodeBean;
import com.google.gson.Gson;
import com.htlc.jrjz.jrjz_project.R;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends BaseTitleActivity {

    @Bind(R.id.et_register_phone)
    EditText etRegisterPhone;
    @Bind(R.id.et_register_code)
    EditText etRegisterCode;
    @Bind(R.id.et_register_password)
    EditText etRegisterPassword;
    @Bind(R.id.et_register_password_again)
    EditText etRegisterPasswordAgain;
    @Bind(R.id.tv_ok)
    TextView tvOk;
    @Bind(R.id.et_register_login)
    TextView etRegisterLogin;

    @Bind(R.id.TimeButton_register)
    TimeButton TimeButtonRegister;

    String url = "http://101.200.167.130:8080/jrjz-api/api/user/getSmsVerifyCode";
    public static final MediaType JSON=MediaType.parse("application/json; charset=utf-8");

    @Override
    protected int getContentResId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        setTitleText("新用户注册");

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick(R.id.TimeButton_register)
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.TimeButton_register:
                //获取验证码
                getSmsVerifyCode();

                break;
        }

    }

    /**
     * 获取验证码
     */
    private void getSmsVerifyCode() {
        SmsVerifyCodeBean smsVerifyCodeBean = new SmsVerifyCodeBean();
        smsVerifyCodeBean.uid = "13126611508";
        smsVerifyCodeBean.timestamp = System.currentTimeMillis();
        smsVerifyCodeBean.random = StringUtils.getRandom();
        try {
            smsVerifyCodeBean.sign = MD5.md5Encode("13126611508"+smsVerifyCodeBean.timestamp+smsVerifyCodeBean.random);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("TAG","SmsVerifyCodeBean=="+smsVerifyCodeBean.toString());

        OkHttpClient okHttpClient = new OkHttpClient();
//        RequestBody requestBody = new FormBody.Builder()
//                .add("uid", "13126611508")
//                //时间戳
//                .add("timestamp", timestamp + "")
//                .add("random", random + "")
//                .add("sign", sign)
//                .build();

        Gson gson = new Gson();
        RequestBody requestBody = RequestBody.create(JSON, gson.toJson(smsVerifyCodeBean));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("TAG", "failure,register========");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.i("TAG", "succuess,register========" + response.toString());
            }
        });
    }
}
