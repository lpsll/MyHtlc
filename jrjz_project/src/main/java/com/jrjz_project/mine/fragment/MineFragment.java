package com.jrjz_project.mine.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.activity.TopUpActivity;
import com.jrjz_project.common.base.BaseFragment;

/**
 * 我的页
 */
public class MineFragment extends BaseFragment {

    private RelativeLayout rl_mine_changephone;//更换手机号
    private LinearLayout ll_mine_chongzhi;//充值
    private LinearLayout ll_mine_feedback; //意见反馈
    private LinearLayout ll_mine_more_setting ; //更多设置

    @Override
    public void initView(View view) {

        rl_mine_changephone = (RelativeLayout) rootView.findViewById(R.id.rl_mine_changephone);
        ll_mine_chongzhi = (LinearLayout) rootView.findViewById(R.id.ll_mine_chongzhi);
        ll_mine_feedback = (LinearLayout) rootView.findViewById(R.id.ll_mine_feedback);
        ll_mine_more_setting =(LinearLayout) rootView.findViewById(R.id.ll_mine_more_setting);

        rl_mine_changephone.setOnClickListener(this);
        ll_mine_chongzhi.setOnClickListener(this);
        ll_mine_feedback.setOnClickListener(this);
        ll_mine_more_setting.setOnClickListener(this);
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
    public void onClick(View v) {

        switch (v.getId()) {
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
            case R.id.ll_mine_feedback:
                //跳转到意见反馈页
                getContext().startActivity(new Intent(getContext(), FeedBackActivity.class));
                break;
            case R.id.ll_mine_more_setting:
                //跳转到更多设置页
                getContext().startActivity(new Intent(getContext(), MoreSettingActivity.class));
                break;
        }
    }
}
