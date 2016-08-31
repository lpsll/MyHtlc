package com.autodesk.easyhome.shejijia.campaign.activity;

import android.content.Intent;
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

import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.utils.EditInputFilter;
import com.autodesk.easyhome.shejijia.common.utils.StringUtils;
import com.autodesk.easyhome.shejijia.common.utils.ToastUtils;
import com.autodesk.easyhome.shejijia.R;

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
//        ensure = (TextView) findViewById(R.id.base_titlebar_ensure);
//        // 初始化右边图片
//        TextViewUtils.setTextViewIcon(this, ensure, R.drawable.back,
//                R.dimen.common_titlebar_icon_width,
//                R.dimen.common_titlebar_icon_height, TextViewUtils.DRAWABLE_LEFT);

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
                if (str.indexOf('0') == 0) {
                    Toast.makeText(TopUpActivity.this, "首位不能为0", Toast.LENGTH_SHORT).show();
                    etTopUpChongzhi.setText("");
                }
                if (str.indexOf('.') == 0) {
                    Toast.makeText(TopUpActivity.this, "首位不能为.", Toast.LENGTH_SHORT).show();
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

                //当是用户输入时，先判定输入金额是否合法
                if ("WriteForUser".equals(typeForTopUp)) {
                    moneyForUserInput = etTopUpChongzhi.getText().toString().trim();
                    moneyForUserInput = StringUtils.addTwoZero(moneyForUserInput);
                    etTopUpChongzhi.setText(moneyForUserInput);

                    if(TextUtils.isEmpty(moneyForUserInput)) {
                        ToastUtils.showShort(TopUpActivity.this,"请输入充值金额！");
                        return;
                    }

                    if (cbTopupWX.isChecked()) {
                        ToastUtils.showShort(TopUpActivity.this, "支付方式：微信支付,金额：" + moneyForUserInput);
                    } else if (cbTopupZFB.isChecked()) {
                        ToastUtils.showShort(TopUpActivity.this, "支付方式：支付宝支付,金额：" + moneyForUserInput);
                    } else {
                        ToastUtils.showShort(TopUpActivity.this, "未选择支付方式！");
                    }


                }

                if ("fixed".equals(typeForTopUp)) {
                    moneyForUserInput = tvTopUpChongzhi.getText().toString();

                    if (cbTopupWX.isChecked()) {
                        ToastUtils.showShort(TopUpActivity.this, "支付方式：微信支付,金额：" + moneyForUserInput);
                    } else if (cbTopupZFB.isChecked()) {
                        ToastUtils.showShort(TopUpActivity.this, "支付方式：支付宝支付,金额：" + moneyForUserInput);
                    } else {
                        ToastUtils.showShort(TopUpActivity.this, "未选择支付方式！");
                    }

                }
                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
//            case R.id.base_titlebar_ensure:
//                baseGoBack();
//                break;

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
     * 设置editText的过滤
     */
    private void setEditTextFilter() {
        InputFilter[] filters = {new EditInputFilter(this)};
        etTopUpChongzhi.setFilters(filters);
    }
}
