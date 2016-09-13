package com.autodesk.easyhome.shejijia.register.activity;

import android.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;
import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.PhoneUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.common.utils.ToastUtils;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.autodesk.easyhome.shejijia.mine.view.TimeCountUtil;
import com.autodesk.easyhome.shejijia.register.dto.ForgetPwdDTO;
import com.autodesk.easyhome.shejijia.register.entity.SmsVerifyEntity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 获取验证码倒计时
 */
public class ForgetPwdActivity extends BaseTitleActivity {

    @Bind(R.id.et_forgetpwd_phone)
    EditText etForgetpwdPhone;
    @Bind(R.id.TimeButton_forgetpwd)
    Button TimeButtonForgetpwd;
    @Bind(R.id.et_forgetpwd_code)
    EditText etForgetpwdCode;
    @Bind(R.id.et_forgetpwd_pwd)
    EditText etForgetpwdPwd;
    @Bind(R.id.et_forgetpwd_pwd_again)
    EditText etForgetpwdPwdAgain;
    @Bind(R.id.tv_ok)
    TextView tvOk;

    TimeCountUtil timeCountUtil;
    @Override
    protected int getContentResId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    public void initView() {
        setTitleText("忘记密码");
        //对密码格式验证
//        pwdVerify();
    }

    @Override
    public void initData() {
        timeCountUtil = new TimeCountUtil(this, 60000, 1000, TimeButtonForgetpwd);
    }


    @OnClick({R.id.TimeButton_forgetpwd, R.id.tv_ok})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.TimeButton_forgetpwd:
                //验证电话号码
                boolean isValid = PhoneUtils.isPhoneNumberValid(etForgetpwdPhone.getText().toString());
                if (!isValid) {
                    new AlertDialog.Builder(this).setTitle("温馨提示").setMessage("请输入正确的电话号码!").setPositiveButton("确定", null).show();
                } else {
                    //获取验证码
                    timeCountUtil.start();
                    getSmsVerifyCode();
                }

                break;
            case R.id.tv_ok:
                //验证
                dataVerify();
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
        dto.setUid(etForgetpwdPhone.getText().toString());
        dto.setSign(etForgetpwdPhone.getText().toString() + time + random);
        CommonApiClient.verifyCode(this, dto, new CallBack<SmsVerifyEntity>() {
            @Override
            public void onSuccess(SmsVerifyEntity result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("获取验证码成功");
                    LogUtils.e("获取验证码成功---------" + result.getData().toString());
                }
            }
        });
    }

    /**
     * 对密码格式验证
     */
    private void pwdVerify() {
        etForgetpwdPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                } else {
                    String pwd = etForgetpwdPwd.getText().toString();
                    String reg = "^[a-zA-Z0-9]{6,20}$";
                    boolean isMatches = pwd.matches(reg);
                    if (!isMatches) {
                        new AlertDialog.Builder(ForgetPwdActivity.this).setTitle("温馨提示").setMessage("密码格式为6位以上字母或数字!").setPositiveButton("确定", null).show();
                        etForgetpwdPwd.setText("");
                        etForgetpwdPwd.setFocusable(true);
                    }
                }
            }
        });
    }

    /**
     * 验证两次密码是否一致
     */
    private void dataVerify() {
        String phone = etForgetpwdPhone.getText().toString().trim();
        String pwd = etForgetpwdPwd.getText().toString().trim();
        String pwdAgain = etForgetpwdPwdAgain.getText().toString().trim();
        //手机号码格式验证
        boolean valid = PhoneUtils.isPhoneNumberValid(phone);
        if (!valid) {
            new AlertDialog.Builder(this).setTitle("温馨提示").setMessage("请输入正确的电话号码!").setPositiveButton("确定", null).show();
            return;
        }

        //密码非空验证
        if (TextUtils.isEmpty(pwd)) {
            new AlertDialog.Builder(this).setTitle("温馨提示").setMessage("密码不能为空!").setPositiveButton("确定", null).show();
            return;
        }

        //两次密码一致验证
        if (!pwd.equals(pwdAgain)) {
            //进行注册操作
            new AlertDialog.Builder(this).setTitle("温馨提示").setMessage("两次密码不一致!").setPositiveButton("确定", null).show();
            return;
        }
        //设置新密码操作
        setNewPwd();
    }

    /**
     * 设置新密码操作
     * 忘记密码接口
     * uid:用户ID，默认为手机号码
     * timestamp:当前时间戳
     * random:随机数
     * smsverifycode:短信验证码
     * password:新密码，MD5加密
     * sign:签名【生成规则 uid+password(MD5)+timestamp+random 后md5加密串】
     */
    private void setNewPwd() {
        String phone = etForgetpwdPhone.getText().toString().trim();
        String code = etForgetpwdCode.getText().toString().trim();
        String pwd = etForgetpwdPwd.getText().toString().trim();
        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();

        ForgetPwdDTO forgetPwdDTO = new ForgetPwdDTO();
        forgetPwdDTO.setUid(phone);
        forgetPwdDTO.setTimestamp(time);
        forgetPwdDTO.setRandom(random);
        forgetPwdDTO.setSmsverifycode(code);
        forgetPwdDTO.setPassword(pwd);
        forgetPwdDTO.setSign(phone + forgetPwdDTO.getPassword() + time + random);

        CommonApiClient.forgetPwd(this, forgetPwdDTO, new CallBack<BaseEntity>() {
            @Override
            public void onSuccess(BaseEntity result) {
                LogUtils.d("result===="+result.getMsg());
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("重新设置密码成功");
                    ToastUtils.showShort(ForgetPwdActivity.this, "重新设置密码成功");
                    LogUtils.e("result---", "" + result);
                    //跳转到登录页面
                    HomeUiGoto.gotoLoginForPwd(ForgetPwdActivity.this);
                    finish();
                }else {
                    ToastUtils.showShort(ForgetPwdActivity.this, "重新设置密码失败");
                }
            }
        });
    }
}