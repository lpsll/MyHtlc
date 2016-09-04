package com.autodesk.easyhome.shejijia.mine.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.widget.SlidingTabLayout;
import com.autodesk.easyhome.shejijia.mine.adapter.CouponTabListAdapter;
import com.autodesk.easyhome.shejijia.mine.fragment.CouponSubclassFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 我的优惠券
 */
public class MineCouponActivity extends BaseTitleActivity{
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
        setTitleText("我的优惠券");
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(CouponSubclassFragment.newInstance(TAB_A));
        fragmentList.add(CouponSubclassFragment.newInstance(TAB_B));
        fragmentList.add(CouponSubclassFragment.newInstance(TAB_C));
        String str1 = "未使用";
        String str2 = "已过期";
        String str3 = "已使用";

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
