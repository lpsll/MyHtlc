package com.autodesk.easyhome.shejijia.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.autodesk.easyhome.shejijia.home.activity.ClassificationActivity;

import java.util.List;

/**
 * Created by John_Libo on 2016/8/17.
 */
public class ClassificationTabAdapter extends FragmentStatePagerAdapter {
    private String[] titles;
    private ClassificationActivity context;
    private List<Fragment> fragmentList;




    public ClassificationTabAdapter(FragmentManager fragmentManager, ClassificationActivity context, String[] titles, List<Fragment> fragmentList) {
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
