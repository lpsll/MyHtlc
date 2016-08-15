package com.jrjz_project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.view.pager.CouponHomePager;

/**
 * Created by John_Libo on 2016/8/2.
 */
public class CouponActivity extends Activity {
    private View view;
    private CouponHomePager couponHomePager;
    private FrameLayout framelayout_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view=View.inflate(this, R.layout.coupon_mine,null);
        setContentView(view);
        initView();
    }

    public void initView(){
        couponHomePager=new CouponHomePager(this,"");
        framelayout_order=(FrameLayout) findViewById(R.id.framelayout_order);
        framelayout_order.removeAllViews();
        framelayout_order.addView(couponHomePager.getRootView());
        couponHomePager.initData();
    }
}
