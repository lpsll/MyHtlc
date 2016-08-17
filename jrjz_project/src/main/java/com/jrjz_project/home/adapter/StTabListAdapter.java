package com.jrjz_project.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jrjz_project.home.activity.ServiceTimeActivity;
import com.jrjz_project.order.fragment.OrderFragment;

import java.util.List;

/**
 * Created by John_Libo on 2016/8/17.
 */
public class StTabListAdapter extends FragmentStatePagerAdapter {
    private String[] titles;
    private ServiceTimeActivity context;
    private List<Fragment> fragmentList;




    public StTabListAdapter(FragmentManager fragmentManager, ServiceTimeActivity context, String[] titles, List<Fragment> fragmentList) {
        super(fragmentManager);
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
