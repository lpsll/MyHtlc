package com.autodesk.easyhome.shejijia.order.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.htlc.jrjz.jrjz_project.R;
import com.autodesk.easyhome.shejijia.common.base.BaseFragment;
import com.autodesk.easyhome.shejijia.common.widget.SlidingTabLayout;
import com.autodesk.easyhome.shejijia.order.adapter.OrderTabListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 订单页
 */
public class OrderFragment extends BaseFragment {
    public static final int TAB_A = 1;
    public static final int TAB_B = 2;
    public static final int TAB_C = 3;
    public static final int TAB_D = 4;
    public static final int TAB_E = 5;
    @Bind(R.id.order_tab)
    SlidingTabLayout mOrderTab;
    @Bind(R.id.order_content)
    ViewPager mOrderContent;
    private OrderTabListAdapter mAdapter;
    private List<Fragment> fragmentList;


    @Override
    protected void retry() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_order;
    }

    @Override
    public void initView(View view) {
        fragmentList = new ArrayList<Fragment>();

        fragmentList.add(OrderInsideFragment.newInstance(TAB_A));
        fragmentList.add(OrderInsideFragment.newInstance(TAB_B));
        fragmentList.add(OrderInsideFragment.newInstance(TAB_C));
        fragmentList.add(OrderInsideFragment.newInstance(TAB_D));
        fragmentList.add(OrderInsideFragment.newInstance(TAB_E));

        String titles[] = getResources().getStringArray(R.array.order_tab);
        mAdapter = new OrderTabListAdapter(getChildFragmentManager(), this, titles, fragmentList);
        mOrderContent.setAdapter(mAdapter);
        mOrderContent.setOffscreenPageLimit(fragmentList.size());
        mOrderTab.setSelectedIndicatorColors(getResources().getColor(R.color.colorPrimary));
        mOrderTab.setDistributeEvenly(true);
//        mOrderTab.setCustomTabView(R.layout.item_tab,0);
        mOrderTab.setViewPager(mOrderContent);


    }

    @Override
    public void initData() {

    }
}
