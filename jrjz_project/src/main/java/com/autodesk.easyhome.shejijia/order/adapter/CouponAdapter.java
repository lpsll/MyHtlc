package com.autodesk.easyhome.shejijia.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
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

        holder.setText(R.id.tv_money,serviceCouponEntity.getValue_amount().replace(".00",""));
        holder.setText(R.id.tv_sy,"满"+serviceCouponEntity.getFace_amount().replace(".00","")+"元使用");
        holder.setText(R.id.tv_jd,serviceCouponEntity.getServiceName());
        holder.setText(R.id.tv_time,serviceCouponEntity.getEffective_date().replace(" 00:00:00","")+"至"+serviceCouponEntity.getExpire_date().replace(" 00:00:00",""));

    }
}
