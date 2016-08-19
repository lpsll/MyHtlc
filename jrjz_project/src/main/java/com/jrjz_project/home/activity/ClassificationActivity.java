package com.jrjz_project.home.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.common.base.BaseTitleActivity;
import com.jrjz_project.common.utils.LogUtils;
import com.jrjz_project.home.adapter.ClassificationAdapter;
import com.jrjz_project.home.adapter.ClassificationNewAdapter;
import com.jrjz_project.home.adapter.ClassificationTabAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 分类页
 */
public class ClassificationActivity extends BaseTitleActivity {
    public static final int TAB_A = 1;
    public static final int TAB_B = 2;
    public static final int TAB_C = 3;
    public static final int TAB_D = 4;
    public static final int TAB_E = 5;
    @Bind(R.id.tv1)
    TextView mTv1;
    @Bind(R.id.tv2)
    TextView mTv2;
    @Bind(R.id.tv3)
    TextView mTv3;
    @Bind(R.id.tv4)
    TextView mTv4;
    @Bind(R.id.tv5)
    TextView mTv5;
    @Bind(R.id.cf_list)
    ListView mCfList;

    private ClassificationTabAdapter mAdapter;
    private List<Fragment> fragmentList;
    public static final int TAB_NUM = 5;
    private TextView[] mTabViews = new TextView[TAB_NUM];

    @Override
    protected int getContentResId() {
        return R.layout.activity_classification;
    }

    @Override
    public void initView() {
        setTitleText("分类");

//        mTabViews[0] = mTv1;
//        mTabViews[1] = mTv2;
//        mTabViews[2] = mTv3;
//        mTabViews[3] = mTv4;
//        mTabViews[4] = mTv5;


//        fragmentList = new ArrayList<Fragment>();
//
//        fragmentList.add(ClassificationFragment.newInstance(TAB_A));
//        fragmentList.add(ClassificationFragment.newInstance(TAB_B));
//        fragmentList.add(ClassificationFragment.newInstance(TAB_C));
//        fragmentList.add(ClassificationFragment.newInstance(TAB_D));
//        fragmentList.add(ClassificationFragment.newInstance(TAB_E));
//
//        String titles[] = getResources().getStringArray(R.array.order_tab);
//        mAdapter = new ClassificationTabAdapter(getSupportFragmentManager(), this, titles, fragmentList);
//        mCfContent.setAdapter(mAdapter);
//        mCfContent.setOffscreenPageLimit(fragmentList.size());
//        mCfTab.setSelectedIndicatorColors(getResources().getColor(R.color.colorPrimary));
//        mCfTab.setDistributeEvenly(true);
//        mCfTab.setViewPager(mCfContent);


    }

    private String[] string = { "家装维修", "家电维修", "家具维修", "家政服务", "社区服务"};
//    private String[] string = { "家具维修", "家电维修", "家装维修", "家政服务", "社区服务"};

    @Override
    public void initData() {
        ClassificationAdapter adapter = new ClassificationAdapter(this);

        int size = string.length;
        for (int i = 0; i < size; i++) {
            adapter.addSeparatorItem(string[i]);
            for (int k = 0; k < 5; k++) {
                adapter.addItem("第一" + k);
            }
//            for (int k = 0; k < 5; k++) {
//                adapter.addNewItem("第二 " + k);
//            }
        }
        mCfList.setAdapter(adapter);
        mCfList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                LogUtils.e("onScrollStateChanged---","onScrollStateChanged");

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem<5*1+1){
                    LogUtils.e("mTv1---","mTv1");

                    mTv1.setBackgroundColor(getResources().getColor(R.color.status_color));
                    mTv1.setTextColor(getResources().getColor(R.color.navi));
                    mTv2.setBackgroundColor(getResources().getColor(R.color.color_cc));
                    mTv2.setTextColor(getResources().getColor(R.color.color_00));
                    mTv3.setBackgroundColor(getResources().getColor(R.color.color_cc));
                    mTv3.setTextColor(getResources().getColor(R.color.color_00));
                    mTv4.setBackgroundColor(getResources().getColor(R.color.color_cc));
                    mTv4.setTextColor(getResources().getColor(R.color.color_00));
                    mTv5.setBackgroundColor(getResources().getColor(R.color.color_cc));
                    mTv5.setTextColor(getResources().getColor(R.color.color_00));
                }
                if(5*1<firstVisibleItem&&firstVisibleItem<5*2+2){
                    LogUtils.e("mTv2---","mTv2");
//                    for(int i = 5*1;i<5*2+2;i++){
//                        LinearLayout  layout  = (LinearLayout )view.getChildAt(i);
//                        LinearLayout lin  = (LinearLayout) layout.findViewById(R.id.lin);
//                        lin.setBackgroundColor(getResources().getColor(R.color.status_color));
//                    }

                    mTv1.setBackgroundColor(getResources().getColor(R.color.color_cc));
                    mTv1.setTextColor(getResources().getColor(R.color.color_00));
                    mTv2.setBackgroundColor(getResources().getColor(R.color.status_color));
                    mTv2.setTextColor(getResources().getColor(R.color.navi));
                    mTv3.setBackgroundColor(getResources().getColor(R.color.color_cc));
                    mTv3.setTextColor(getResources().getColor(R.color.color_00));
                    mTv4.setBackgroundColor(getResources().getColor(R.color.color_cc));
                    mTv4.setTextColor(getResources().getColor(R.color.color_00));
                    mTv5.setBackgroundColor(getResources().getColor(R.color.color_cc));
                    mTv5.setTextColor(getResources().getColor(R.color.color_00));
                }
                if(5*2+1<firstVisibleItem&&firstVisibleItem<5*3+3){
                    LogUtils.e("mTv3---","mTv3");
                    mTv1.setBackgroundColor(getResources().getColor(R.color.color_cc));
                    mTv1.setTextColor(getResources().getColor(R.color.color_00));
                    mTv2.setBackgroundColor(getResources().getColor(R.color.color_cc));
                    mTv2.setTextColor(getResources().getColor(R.color.color_00));
                    mTv3.setBackgroundColor(getResources().getColor(R.color.status_color));
                    mTv3.setTextColor(getResources().getColor(R.color.navi));
                    mTv4.setBackgroundColor(getResources().getColor(R.color.color_cc));
                    mTv4.setTextColor(getResources().getColor(R.color.color_00));
                    mTv5.setBackgroundColor(getResources().getColor(R.color.color_cc));
                    mTv5.setTextColor(getResources().getColor(R.color.color_00));
                }
                if(5*3+2<firstVisibleItem&&firstVisibleItem<5*4+4){
                    LogUtils.e("mTv4---","mTv4");
                    mTv1.setBackgroundColor(getResources().getColor(R.color.color_cc));
                    mTv1.setTextColor(getResources().getColor(R.color.color_00));
                    mTv2.setBackgroundColor(getResources().getColor(R.color.color_cc));
                    mTv2.setTextColor(getResources().getColor(R.color.color_00));
                    mTv3.setBackgroundColor(getResources().getColor(R.color.color_cc));
                    mTv3.setTextColor(getResources().getColor(R.color.color_00));
                    mTv4.setBackgroundColor(getResources().getColor(R.color.status_color));
                    mTv4.setTextColor(getResources().getColor(R.color.navi));
                    mTv5.setBackgroundColor(getResources().getColor(R.color.color_cc));
                    mTv5.setTextColor(getResources().getColor(R.color.color_00));
                }
                if(5*4+3<firstVisibleItem){
                    LogUtils.e("mTv5---","mTv5");
                    mTv1.setBackgroundColor(getResources().getColor(R.color.color_cc));
                    mTv1.setTextColor(getResources().getColor(R.color.color_00));
                    mTv2.setBackgroundColor(getResources().getColor(R.color.color_cc));
                    mTv2.setTextColor(getResources().getColor(R.color.color_00));
                    mTv3.setBackgroundColor(getResources().getColor(R.color.color_cc));
                    mTv3.setTextColor(getResources().getColor(R.color.color_00));
                    mTv4.setBackgroundColor(getResources().getColor(R.color.color_cc));
                    mTv4.setTextColor(getResources().getColor(R.color.color_00));
                    mTv5.setBackgroundColor(getResources().getColor(R.color.status_color));
                    mTv5.setTextColor(getResources().getColor(R.color.navi));
                }

            }
        });

    }





    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                mTv1.setBackgroundColor(getResources().getColor(R.color.status_color));
                mTv1.setTextColor(getResources().getColor(R.color.navi));
                mTv2.setBackgroundColor(getResources().getColor(R.color.color_cc));
                mTv2.setTextColor(getResources().getColor(R.color.color_00));
                mTv3.setBackgroundColor(getResources().getColor(R.color.color_cc));
                mTv3.setTextColor(getResources().getColor(R.color.color_00));
                mTv4.setBackgroundColor(getResources().getColor(R.color.color_cc));
                mTv4.setTextColor(getResources().getColor(R.color.color_00));
                mTv5.setBackgroundColor(getResources().getColor(R.color.color_cc));
                mTv5.setTextColor(getResources().getColor(R.color.color_00));
                mCfList.setSelection(0);
                break;
            case R.id.tv2:
                mTv1.setBackgroundColor(getResources().getColor(R.color.color_cc));
                mTv1.setTextColor(getResources().getColor(R.color.color_00));
                mTv2.setBackgroundColor(getResources().getColor(R.color.status_color));
                mTv2.setTextColor(getResources().getColor(R.color.navi));
                mTv3.setBackgroundColor(getResources().getColor(R.color.color_cc));
                mTv3.setTextColor(getResources().getColor(R.color.color_00));
                mTv4.setBackgroundColor(getResources().getColor(R.color.color_cc));
                mTv4.setTextColor(getResources().getColor(R.color.color_00));
                mTv5.setBackgroundColor(getResources().getColor(R.color.color_cc));
                mTv5.setTextColor(getResources().getColor(R.color.color_00));
                mCfList.setSelection(5*1+1);
                break;
            case R.id.tv3:
                mTv1.setBackgroundColor(getResources().getColor(R.color.color_cc));
                mTv1.setTextColor(getResources().getColor(R.color.color_00));
                mTv2.setBackgroundColor(getResources().getColor(R.color.color_cc));
                mTv2.setTextColor(getResources().getColor(R.color.color_00));
                mTv3.setBackgroundColor(getResources().getColor(R.color.status_color));
                mTv3.setTextColor(getResources().getColor(R.color.navi));
                mTv4.setBackgroundColor(getResources().getColor(R.color.color_cc));
                mTv4.setTextColor(getResources().getColor(R.color.color_00));
                mTv5.setBackgroundColor(getResources().getColor(R.color.color_cc));
                mTv5.setTextColor(getResources().getColor(R.color.color_00));
                mCfList.setSelection(5*2+2);
                break;
            case R.id.tv4:
                mTv1.setBackgroundColor(getResources().getColor(R.color.color_cc));
                mTv1.setTextColor(getResources().getColor(R.color.color_00));
                mTv2.setBackgroundColor(getResources().getColor(R.color.color_cc));
                mTv2.setTextColor(getResources().getColor(R.color.color_00));
                mTv3.setBackgroundColor(getResources().getColor(R.color.color_cc));
                mTv3.setTextColor(getResources().getColor(R.color.color_00));
                mTv4.setBackgroundColor(getResources().getColor(R.color.status_color));
                mTv4.setTextColor(getResources().getColor(R.color.navi));
                mTv5.setBackgroundColor(getResources().getColor(R.color.color_cc));
                mTv5.setTextColor(getResources().getColor(R.color.color_00));
                mCfList.setSelection(5*3+3);
                break;
            case R.id.tv5:
                mTv1.setBackgroundColor(getResources().getColor(R.color.color_cc));
                mTv1.setTextColor(getResources().getColor(R.color.color_00));
                mTv2.setBackgroundColor(getResources().getColor(R.color.color_cc));
                mTv2.setTextColor(getResources().getColor(R.color.color_00));
                mTv3.setBackgroundColor(getResources().getColor(R.color.color_cc));
                mTv3.setTextColor(getResources().getColor(R.color.color_00));
                mTv4.setBackgroundColor(getResources().getColor(R.color.color_cc));
                mTv4.setTextColor(getResources().getColor(R.color.color_00));
                mTv5.setBackgroundColor(getResources().getColor(R.color.status_color));
                mTv5.setTextColor(getResources().getColor(R.color.navi));
                mCfList.setSelection(5*4+4);
                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
        }
    }
}
