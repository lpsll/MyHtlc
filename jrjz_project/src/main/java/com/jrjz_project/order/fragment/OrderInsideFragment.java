package com.jrjz_project.order.fragment;

import android.os.Bundle;

import com.jrjz_project.common.base.BaseListFragment;
import com.jrjz_project.order.adapter.OrderInsideAdapter;
import com.jrjz_project.order.entity.OrderEntity;
import com.jrjz_project.order.entity.OrderResult;
import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by John_Libo on 2016/8/15.
 */
public class OrderInsideFragment extends BaseListFragment<OrderEntity> {
    private static final String TYPE = "type";
    private int type;

    public static OrderInsideFragment newInstance(int type) {
        OrderInsideFragment fragment = new OrderInsideFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);//传递Type
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt(TYPE, OrderFragment.TAB_A);

        }
    }

    @Override
    public BaseRecyclerAdapter<OrderEntity> createAdapter() {
        return new OrderInsideAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "OrderInsideFragment"+type+"_";
    }

    @Override
    public List<OrderEntity> readList(Serializable seri) {
        return ((OrderResult)seri).getData();
    }

    @Override
    protected void sendRequestData() {

    }

    @Override
    public void initData() {

    }
}
