package com.jrjz_project.order.adapter;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.order.entity.OrderEntity;
import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;

/**
 * Created by John_Libo on 2016/8/15.
 */
public class OrderInsideAdapter extends BaseSimpleRecyclerAdapter<OrderEntity> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_orderinsider;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, OrderEntity orderEntity, int position) {

    }
}
