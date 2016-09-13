package com.autodesk.easyhome.shejijia.order.adapter;

import android.graphics.Color;
import android.widget.LinearLayout;

import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.order.entity.ServiceCouponEntity;
import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;

/**
 * Created by John_Libo on 2016/8/18.
 */
public class CouponAdapter extends BaseSimpleRecyclerAdapter<ServiceCouponEntity> {
    LinearLayout linearLayout;

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_coupon;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, ServiceCouponEntity serviceCouponEntity, int position) {
        linearLayout = holder.getView(R.id.lin);
        if(serviceCouponEntity.getAmount_level().equals("1")){
            linearLayout.setBackgroundColor(Color.parseColor("#A4E0D6"));
        }
        else if(serviceCouponEntity.getAmount_level().equals("2")){
            linearLayout.setBackgroundColor(Color.parseColor("#9EE1A4"));
        }
        else if(serviceCouponEntity.getAmount_level().equals("3")){
            linearLayout.setBackgroundColor(Color.parseColor("#FEEDA4"));
        }
        else if(serviceCouponEntity.getAmount_level().equals("4")){
            linearLayout.setBackgroundColor(Color.parseColor("#FAC18C"));
        }
        else if(serviceCouponEntity.getAmount_level().equals("5")){
            linearLayout.setBackgroundColor(Color.parseColor("#EF4E7F"));
        }

        holder.setText(R.id.tv_money,serviceCouponEntity.getValue_amount().replace(".00",""));
        holder.setText(R.id.tv_sy,"满"+serviceCouponEntity.getFace_amount().replace(".00","")+"元使用");
        holder.setText(R.id.tv_jd,serviceCouponEntity.getServiceName());
        LogUtils.d("有效期====="+serviceCouponEntity.getEffective_date());
        holder.setText(R.id.tv_time,serviceCouponEntity.getEffective_date().replace(" 00:00:00","")+"至"+serviceCouponEntity.getExpire_date().replace(" 00:00:00",""));

    }
}
