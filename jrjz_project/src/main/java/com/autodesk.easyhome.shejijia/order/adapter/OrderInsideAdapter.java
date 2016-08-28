package com.autodesk.easyhome.shejijia.order.adapter;

import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.order.entity.OrderEntity;
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
        holder.setText(R.id.order_tv01,"1111");
        holder.setText(R.id.order_tv02,"2222");
        holder.setText(R.id.order_tv03,"3333");
        holder.setText(R.id.order_tv04,"4444");
        holder.setText(R.id.order_tv05,"5555");
        holder.setText(R.id.order_tv06,"6666");

    }
}
