package com.autodesk.easyhome.shejijia.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.order.entity.OrderEntity;
import com.autodesk.easyhome.shejijia.order.entity.ServiceCouponEntity;
import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by John_Libo on 2016/8/18.
 */
public class CouponAdapter extends BaseSimpleRecyclerAdapter<ServiceCouponEntity> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_coupon;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, ServiceCouponEntity serviceCouponEntity, int position) {

    }
}
