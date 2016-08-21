package com.jrjz_project.mine.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.campaign.activity.TopUpActivity;
import com.jrjz_project.common.base.BaseFragment;
import com.jrjz_project.home.activity.SelectAddressActivity;
import com.jrjz_project.mine.activity.ChangePhoneActivity;
import com.jrjz_project.mine.activity.FeedBackActivity;
import com.jrjz_project.mine.activity.MineOrderActivity;
import com.jrjz_project.mine.activity.MoreSettingActivity;
import com.jrjz_project.order.activity.CouponActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的页
 */
public class MineFragment extends BaseFragment {

    @Bind(R.id.rl_mine_changephone)
    RelativeLayout rlMineChangephone;
    @Bind(R.id.ll_mine_chongzhi)
    LinearLayout llMineChongzhi;
    @Bind(R.id.ll_mine_more_setting)
    LinearLayout llMineMoreSetting;
    @Bind(R.id.ll_mine_address)
    LinearLayout llMineAddress;
    @Bind(R.id.ll_mine_myorder)
    LinearLayout llMineMyorder;
    @Bind(R.id.ll_mine_coupon)
    LinearLayout llMineCoupon;
    @Bind(R.id.ll_mine_feedback)
    LinearLayout llMineFeedback;

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }


    @Override
    protected void retry() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_mine;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.rl_mine_changephone, R.id.ll_mine_chongzhi, R.id.ll_mine_more_setting,R.id.ll_mine_myorder,R.id.ll_mine_address,R.id.ll_mine_coupon,R.id.ll_mine_feedback})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_mine_changephone:
                //跳转到切换手机号页面
                getContext().startActivity(new Intent(getContext(), ChangePhoneActivity.class));
                break;
            case R.id.ll_mine_chongzhi:
                //跳转到充值页
                Intent intent = new Intent(new Intent(getContext(), TopUpActivity.class));
                intent.putExtra("TypeForTopUp", "WriteForUser");
                getContext().startActivity(intent);
                break;
            case R.id.ll_mine_more_setting:
                //跳转到更多设置页
                getContext().startActivity(new Intent(getContext(), MoreSettingActivity.class));
                break;
            case R.id.ll_mine_address:
                getContext().startActivity(new Intent(getContext(), SelectAddressActivity.class));
                break;
            case R.id.ll_mine_myorder:
                getContext().startActivity(new Intent(getContext(), MineOrderActivity.class));
                break;
            case R.id.ll_mine_coupon:
                //跳转到优惠券页面
                getContext().startActivity(new Intent(getContext(), CouponActivity.class));
                break;
            case R.id.ll_mine_feedback:
                getContext().startActivity(new Intent(getContext(), FeedBackActivity.class));
                break;
        }
    }

}
