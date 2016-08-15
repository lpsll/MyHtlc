package com.jrjz_project.view.pager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.base.BasePager;
import com.jrjz_project.bean.OrderStatusCate;
import com.jrjz_project.bean.OrderStatusItem;
import com.jrjz_project.view.pagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;


public class CouponHomePager extends BasePager {
    private TabPageIndicator indicator;
    private ViewPager view_pager;
    private OrderStatusCate orderStatusCate;
    //	private MailCate data;
//    private MibaCategory mibaCategory;
    private String flag;
    public CouponHomePager(Context context, String flag) {
        super(context);
        this.flag=flag;
    }

    private List<BasePager> mLists = new ArrayList<BasePager>();
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.coupon_status_frag, null);
        return view;
    }
    private int mCurrentPosition = 0;
    @Override
    public void initData() {
        //这次数据是定死的，后期可以动态从服务器端获取
        //selfinfo的信息也可以用这个bean来做
        String[] str_txt = {"未完成","待支付","待评价","已完成","全部订单"};
        String[] str_url = {"http://192.382.8.23:8080/women","http://192.382.8.23:8080/women","http://192.382.8.23:8080/women","http://192.382.8.23:8080/women","http://192.382.8.23:8080/women"};

        orderStatusCate=new OrderStatusCate();
        for (int i=0;i<5;i++){
            OrderStatusItem orderStatusItem=new OrderStatusItem();
            orderStatusItem.title=str_txt[i];
            orderStatusItem.url=str_url[i];
            orderStatusCate.list.add(orderStatusItem);
        }
        mLists.add(new CouponDue(context,orderStatusCate.list.get(0).url));     //未完成
        mLists.add(new CouponUse(context,orderStatusCate.list.get(1).url));       //待支付
        indicator=(TabPageIndicator) view.findViewById(R.id.indicator);
        indicator.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
                BasePager pager = mLists.get(position);
                if(!pager.is_load){
                    pager.initData();
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

        ViewPagerAdapter adapter = new ViewPagerAdapter(mLists);
        view_pager=(ViewPager) view.findViewById(R.id.pager);
        view_pager.setAdapter(adapter);
        indicator.setViewPager(view_pager);
        indicator.setCurrentItem(mCurrentPosition);
        mLists.get(0).initData();
        mLists.get(0).is_load = true;
    }


    private class ViewPagerAdapter extends PagerAdapter{
        private List<BasePager> mViewPager = null;
        public ViewPagerAdapter(List<BasePager> mLists) {
            this.mViewPager = mLists;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // TODO Auto-generated method stub
            ((ViewPager)container).removeView(mViewPager.get(position).getRootView());
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // TODO Auto-generated method stub
            return orderStatusCate.list.get(position).title;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager)container).addView(mViewPager.get(position).getRootView());
            return mViewPager.get(position).getRootView();
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mViewPager.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1 ;
        }

    }

}
