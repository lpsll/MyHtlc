package com.jrjz_project.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrivilegeFragment extends BaseFragment {

    private  View view;
    public PrivilegeFragment() {
        // Required empty public constructor
    }

    @Override
    public View initView(LayoutInflater inflater) {
        view=inflater.inflate(R.layout.regiest,null);
        return view;
    }

    @Override
    public void initData() {

    }


    /**
     * 初始化顶部Title
     * @return
     */
    @Override
    public void initTitleView() {
    }

}
