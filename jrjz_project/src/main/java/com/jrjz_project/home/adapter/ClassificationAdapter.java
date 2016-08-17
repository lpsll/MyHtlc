package com.jrjz_project.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.htlc.jrjz.jrjz_project.R;

import java.util.List;
import java.util.Map;

/**
 * Created by John_Libo on 2016/8/17.
 */
public class ClassificationAdapter extends BaseAdapter {
    private final Context context;
    private final List<Map<String, Object>> list;

    public ClassificationAdapter(Context context, List<Map<String, Object>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder
    {
        public ImageView img;
        public TextView tv1,tv2;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_classification, null);
            holder.tv1 = (TextView)convertView.findViewById(R.id.item_tv1);
            holder.tv2 = (TextView)convertView.findViewById(R.id.item_tv2);
            convertView.setTag(holder);

        }else {

            holder = (ViewHolder)convertView.getTag();
        }

        holder.tv1.setText((String)list.get(position).get("name"));
        holder.tv2.setText((String)list.get(position).get("title"));

        return convertView;
    }
}
