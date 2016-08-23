package com.autodesk.easyhome.shejijia.register.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;
import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.common.widget.EmptyLayout;
import com.autodesk.easyhome.shejijia.mine.view.TimeButton;
import com.htlc.jrjz.jrjz_project.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;

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
        BaseDTO dto = new BaseDTO();
        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();
        dto.setRandom(random);
        dto.setTimestamp(time);
        dto.setUid(etRegisterPhone.getText().toString());
        dto.setSign(etRegisterPhone.getText().toString()+time+random);
        LogUtils.e("time---",""+time);
        LogUtils.e("random---",""+random);
        CommonApiClient.verifyCode(this, dto, new CallBack<BaseEntity>() {
            @Override
            public void onSuccess(BaseEntity result) {
                if(AppConfig.SUCCESS.equals(result.getCode())){
                    LogUtils.e("获取验证码成功");

                }

            }
        });
    }
}
