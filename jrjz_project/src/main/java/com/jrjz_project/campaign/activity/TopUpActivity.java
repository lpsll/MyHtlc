package com.jrjz_project.campaign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.common.base.BaseTitleActivity;
import com.jrjz_project.common.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 充值页面
 */
public class TopUpActivity extends BaseTitleActivity {

    @Bind(R.id.et_top_up_chongzhi)
    EditText etTopUpChongzhi;
    @Bind(R.id.img_top_up_weixin)
    ImageView imgTopUpWeixin;
    @Bind(R.id.img_top_up_zhifubao)
    ImageView imgTopUpZhifubao;
    @Bind(R.id.tv_top_up_ok)
    TextView tvTopUpOk;
    @Bind(R.id.tv_top_up_chongzhi)
    TextView tvTopUpChongzhi;

    private Intent mIntent;
    private String typeForTopUp; //判断是否是用户输入充值金额

    private static String typeForPay; //支付方式
    private static final String WEIXIN = "weixin";
    private static final String ZHIFUBAO = "zhifubao";

    private String moneyForUserInput; //用户输入的充值金额


    @Override
    public void initView() {
        setTitleText("充值");
//        ensure = (TextView) findViewById(R.id.base_titlebar_ensure);
//        // 初始化右边图片
//        TextViewUtils.setTextViewIcon(this, ensure, R.drawable.back,
//                R.dimen.common_titlebar_icon_width,
//                R.dimen.common_titlebar_icon_height, TextViewUtils.DRAWABLE_LEFT);

        typeForPay = null;
        mIntent = getIntent();
        typeForTopUp = mIntent.getStringExtra("TypeForTopUp");
        if (!TextUtils.isEmpty(typeForTopUp)) {
            //当是固定的金额时
            if ("fixed".equals(typeForTopUp)) {
                tvTopUpChongzhi.setVisibility(View.VISIBLE);
                etTopUpChongzhi.setVisibility(View.GONE);
                //当需要用户输入金额时
            } else if ("WriteForUser".equals(typeForTopUp)) {
                tvTopUpChongzhi.setVisibility(View.GONE);
                etTopUpChongzhi.setVisibility(View.VISIBLE);
            }
        }


    }

    @Override
    public void initData() {


    }


    @Override
    protected int getContentResId() {
        return R.layout.activity_top_up;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_top_up_weixin, R.id.img_top_up_zhifubao, R.id.tv_top_up_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_top_up_weixin:  //微信支付
//                ToastUtils.showShort(TopUpActivity.this,"微信支付");
                imgTopUpWeixin.setImageResource(R.drawable.dianjixdpi_03);
                imgTopUpZhifubao.setImageResource(R.drawable.weidianjixdpi_03);
                typeForPay = WEIXIN;
                break;
            case R.id.img_top_up_zhifubao: //支付宝支付
//                ToastUtils.showShort(TopUpActivity.this,"支付宝支付");
                imgTopUpWeixin.setImageResource(R.drawable.weidianjixdpi_03);
                imgTopUpZhifubao.setImageResource(R.drawable.dianjixdpi_03);
                typeForPay = ZHIFUBAO;
                break;
            case R.id.tv_top_up_ok: //确定按钮 确定支付方式
                //当是用户输入时，先判定输入金额是否合法
                if("WriteForUser".equals(typeForTopUp)) {
                    moneyForUserInput = etTopUpChongzhi.getText().toString().trim();
                    if (TextUtils.isEmpty(moneyForUserInput) || Integer.parseInt(moneyForUserInput) == 0) {
                        ToastUtils.showShort(TopUpActivity.this, "请输入正确金额");
                        break;
                    }
                }
                    if (WEIXIN.equals(typeForPay)) {
                        ToastUtils.showShort(TopUpActivity.this, "支付方式：微信支付");
                    } else if (ZHIFUBAO.equals(typeForPay)) {
                        ToastUtils.showShort(TopUpActivity.this, "支付方式：支付宝支付");
                    } else {
                        ToastUtils.showShort(TopUpActivity.this, "未选择支付方式！");
                    }
                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
//            case R.id.base_titlebar_ensure:
//                baseGoBack();
//                break;
        }
    }
}
