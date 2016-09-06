package com.autodesk.easyhome.shejijia.order.adapter;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.order.fragment.OrderFragment;

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

//        String tit = titles[position];
//        LogUtils.e("tit.length()----",""+tit.length());
//        if(tit.length()>3){
//            SpannableStringBuilder style = new SpannableStringBuilder(tit);
//            style.setSpan(new ForegroundColorSpan(Color.RED), 0, 4, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //设置指定位置文字的颜色
//            return style;
//        }else {
//            return tit;
//        }

    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
