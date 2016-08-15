package com.jrjz_project.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.CouponActivity;
import com.jrjz_project.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment{
    private LinearLayout coupon;
    private  View view;
    public MineFragment() {
        // Required empty public constructor
    }
    @Override
    public View initView(LayoutInflater inflater) {
        view=inflater.inflate(R.layout.fragment_mine,null);
        return view;
    }

    /**
     * 初始化顶部Title
     * @return
     */
    @Override
    public void initTitleView() {
    }

    @Override
    public void initData() {
        coupon=(LinearLayout) view.findViewById(R.id.myorder);
        coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CouponActivity.class);
                startActivity(intent);
            }
        });
    }



}
