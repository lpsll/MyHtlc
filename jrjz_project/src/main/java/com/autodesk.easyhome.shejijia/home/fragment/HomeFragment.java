package com.autodesk.easyhome.shejijia.home.fragment;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.common.bean.ViewFlowBean;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.widget.ViewFlowLayout;
import com.htlc.jrjz.jrjz_project.R;
import com.autodesk.easyhome.shejijia.common.base.BaseFragment;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.autodesk.easyhome.shejijia.order.OrderUiGoto;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 首页Fragment
 */
public class HomeFragment extends BaseFragment {

    @Bind(R.id.vf_layout)
    ViewFlowLayout mVfLayout;
    @Bind(R.id.middle_image1)
    ImageView mMiddleImage1;
    @Bind(R.id.tv01)
    TextView mTv01;
    @Bind(R.id.lin01)
    LinearLayout mLin01;
    @Bind(R.id.middle_image2)
    ImageView mMiddleImage2;
    @Bind(R.id.tv02)
    TextView mTv02;
    @Bind(R.id.lin02)
    LinearLayout mLin02;
    @Bind(R.id.middle_image3)
    ImageView mMiddleImage3;
    @Bind(R.id.tv03)
    TextView mTv03;
    @Bind(R.id.lin03)
    LinearLayout mLin03;
    @Bind(R.id.middle_image4)
    ImageView mMiddleImage4;
    @Bind(R.id.tv04)
    TextView mTv04;
    @Bind(R.id.lin04)
    LinearLayout mLin04;
    @Bind(R.id.middle_image5)
    ImageView mMiddleImage5;
    @Bind(R.id.tv05)
    TextView mTv05;
    @Bind(R.id.lin05)
    LinearLayout mLin05;
    @Bind(R.id.layout_middle1)
    LinearLayout mLayoutMiddle1;
    @Bind(R.id.image_ad)
    ImageView mImageAd;
    @Bind(R.id.rl_tv01)
    TextView mRlTv01;
    @Bind(R.id.lin06)
    LinearLayout mLin06;
    @Bind(R.id.lin07)
    LinearLayout mLin07;
    @Bind(R.id.lin08)
    LinearLayout mLin08;
    @Bind(R.id.lin09)
    LinearLayout mLin09;
    @Bind(R.id.rl_tv02)
    TextView mRlTv02;
    @Bind(R.id.lin10)
    LinearLayout mLin10;
    @Bind(R.id.lin11)
    LinearLayout mLin11;
    @Bind(R.id.lin12)
    LinearLayout mLin12;
    @Bind(R.id.lin13)
    LinearLayout mLin13;
    @Bind(R.id.rl_tv03)
    TextView mRlTv03;
    @Bind(R.id.lin14)
    LinearLayout mLin14;
    @Bind(R.id.lin15)
    LinearLayout mLin15;
    @Bind(R.id.lin_15)
    LinearLayout mLin115;
    @Bind(R.id.lin16)
    LinearLayout mLin16;
    @Bind(R.id.lin17)
    LinearLayout mLin17;
    @Bind(R.id.all_service)
    TextView mAllService;

    @Override
    protected void retry() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home1;
    }

    @Override
    public void initView(View view) {
//        initViewFlow();

    }

    private void initViewFlow() {
        ArrayList<ViewFlowBean> list = new ArrayList<>();
        ViewFlowBean bean = new ViewFlowBean();
//        bean.setImgUrl(R.drawable.about_us_mine);
        list.add(bean);
        mVfLayout.updateView(list);
    }

    @Override
    public void initData() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.lin01, R.id.lin02, R.id.lin03, R.id.lin04, R.id.lin05, R.id.lin06, R.id.lin07, R.id.lin08, R.id.lin09, R.id.lin10, R.id.lin11, R.id.lin12, R.id.lin13, R.id.lin14, R.id.lin15, R.id.lin_15, R.id.lin16, R.id.lin17, R.id.all_service})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin01:
                HomeUiGoto.gotoCf(getActivity());
                break;
            case R.id.lin02:
                HomeUiGoto.gotoCf(getActivity());
                break;
            case R.id.lin03:
                HomeUiGoto.gotoCf(getActivity());
                break;
            case R.id.lin04:
//                HomeUiGoto.gotoCf(getActivity());
                OrderUiGoto.gotoOrderNew(getActivity());
                break;
            case R.id.lin05:
//                HomeUiGoto.gotoCf(getActivity());
                OrderUiGoto.gotoEvaluate(getActivity());
                break;
            case R.id.lin06:
                HomeUiGoto.gotoCf(getActivity());
                break;
            case R.id.lin07:
                //跳转到注册页面

                HomeUiGoto.gotoRegister(getActivity());
//                HomeUiGoto.gotoApt(getActivity());
                break;
            case R.id.lin08:
                HomeUiGoto.gotoApt(getActivity());
                break;
            case R.id.lin09:
                HomeUiGoto.gotoApt(getActivity());
                break;
            case R.id.lin10:
                HomeUiGoto.gotoCf(getActivity());
                break;
            case R.id.lin11:
                HomeUiGoto.gotoApt(getActivity());
                break;
            case R.id.lin12:
                HomeUiGoto.gotoApt(getActivity());
                break;
            case R.id.lin13:
                HomeUiGoto.gotoApt(getActivity());
                break;
            case R.id.lin14:
                HomeUiGoto.gotoCf(getActivity());
                break;
            case R.id.lin15:
                HomeUiGoto.gotoApt(getActivity());
                break;
            case R.id.lin_15:
                HomeUiGoto.gotoApt(getActivity());
                break;
            case R.id.lin16:
                HomeUiGoto.gotoApt(getActivity());
                break;
            case R.id.lin17:
                HomeUiGoto.gotoApt(getActivity());
                break;
            case R.id.all_service:
                HomeUiGoto.gotoCf(getActivity());
                break;
        }
    }
}
