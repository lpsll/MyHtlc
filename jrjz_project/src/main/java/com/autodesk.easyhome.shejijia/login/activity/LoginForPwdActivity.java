package com.autodesk.easyhome.shejijia.login.activity;

import android.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.PhoneUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.common.utils.ToastUtils;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.autodesk.easyhome.shejijia.login.dto.LoginDTO;
import com.autodesk.easyhome.shejijia.login.entity.LoginEntity;

import butterknife.Bind;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

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
                //判断电话，密码格式和是否为空
                if (TextUtils.isEmpty(etLoginPhone.getText().toString().trim())) {
                    new AlertDialog.Builder(LoginForPwdActivity.this).setTitle("温馨提示").setMessage("请输入用户名").setPositiveButton("确定", null).show();
                    break;
                }
                boolean isValid = PhoneUtils.isPhoneNumberValid(etLoginPhone.getText().toString());
                if (!isValid) {
                    new AlertDialog.Builder(this).setTitle("温馨提示").setMessage("请输入正确的电话号码!").setPositiveButton("确定", null).show();
                    break;
                }
                if (TextUtils.isEmpty(etLoginPwd.getText().toString().trim())) {
                    new AlertDialog.Builder(LoginForPwdActivity.this).setTitle("温馨提示").setMessage("请输入密码").setPositiveButton("确定", null).show();
                    break;
                }

                //密码格式验证
                boolean isMatches = etLoginPwd.getText().toString().trim().matches(AppConfig.PWD_REG);
                if (!isMatches) {
                    new AlertDialog.Builder(LoginForPwdActivity.this).setTitle("温馨提示").setMessage("密码格式为6位以上字母或数字!").setPositiveButton("确定", null).show();
                    etLoginPwd.setText("");
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
                    AppContext.set("uid", phone);
                    AppContext.set("accessToken", result.getData().getAccessToken());
                    AppContext.set("IS_LOGIN", true);


                    //注册成功后设置极光推送的别名和tag
                    JPushInterface.setAlias(LoginForPwdActivity.this, etLoginPhone.getText().toString(), null);

//                    HashSet<String> tag = new HashSet<>();
//                    tag.add("");
//                    JPushInterface.setTags(RegisterActivity.this,tag,null);

                    //跳转到预约页面
//                        HomeUiGoto.gotoApt(LoginForPwdActivity.this);
                    setResult(1001);
                    finish();
                }
            }
        });
    }
}
