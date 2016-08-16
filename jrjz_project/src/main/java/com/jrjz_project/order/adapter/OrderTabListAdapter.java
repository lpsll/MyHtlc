package com.jrjz_project.order.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jrjz_project.order.fragment.OrderFragment;

import java.util.List;

/**
 * Created by John_Libo on 2016/8/15.
 */
public class OrderTabListAdapter extends FragmentStatePagerAdapter {
    private String[] titles;
    private OrderFragment context;
    private List<Fragment> fragmentList;


    public OrderTabListAdapter(FragmentManager fm, OrderFragment context, String[] titles,
                                List<Fragment> fragmentList) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
        this.titles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
