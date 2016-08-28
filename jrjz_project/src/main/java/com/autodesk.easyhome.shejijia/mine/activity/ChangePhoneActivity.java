package com.autodesk.easyhome.shejijia.mine.activity;

import android.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;
import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.PhoneUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.common.utils.ToastUtils;
import com.autodesk.easyhome.shejijia.mine.dto.ChangePhoneDTO;
import com.autodesk.easyhome.shejijia.mine.view.TimeButton;
import com.autodesk.easyhome.shejijia.register.entity.SmsVerifyEntity;
import com.autodesk.easyhome.shejijia.R;

import butterknife.Bind;
import butterknife.OnClick;

public class ChangePhoneActivity extends BaseTitleActivity {


    @Bind(R.id.et_change_phone_phone)
    EditText etChangePhonePhone;
    @Bind(R.id.TimeButton_mine_changePhone)
    TimeButton TimeButtonMineChangePhone;
    @Bind(R.id.et_change_phone_code)
    EditText etChangePhoneCode;
    @Bind(R.id.tv_change_phone_ok)
    TextView tvChangePhoneOk;

    @Override
    public void initView() {
        setTitleText("换绑手机");


    }

    @Override
    public void initData() {

    }

    @Override
    protected int getContentResId() {
        return R.layout.activity_change_phone;
    }


    @OnClick({R.id.TimeButton_mine_changePhone, R.id.tv_change_phone_ok})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.TimeButton_mine_changePhone:
                //验证电话号码
                boolean isValid = PhoneUtils.isPhoneNumberValid(etChangePhonePhone.getText().toString());
                if (!isValid) {
                    TimeButtonMineChangePhone.setLenght(0);
                    new AlertDialog.Builder(this).setTitle("温馨提示").setMessage("请输入正确的电话号码!").setPositiveButton("确定", null).show();
                } else {
                    TimeButtonMineChangePhone.setLenght(60 * 1000);
                    //获取验证码
                    getSmsVerifyCode();
                }
                break;
            case R.id.tv_change_phone_ok:
                //换绑手机
                changePhone();

                break;
        }
    }

    /**
     * 换绑手机
     *
     * 修改用户信息接口

     accessToken:访问授权码

     uid:用户ID，默认为原手机号码

     timestamp:当前时间戳

     random:随机数

     sign:签名【生成规则uid+timestamp+random 后md5加密串】

     newmobile:新手机号

     smsverifycode:短信验证码
     */
    private void changePhone() {
        final String phone = etChangePhonePhone.getText().toString().trim();
        String code = etChangePhoneCode.getText().toString().trim();
        //验证电话号码
        boolean isValid = PhoneUtils.isPhoneNumberValid(phone);
        if (!isValid) {
            new AlertDialog.Builder(this).setTitle("温馨提示").setMessage("请输入正确的电话号码!").setPositiveButton("确定", null).show();
            return;
        }


        ChangePhoneDTO changePhoneDTO = new ChangePhoneDTO();
        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();

        changePhoneDTO.setAccessToken(AppContext.get("accessToken",""));
        changePhoneDTO.setUid(AppContext.get("uid",""));
        changePhoneDTO.setTimestamp(time);
        changePhoneDTO.setRandom(random);
        changePhoneDTO.setSign(AppContext.get("uid","") +time+ random);
        changePhoneDTO.setNewmobile(phone);
        changePhoneDTO.setSmsverifycode(code);

        CommonApiClient.changePhone(this, changePhoneDTO, new CallBack<BaseEntity>() {
            @Override
            public void onSuccess(BaseEntity result) {
                LogUtils.e("result========" + result.getMsg());
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("换绑手机成功");
                    ToastUtils.showShort(ChangePhoneActivity.this, "换绑手机成功");

                    //保存用户信息
                    AppContext.set("uid", phone);
                    AppContext.set("IS_LOGIN", true);

                    setResult(1001);

                    finish();
                }
            }
        });

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
        dto.setUid(etChangePhonePhone.getText().toString());
        dto.setSign(etChangePhonePhone.getText().toString() + time + random);
        LogUtils.e("time---", "" + time);
        LogUtils.e("random---", "" + random);
        CommonApiClient.verifyCode(this, dto, new CallBack<SmsVerifyEntity>() {
            @Override
            public void onSuccess(SmsVerifyEntity result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("获取验证码成功");
                    LogUtils.e("result---------", "" + result);
                }
            }
        });
    }
}
