package com.autodesk.easyhome.shejijia.mine.fragment;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.campaign.activity.TopUpActivity;
import com.autodesk.easyhome.shejijia.common.base.BaseFragment;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.autodesk.easyhome.shejijia.home.activity.SelectAddressActivity;
import com.autodesk.easyhome.shejijia.mine.MineUiGoto;
import com.autodesk.easyhome.shejijia.mine.activity.ChangePhoneActivity;
import com.autodesk.easyhome.shejijia.mine.activity.FeedBackActivity;
import com.autodesk.easyhome.shejijia.mine.activity.MineOrderActivity;
import com.autodesk.easyhome.shejijia.mine.activity.MoreSettingActivity;
import com.autodesk.easyhome.shejijia.order.activity.CouponActivity;
import com.htlc.jrjz.jrjz_project.R;

import butterknife.Bind;
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
    @Bind(R.id.tv_mine_phone)
    TextView tvMinePhone;

    @Override
    public void initView(View view) {
        LogUtils.e("isLogin---",""+AppContext.get("IS_LOGIN", false));
        if (AppContext.get("IS_LOGIN", false)) {
            LogUtils.e("true---","true");
            //登录状态时就设置为用户手机号
            tvMinePhone.setText(AppContext.get(AppConfig.UID,""));

        }else {
            LogUtils.e("false---","false");
            tvMinePhone.setText("请登录");
        }

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




    @OnClick({R.id.rl_mine_changephone, R.id.ll_mine_chongzhi, R.id.ll_mine_more_setting, R.id.ll_mine_myorder, R.id.ll_mine_address, R.id.ll_mine_coupon, R.id.ll_mine_feedback})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_mine_changephone:
                //跳转到切换手机号页面
                if (AppContext.get(AppConfig.IS_LOGIN, false)) {
                    getContext().startActivity(new Intent(getContext(), ChangePhoneActivity.class));
                } else {
                    HomeUiGoto.gotoLoginForPwd(getActivity());
                }
                break;
            case R.id.ll_mine_chongzhi:
                //跳转到充值页
                if (AppContext.get(AppConfig.IS_LOGIN, false)) {
                    Intent intent = new Intent(new Intent(getContext(), TopUpActivity.class));
                    intent.putExtra("TypeForTopUp", "WriteForUser");
                    getContext().startActivity(intent);
                } else {
                    HomeUiGoto.gotoLoginForPwd(getActivity());
                }
                break;
            case R.id.ll_mine_more_setting:
                //跳转到更多设置页
                if (AppContext.get("IS_LOGIN", false)) {
                    MineUiGoto.gotoSetting(getActivity());
                } else {
                    HomeUiGoto.gotoLoginForPwd(getActivity());
                }
                break;
            case R.id.ll_mine_address:
                if (AppContext.get(AppConfig.IS_LOGIN, false)) {
                    getContext().startActivity(new Intent(getContext(), SelectAddressActivity.class));
                } else {
                    HomeUiGoto.gotoLoginForPwd(getActivity());
                }
                break;
            case R.id.ll_mine_myorder:
                if (AppContext.get(AppConfig.IS_LOGIN, false)) {
                    getContext().startActivity(new Intent(getContext(), MineOrderActivity.class));
                } else {
                    HomeUiGoto.gotoLoginForPwd(getActivity());
                }

                break;
            case R.id.ll_mine_coupon:
                //跳转到优惠券页面
                if (AppContext.get(AppConfig.IS_LOGIN, false)) {
                    getContext().startActivity(new Intent(getContext(), CouponActivity.class));
                } else {
                    HomeUiGoto.gotoLoginForPwd(getActivity());
                }

                break;
            case R.id.ll_mine_feedback:
                if (AppContext.get(AppConfig.IS_LOGIN, false)) {
                    getContext().startActivity(new Intent(getContext(), FeedBackActivity.class));
                } else {
                    HomeUiGoto.gotoLoginForPwd(getActivity());
                }
                break;
        }
    }

}
