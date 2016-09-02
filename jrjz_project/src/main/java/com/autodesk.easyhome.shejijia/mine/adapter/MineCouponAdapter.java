package com.autodesk.easyhome.shejijia.mine.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.mine.entity.MineCouponEntity;
import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;

/**
 * Created by John_Libo on 2016/8/30.
 */
public class MineCouponAdapter extends BaseSimpleRecyclerAdapter<MineCouponEntity> {
    private final int type;
    LinearLayout linearLayout;
    TextView tv;

    public MineCouponAdapter(int type) {
        this.type = type;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_mine_coupon;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, MineCouponEntity mineCouponEntity, int position) {
        linearLayout = holder.getView(R.id.mine_coupon_lin);
        tv = holder.getView(R.id.tv_tv);
        if(type==1){
            linearLayout.setBackgroundColor(Color.parseColor("#008ce7"));
            tv.setVisibility(View.GONE);
            holder.setText(R.id.tv_time,mineCouponEntity.getEffective_date().replace(" 00:00:00","")+"至"+mineCouponEntity.getExpire_date().replace(" 00:00:00",""));
        }
        else if(type==2){
            linearLayout.setBackgroundColor(Color.parseColor("#008ce7"));
            tv.setVisibility(View.VISIBLE);
            tv.setText("已过期");
            holder.setText(R.id.tv_time,mineCouponEntity.getExpire_date().replace(" 00:00:00","")+"已过期");
        }
        else if(type==3){
            linearLayout.setBackgroundColor(Color.parseColor("#999999"));
            tv.setVisibility(View.VISIBLE);
            tv.setText("已使用");
            holder.setText(R.id.tv_time,mineCouponEntity.getExpire_date().replace(" 00:00:00","")+"已使用");
        }
        holder.setText(R.id.tv_money,mineCouponEntity.getValue_amount().replace(".00",""));
        holder.setText(R.id.tv_sy,"满"+mineCouponEntity.getFace_amount().replace(".00","")+"元使用");
        holder.setText(R.id.tv_jd,mineCouponEntity.getServiceName());

    }
}
