package com.jrjz_project.home.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.common.base.BaseTitleActivity;
import com.jrjz_project.home.adapter.ClassificationAdapter;
import com.jrjz_project.home.adapter.ClassificationTabAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by John_Libo on 2016/8/17.
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

    @Override
    protected int getContentResId() {
        return R.layout.activity_classification;
    }

    @Override
    public void initView() {
        setTitleText("分类");
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

    @Override
    public void initData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 30; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", "利" + i);
            map.put("title", "先生" + i);
            list.add(map);
        }
        mCfList.setAdapter(new ClassificationAdapter(this, list));
    }



    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                break;
            case R.id.tv2:
                break;
            case R.id.tv3:
                break;
            case R.id.tv4:
                break;
            case R.id.tv5:
                break;
        }
    }
}
