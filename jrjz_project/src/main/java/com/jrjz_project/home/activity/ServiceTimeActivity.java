package com.jrjz_project.home.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.common.base.BaseTitleActivity;
import com.jrjz_project.common.widget.SlidingTabLayout;
import com.jrjz_project.home.adapter.StTabListAdapter;
import com.jrjz_project.home.fragment.STimeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by John_Libo on 2016/8/17.
 */
public class ServiceTimeActivity extends BaseTitleActivity {
    @Bind(R.id.st_tab)
    SlidingTabLayout mStTabLayout;
    @Bind(R.id.st_content)
    ViewPager mStContent;
    private List<Fragment> fragmentList;
    public static final int TAB_A = 1;
    public static final int TAB_B = 2;
    public static final int TAB_C = 3;
    public static final int TAB_D = 4;
    public static final int TAB_E = 5;
    private StTabListAdapter mAdapter;





    @Override
    protected int getContentResId() {
        return R.layout.activity_servicetime;
    }

    @Override
    public void initView() {
        setTitleText("服务时间");
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(STimeFragment.newInstance(TAB_A));
        fragmentList.add(STimeFragment.newInstance(TAB_B));
        fragmentList.add(STimeFragment.newInstance(TAB_C));
        fragmentList.add(STimeFragment.newInstance(TAB_D));
        fragmentList.add(STimeFragment.newInstance(TAB_E));

        String titles[] = {"7/19明天","7/20周一","7/21周二","7/22周三","7/23周四"};
        mAdapter = new StTabListAdapter(getSupportFragmentManager(), this, titles, fragmentList);
        mStContent.setAdapter(mAdapter);
        mStContent.setOffscreenPageLimit(fragmentList.size());
        mStTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorPrimary));
        mStTabLayout.setDistributeEvenly(true);
        mStTabLayout.setViewPager(mStContent);

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
