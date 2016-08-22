package com.autodesk.easyhome.shejijia.order.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.htlc.jrjz.jrjz_project.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.widget.SlidingTabLayout;
import com.autodesk.easyhome.shejijia.order.adapter.CouponTabListAdapter;
import com.autodesk.easyhome.shejijia.order.fragment.CouponFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 优惠券页
 */
public class CouponActivity extends BaseTitleActivity {
    public static final int TAB_A = 1;
    public static final int TAB_B = 2;
    public static final int TAB_C = 3;
    @Bind(R.id.coupon_tab)
    SlidingTabLayout mTab;
    @Bind(R.id.coupon_content)
    ViewPager mContent;
    private CouponTabListAdapter mAdapter;
    private List<Fragment> fragmentList;

    @Override
    protected int getContentResId() {
        return R.layout.activity_coupon;
    }

    @Override
    public void initView() {
        setTitleText("优惠券");
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(CouponFragment.newInstance(TAB_A));
        fragmentList.add(CouponFragment.newInstance(TAB_B));
        fragmentList.add(CouponFragment.newInstance(TAB_C));
        String str1 = "未使用"+"(4)";
        String str2 = "已过期"+"(10)";
        String str3 = "已使用"+"(15)";

        String titles[] = {str1,str2,str3};
        mAdapter = new CouponTabListAdapter(getSupportFragmentManager(), this, titles, fragmentList);
        mContent.setAdapter(mAdapter);
        mContent.setOffscreenPageLimit(fragmentList.size());
        mTab.setSelectedIndicatorColors(getResources().getColor(R.color.colorPrimary));
        mTab.setDistributeEvenly(true);
        mTab.setViewPager(mContent);

    }

    @Override
    public void initData() {

    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
        }
    }
}
