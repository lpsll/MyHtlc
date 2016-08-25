package com.autodesk.easyhome.shejijia.login.activity;

import android.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.common.utils.ToastUtils;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.autodesk.easyhome.shejijia.login.dto.LoginDTO;
import com.autodesk.easyhome.shejijia.login.entity.LoginEntity;
import com.htlc.jrjz.jrjz_project.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 密码登录页面
 */
public class LoginForPwdActivity extends BaseTitleActivity {

    @Bind(R.id.et_login_phone)
    EditText etLoginPhone;
    @Bind(R.id.et_login_pwd)
    EditText etLoginPwd;
    @Bind(R.id.et_login_forgetpwd)
    TextView etLoginForgetpwd;
    @Bind(R.id.tv_ok)
    TextView tvOk;
    @Bind(R.id.tv_login_register)
    TextView tvLoginRegister;
    @Bind(R.id.tv_login_to_code_login)
    TextView tvLoginToCodeLogin;

    @Override
    protected int getContentResId() {
        return R.layout.activity_login_for_pwd;
    }

    @Override
    public void initView() {
        setTitleText("登录");

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.et_login_forgetpwd, R.id.tv_ok, R.id.tv_login_register, R.id.tv_login_to_code_login})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.et_login_forgetpwd:
                //跳转到忘记密码页面
                HomeUiGoto.gotoForgetPwd(LoginForPwdActivity.this);
                finish();
                break;
            case R.id.tv_ok:
                //登录
                //判空
                String pwd = etLoginForgetpwd.getText().toString().trim();
                if(TextUtils.isEmpty(etLoginPhone.getText().toString().trim())) {
                    new AlertDialog.Builder(LoginForPwdActivity.this).setMessage("请输入用户名").setPositiveButton("确定",null).show();
                    break;
                }
                if(TextUtils.isEmpty(etLoginPwd.getText().toString().trim())) {
                    new AlertDialog.Builder(LoginForPwdActivity.this).setMessage("请输入密码").setPositiveButton("确定",null).show();
                    break;
                }

                login();


                break;
            case R.id.tv_login_register:
                //跳转到用户注册页面
                HomeUiGoto.gotoRegister(this);
                finish();
                break;

            case R.id.tv_login_to_code_login:
                //跳转到验证码登录页面
                HomeUiGoto.gotoLoginForCode(LoginForPwdActivity.this);
                finish();
                break;
        }
    }

    /**
     * 登录操作
     */
    private void login() {
        /**
         * uid：用户ID，默认为手机号码

         password：用户密码 md5加密32位字符串

         timestamp：当前时间戳

         random：随机数

         sign：签名【生成规则 uid+password+timestamp+random 后md5加密串】

         usertype:用户类型
         */
        final String phone = etLoginPhone.getText().toString().trim();
        String pwd = etLoginPwd.getText().toString().trim();

        LoginDTO loginDTO = new LoginDTO();
        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();
        loginDTO.setUid(phone);
        loginDTO.setPassword(pwd);
        loginDTO.setTimestamp(time);
        loginDTO.setRandom(random);
        loginDTO.setSign(phone + pwd + time + random);
        loginDTO.setUsertype(AppConfig.COMMON_USER_TYPE); //默认为普通用户
        LogUtils.e("time---", "" + time);
        LogUtils.e("random---", "" + random);
        CommonApiClient.login(this, loginDTO, new CallBack<LoginEntity>() {
            @Override
            public void onSuccess(LoginEntity result) {
                LogUtils.e("result========" + result.getMsg());
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("登录成功");
                    ToastUtils.showShort(LoginForPwdActivity.this, "登录成功");
                    LogUtils.e("用户令牌======" + result.getData().getAccessToken());

                    //保存用户信息
                    AppConfig.uid = phone;
                    AppConfig.accessToken = result.getData().getAccessToken();
                    AppConfig.isLogin = true;

                    LogUtils.d("uid==" + phone + ",accessToken==" + AppConfig.accessToken + ",isLogin==" + AppConfig.isLogin);
                    //跳转到预约页面
                    HomeUiGoto.gotoApt(LoginForPwdActivity.this);
                    finish();
                } else {
                    new AlertDialog.Builder(LoginForPwdActivity.this).setTitle("用户名密码错误").setPositiveButton("确定", null).show();
                }
            }
        });
    }
}
