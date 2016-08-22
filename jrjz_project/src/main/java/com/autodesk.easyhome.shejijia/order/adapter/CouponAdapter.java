package com.autodesk.easyhome.shejijia.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htlc.jrjz.jrjz_project.R;

import java.util.List;
import java.util.Map;

/**
 * Created by John_Libo on 2016/8/18.
 */
public class CouponAdapter extends BaseAdapter {
    private final Context context;
    private final List<Map<String, Object>> list;

    public CouponAdapter(Context context, List<Map<String, Object>> list) {
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
        public TextView tv1,tv2,tv3,tv4,tv5;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_coupon, null);
            holder.tv1 = (TextView)convertView.findViewById(R.id.tv_money);
            holder.tv2 = (TextView)convertView.findViewById(R.id.tv_coupon);
            holder.tv3 = (TextView)convertView.findViewById(R.id.tv_sy);
            holder.tv4 = (TextView)convertView.findViewById(R.id.tv_jd);
            holder.tv5 = (TextView)convertView.findViewById(R.id.tv_time);
            convertView.setTag(holder);

        }else {

            holder = (ViewHolder)convertView.getTag();
        }

        holder.tv1.setText((String)list.get(position).get("a"));
        holder.tv2.setText((String)list.get(position).get("b"));
        holder.tv3.setText((String)list.get(position).get("c"));
        holder.tv4.setText((String)list.get(position).get("d"));
        holder.tv5.setText((String)list.get(position).get("e"));

        return convertView;
    }
}
