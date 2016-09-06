package com.autodesk.easyhome.shejijia;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.campaign.fragment.CampaignFragment;
import com.autodesk.easyhome.shejijia.common.base.BaseFragment;
import com.autodesk.easyhome.shejijia.common.base.BaseHomeTitleActivity;
import com.autodesk.easyhome.shejijia.common.utils.DialogUtils;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.TextViewUtils;
import com.autodesk.easyhome.shejijia.common.utils.ToastUtils;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.autodesk.easyhome.shejijia.home.fragment.HomeFragment;
import com.autodesk.easyhome.shejijia.mine.MineUiGoto;
import com.autodesk.easyhome.shejijia.mine.fragment.MineFragment;
import com.autodesk.easyhome.shejijia.order.OrderUiGoto;
import com.autodesk.easyhome.shejijia.order.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/*
整个程序的MainActivity，入口
*/

public class MainActivity extends BaseHomeTitleActivity {

    @Bind(R.id.tv_tab_home)
    ImageView mTvTabHome;
    @Bind(R.id.tv_tab_order)
    ImageView mTvTabOrder;
    @Bind(R.id.tv_tab_campaign)
    ImageView mTvTabCampaign;
    @Bind(R.id.tv_tab_mine)
    ImageView mTvTabMine;
    int fg1,fg2,fg3,fg4,fg5;


    public static final int TAB_NUM = 4;
    private ImageView[] mTabViews = new ImageView[TAB_NUM];
    private FragmentManager fragmentManager;
    private List<BaseFragment> fragmentList = new ArrayList<>();
    /**
     * Tab图片没有选中的状态资源ID
     */
    private int[] mTabIconNors = {
            R.drawable.shouyexdpi_03,
            R.drawable.dingdanxdpi_03,
            R.drawable.huodongxdpi_03,
            R.drawable.wodexdpi_03};
    /**
     * Tab图片选中的状态资源ID
     */
    private int[] mTabIconSels = {
            R.drawable.shouyedxdpi_03,
            R.drawable.dingdandxdpi_03,
            R.drawable.huodongdxdpi_03_03,
            R.drawable.wodedxdpi_03};

    private int currentTab = -1; // 当前Tab页面索引
    private TextView mBaseEnsure, mBaseBack;

    @Override
    protected int getContentResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mBaseBack = (TextView) findViewById(R.id.base_titlebar_back);
        mBaseEnsure = (TextView) findViewById(R.id.base_titlebar_ensure);
        mBaseBack.setText("北京");

        fg1 = 0;
        fg2 = 0;
        fg3 = 0;
        fg4 = 0;
        fg5 = 0;


        // 初始化右边图片大小
        TextViewUtils.setTextViewIcon(this, mBaseEnsure, R.drawable.xiaoxixdpi_03,
                R.dimen.common_titlebar_right_icon_width,
                R.dimen.common_titlebar_right_icon_height, TextViewUtils.DRAWABLE_LEFT);

        fragmentManager = getSupportFragmentManager();
        mTabViews[0] = mTvTabHome;
        mTabViews[1] = mTvTabOrder;
        mTabViews[2] = mTvTabCampaign;
        mTabViews[3] = mTvTabMine;

        for (int i = 0; i < mTabViews.length; i++) {
            fragmentList.add(null);
//            addFragment(i);
            final int j = i;
            mTabViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.e("j----", "" + j);
                    showTab(j);
                }
            });
        }
        showTab(0); // 显示目标tab

        //注册充值页面广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(AppConfig.TOPUP_RECIVER_ACTION);
        registerReceiver(topupReceiver, filter);

    }

    /**
     * @param fragment 除了fragment，其他的都hide
     */
    private void hideAllFragments(BaseFragment fragment) {
        for (int i = 0; i < TAB_NUM; i++) {
            Fragment f = fragmentManager.findFragmentByTag("tag" + i);
            LogUtils.e("f----", "i--" + i + "---" + f);
            if (f != null && f.isAdded() && f != fragment) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.hide(f);
                transaction.commitAllowingStateLoss();
                f.setUserVisibleHint(false);
            }
        }
    }

    private BaseFragment addFragment(int index) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        BaseFragment fragment = null;
        switch (index) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new OrderFragment();
                break;
            case 2:
                fragment = new CampaignFragment();
                break;
            case 3:
                fragment = new MineFragment();
                break;

        }
        LogUtils.e("index----", "" + index);
        LogUtils.e("fragment----", "" + fragment);
        fragmentList.add(index, fragment);
        transaction.add(R.id.realtabcontent, fragment, "tag" + index);
        transaction.commitAllowingStateLoss();
        // fragmentManager.executePendingTransactions();
        return fragment;
    }

    private void showFragment(BaseFragment fragment) {
        LogUtils.e("fragment----showFragment", "" + fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.show(fragment);
        transaction.commitAllowingStateLoss();
        fragment.setUserVisibleHint(true);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        LogUtils.e("intent-----", intent + "");
        if (intent != null) {
            if (intent.getExtras() != null) {
                int tag = intent.getExtras().getInt("tag");
                LogUtils.e("tag-----", tag + "");
                showTab(tag);
            }


        }
    }

    /**
     * 切换tab
     *
     * @param idx
     */
    private void showTab(int idx) {
//        if (currentTab == idx) {
//            return;
//        }
        BaseFragment targetFragment = (BaseFragment) fragmentManager
                .findFragmentByTag("tag" + idx);
        LogUtils.e("showTab---idx----", "" + idx);
        LogUtils.e("targetFragment----", "" + targetFragment);
//        targetFragment = fragmentList.get(idx);

        if (targetFragment == null || !targetFragment.isAdded()) {
            LogUtils.e("size----", "" + fragmentList.size());
            if (idx < fragmentList.size() && fragmentList.get(idx) != null) {
                targetFragment = fragmentList.get(idx);
                LogUtils.e("targetFragment----idx---if", "" + idx + "---" + targetFragment);
            } else {
                targetFragment = addFragment(idx);
                LogUtils.e("targetFragment----idx---else", "" + idx + "---" + targetFragment);
            }
        }


        hideAllFragments(targetFragment);
        showFragment(targetFragment);
        for (int i = 0; i < TAB_NUM; i++) {
            if (idx == i) {
                mTabViews[i].setBackgroundColor(getResources().getColor(R.color.navi_press));

            } else {
                mTabViews[i].setBackgroundColor(getResources().getColor(R.color.navi));

            }
        }
        currentTab = idx; // 更新目标tab为当前tab
        LogUtils.e("currentTab----", "" + currentTab);
        getTitleLayout().setVisibility(View.VISIBLE);
        switch (currentTab) {
            case 0:
                setTitleText("居然家政");
                mBaseBack.setVisibility(View.VISIBLE);
                mBaseEnsure.setVisibility(View.VISIBLE);
                if(fg1==0){
                    fg1=1;
                }else {
                    targetFragment.initData();
                }

                break;
            case 1:
                setTitleText("订单");
                mBaseBack.setVisibility(View.GONE);
                mBaseEnsure.setVisibility(View.GONE);
                if(fg2==0){
                    fg2=1;
                }else {
                    targetFragment.initView(null);
                }
                break;
            case 2:
                setTitleText("活动");
                mBaseBack.setVisibility(View.GONE);
                mBaseEnsure.setVisibility(View.GONE);
                if(fg3==0){
                    fg3=1;
                }else {
                    targetFragment.initData();
                }
                break;
            case 3:
                setTitleText("个人中心");
                mBaseBack.setVisibility(View.GONE);
                mBaseEnsure.setVisibility(View.GONE);
                if(fg4==0){
                    fg4=1;
                }else {
                    targetFragment.initView(null);
                }
                break;

        }
    }

    @Override
    public void initData() {
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == HomeUiGoto.LOFIN_REQUEST) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            MineFragment meFragment = (MineFragment) fragmentManager.findFragmentByTag("tag3");
            if (meFragment != null) {

                meFragment.initView(null);
//                CampaignFragment campaignFragment = (CampaignFragment) fragmentManager.findFragmentByTag("tag2");
            }

//            campaignFragment.initData();
        }
        if (requestCode == MineUiGoto.SETTING_REQUEST) {
            LogUtils.e("SETTING_REQUEST----","SETTING_REQUEST");
            FragmentManager fragmentManager = getSupportFragmentManager();
            MineFragment meFragment = (MineFragment) fragmentManager.findFragmentByTag("tag3");
            if (meFragment != null) {
                meFragment.initView(null);
            }
        }

        if (requestCode == MineUiGoto.CHANGEPHONE_REQUEST) {
            LogUtils.e("CHANGEPHONE_REQUEST----","CHANGEPHONE_REQUEST");
            FragmentManager fragmentManager = getSupportFragmentManager();
            MineFragment meFragment = (MineFragment) fragmentManager.findFragmentByTag("ta3");
//            meFragment.initView(null);
        }

        if (requestCode == HomeUiGoto.LF_REQUEST) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            HomeFragment homeFragment = (HomeFragment) fragmentManager.findFragmentByTag("tag0");
            if (homeFragment != null) {
                DialogUtils.showPrompt(this, "提示","请登录", "知道了");
                homeFragment.initView(null);
            }
        }

        //订单页登录成功后刷新订单页面
        if (requestCode == HomeUiGoto.LG_REQUEST) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            OrderFragment orderFragment = (OrderFragment) fragmentManager.findFragmentByTag("tag1");
            if (orderFragment != null) {
                orderFragment.initView(null);
            }
        }

        if (requestCode == OrderUiGoto.EVALUATE_REQUEST) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            OrderFragment orderFragment = (OrderFragment) fragmentManager.findFragmentByTag("tag1");
            if (orderFragment != null) {
                orderFragment.initView(null);
            }
        }

        //我的中充值页面充值成功后刷新我的页面
        if (requestCode == MineFragment.TOPUP_REQUEST && resultCode==1009) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            MineFragment meFragment = (MineFragment) fragmentManager.findFragmentByTag("tag3");
            if (meFragment != null) {
                meFragment.initView(null);
            }
        }
    }


    private BroadcastReceiver topupReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            MineFragment meFragment = (MineFragment) fragmentManager.findFragmentByTag("tag3");
            if (meFragment != null) {
                meFragment.initView(null);
            }
        }
    };

    /**
     * 连按两次返回退出应用
     */
    private long fistTime = 0;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if((System.currentTimeMillis() - fistTime)>2000){
                ToastUtils.showShort(this,"连按两次返回键退出应用");
                fistTime = System.currentTimeMillis();
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解注册充值页面广播
        if(topupReceiver != null){
            unregisterReceiver(topupReceiver);
            topupReceiver = null;//不要忘了
        }

    }
}


