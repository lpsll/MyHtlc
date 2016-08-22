package com.autodesk.easyhome.shejijia.mine.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.htlc.jrjz.jrjz_project.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.order.fragment.OrderFragment;

import butterknife.ButterKnife;

/**
 * 我的 中“我的订单”页
 */
public class MineOrderActivity extends BaseTitleActivity {


    private FrameLayout mineOrder;

    @Override
    public void initView() {
        setTitleText("我的订单");
        mineOrder = (FrameLayout) findViewById(R.id.fl_mine_order);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_mine_order, new OrderFragment());
        fragmentTransaction.commit();

    }

    @Override
    public void initData() {

    }

    @Override
    protected int getContentResId() {
        return R.layout.activity_mine_order;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
