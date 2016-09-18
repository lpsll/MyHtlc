package com.autodesk.easyhome.shejijia.order.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.order.fragment.ServiceCouponFragment;

/**
 * 订单之优惠券
 */
public class ServiceCouponActivity extends BaseTitleActivity {
    @Override
    protected int getContentResId() {
        return R.layout.activity_simple;
    }

    @Override
    public void initView() {
        LogUtils.e("initView---","initView");
        setTitleText("优惠券");
        setEnsureText("确定");
        ServiceCouponFragment fragment = new ServiceCouponFragment();
        Bundle b  = getIntent().getBundleExtra("bundle");
        if(null!=b){
            fragment.setArguments(b);
        }
        FragmentTransaction trans = getSupportFragmentManager()
                .beginTransaction();
        trans.replace(R.id.container, fragment);
        trans.commitAllowingStateLoss();
    }

    @Override
    public void initData() {
        LogUtils.e("initData---","initData");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
            case R.id.base_titlebar_ensure:
                setResult(1200);
                finish();
                break;
            default:
                break;
        }
        super.onClick(v);
    }
}
