package com.autodesk.easyhome.shejijia.mine.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
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
    private String effective,expire;

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
        effective = mineCouponEntity.getEffective_date().substring(0,10);
        expire = mineCouponEntity.getExpire_date().substring(0,10);
        if (type == 1) {
            if (mineCouponEntity.getAmount_level().equals("1")) {
                linearLayout.setBackgroundColor(Color.parseColor("#A4E0D6"));
            } else if (mineCouponEntity.getAmount_level().equals("2")) {
                linearLayout.setBackgroundColor(Color.parseColor("#9EE1A4"));
            } else if (mineCouponEntity.getAmount_level().equals("3")) {
                linearLayout.setBackgroundColor(Color.parseColor("#FEEDA4"));
            } else if (mineCouponEntity.getAmount_level().equals("4")) {
                linearLayout.setBackgroundColor(Color.parseColor("#FAC18C"));
            } else if (mineCouponEntity.getAmount_level().equals("5")) {
                linearLayout.setBackgroundColor(Color.parseColor("#EF4E7F"));
            }

            tv.setVisibility(View.GONE);
            holder.setText(R.id.tv_time, effective + "至" + expire);
        } else if (type == 2) {
            linearLayout.setBackgroundColor(Color.parseColor("#008ce7"));
            tv.setVisibility(View.VISIBLE);
            tv.setText("已过期");
            holder.setText(R.id.tv_time, expire + "已过期");
        } else if (type == 3) {
            linearLayout.setBackgroundColor(Color.parseColor("#999999"));
            tv.setVisibility(View.VISIBLE);
            tv.setText("已使用");
            holder.setText(R.id.tv_time, expire + "已使用");
        }
        holder.setText(R.id.tv_money, mineCouponEntity.getValue_amount().replace(".00", ""));
        holder.setText(R.id.tv_sy, "满" + mineCouponEntity.getFace_amount().replace(".00", "") + "元使用");


        LogUtils.e("有效期==========" + mineCouponEntity.getServiceName());
        holder.setText(R.id.tv_jd, mineCouponEntity.getServiceName());

    }
}
