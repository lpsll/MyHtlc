package com.autodesk.easyhome.shejijia.register.activity;

import android.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
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
import com.autodesk.easyhome.shejijia.common.utils.PhoneUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.mine.view.TimeButton;
import com.htlc.jrjz.jrjz_project.R;

import butterknife.Bind;
import butterknife.OnClick;

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

        //设置倒计时中间电话号码不可修改
        setTimeButtonEditable();


    }

    /**
     * 设置倒计时中间电话号码不可修改
     */
    private void setTimeButtonEditable() {

        TimeButtonRegister.setmOnTimeOverListener(new TimeButton.OnTimeOverListener() {
            @Override
            public void OnTimeOver(boolean b) {
                Log.i("TAG", "b=====" + b);
                if (b) {
                    //电话号码可编辑状态
                    etRegisterPhone.setInputType(InputType.TYPE_CLASS_NUMBER);
                } else {
                    //电话号码不可编辑状态
                    etRegisterPhone.setInputType(InputType.TYPE_NULL);
                }
            }
        });
    }

    @Override
    public void initData() {

    }


    @OnClick(R.id.TimeButton_register)
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.TimeButton_register:
                //验证电话号码
                boolean isValid = PhoneUtils.isPhoneNumberValid(etRegisterPhone.getText().toString());
                if (!isValid) {
                    TimeButtonRegister.setLenght(0);
                    new AlertDialog.Builder(this).setTitle("请输入正确的电话号码!").setPositiveButton("确定", null).show();
                } else {
                    TimeButtonRegister.setLenght(60 * 1000);
                    //获取验证码
                    getSmsVerifyCode();
                }
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
        dto.setSign(etRegisterPhone.getText().toString() + time + random);
        LogUtils.e("time---", "" + time);
        LogUtils.e("random---", "" + random);
        CommonApiClient.verifyCode(this, dto, new CallBack<BaseEntity>() {
            @Override
            public void onSuccess(BaseEntity result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("获取验证码成功");
                    LogUtils.e("result---", "" + result);

                }

            }
        });
    }
}
