package com.autodesk.easyhome.shejijia.home.adapter;

import android.app.Activity;
import android.widget.ImageView;

import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.utils.ImageLoaderUtils;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.home.entity.ServicesEntity;
import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;

import java.util.List;

/**
 * Created by John_Libo on 2016/9/12.
 */
public class HomeServiceItemAdapter extends BaseSimpleRecyclerAdapter<ServicesEntity> {
    private final List<ServicesEntity> entity;
    ImageView imageHousekeeping;

    private Activity act;

    public HomeServiceItemAdapter(Activity act,List<ServicesEntity> servicesEntity) {
        this.act = act;
        this.entity = servicesEntity;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_hs_item;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, ServicesEntity servicesEntity, int position) {
        LogUtils.e("List<ServicesEntity>---",""+servicesEntity+"");
        LogUtils.e("position---",""+position);
//        holder.setText(R.id.tv_housekeeping_item_service_name1,entity.get(position).getName());
//        holder.setText(R.id.tv_housekeeping_item_price1,"¥"+entity.get(position).getPrice());
//        imageHousekeeping = holder.getView(R.id.img_housekeeping_item_img1);
//        ImageLoaderUtils.displayImage(entity.get(position).getLogo(), imageHousekeeping);

        holder.setText(R.id.tv_housekeeping_item_service_name1,servicesEntity.getName());
        holder.setText(R.id.tv_housekeeping_item_price1,"¥"+servicesEntity.getPrice());
        imageHousekeeping = holder.getView(R.id.img_housekeeping_item_img1);
        ImageLoaderUtils.displayImage(servicesEntity.getLogo(), imageHousekeeping);

    }
}
