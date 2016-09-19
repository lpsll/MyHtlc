package com.autodesk.easyhome.shejijia.home.adapter;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.home.entity.NewsEntity;
import com.autodesk.easyhome.shejijia.order.entity.OrderEntity;
import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;

/**
 * Created by John_Libo on 2016/9/9.
 */
public class NewsAdapter extends BaseSimpleRecyclerAdapter<NewsEntity> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_news;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, NewsEntity newsEntity, int position) {
        ImageView tv = holder.getView(R.id.tv04);
        holder.setText(R.id.tv01,newsEntity.getSmtitle());
        holder.setText(R.id.tv02,newsEntity.getSmcontent());
        holder.setText(R.id.tv03,newsEntity.getCreatedate());
        if(newsEntity.getReadflag().equals("0")){
            tv.setVisibility(View.VISIBLE);
        }else {
            tv.setVisibility(View.GONE);
        }
    }


}
