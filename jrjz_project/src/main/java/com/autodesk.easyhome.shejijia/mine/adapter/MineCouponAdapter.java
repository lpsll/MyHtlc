package com.autodesk.easyhome.shejijia.mine.adapter;

import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.mine.entity.MineCouponEntity;
import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;

/**
 * Created by John_Libo on 2016/8/30.
 */
public class MineCouponAdapter extends BaseSimpleRecyclerAdapter<MineCouponEntity> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_coupon;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, MineCouponEntity mineCouponEntity, int position) {

    }
}
