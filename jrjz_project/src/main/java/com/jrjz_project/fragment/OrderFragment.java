package com.jrjz_project.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.base.BaseFragment;
import com.jrjz_project.utils.Common;
import com.jrjz_project.utils.SPUtil;
import com.jrjz_project.view.pager.OrderHomePager;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends BaseFragment {

    private FrameLayout fragment_order_switch;
    private  View view;
    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View initView(LayoutInflater inflater) {
        view=inflater.inflate(R.layout.fragment_order,null);
        return view;
    }

    @Override
    public void initData() {
        String string = SPUtil.getString(context,"order_result","");
        if(Common.isNetworkAvailable(context)==0){
            Toast.makeText(context,"网络错误，请重新请求网络！",Toast.LENGTH_SHORT).show();
        }

        //添加整个滑动切换布局
        OrderHomePager orderHomePager=new OrderHomePager(getActivity(),"");
        fragment_order_switch=(FrameLayout) getActivity().findViewById(R.id.framelayout_order);
        fragment_order_switch.removeAllViews();
        fragment_order_switch.addView(orderHomePager.getRootView());
        orderHomePager.initData();
    }


    /**
     * 初始化顶部Title
     * @return
     */
    @Override
    public void initTitleView() {

    }
}
