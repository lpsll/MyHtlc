package com.autodesk.easyhome.shejijia.home.adapter;

import android.widget.ImageView;

import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.utils.ImageLoaderUtils;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.home.entity.HomeServiceEntity;
import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;

/**
 * Created by John_Libo on 2016/9/12.
 */
public class HomeServiceItemAdapter extends BaseSimpleRecyclerAdapter<HomeServiceEntity> {
    ImageView imageHousekeeping;
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_hs_item;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, HomeServiceEntity homeServiceEntity, int position) {
        LogUtils.e("mItemAdapter---","mItemAdapter");
        holder.setText(R.id.tv_housekeeping_item_service_name1,homeServiceEntity.getName());
        holder.setText(R.id.tv_housekeeping_item_price1,homeServiceEntity.getName());
        imageHousekeeping = holder.getView(R.id.img_housekeeping_item_img1);
        ImageLoaderUtils.displayImage(homeServiceEntity.getSiteImg(), imageHousekeeping);
    }
}
