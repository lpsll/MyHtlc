package com.autodesk.easyhome.shejijia.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.htlc.jrjz.jrjz_project.R;

import java.util.List;
import java.util.Map;

/**
 * Created by John_Libo on 2016/8/16.
 */
public class SelectAddressAdapter extends BaseAdapter{
    private final Context context;
    private final List<Map<String, Object>> list;
    private  boolean flag;

    public SelectAddressAdapter(Context context, List<Map<String, Object>> list) {
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
        public TextView tv1,tv2,tv3,tv4;
        public LinearLayout mLinSz;
        public TextView mTvCk;
        public CheckBox mCb;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_select_address, null);
            holder.tv1 = (TextView)convertView.findViewById(R.id.add_tv01);
            holder.tv2 = (TextView)convertView.findViewById(R.id.add_tv02);
            holder.tv3 = (TextView)convertView.findViewById(R.id.add_tv03);
            holder.tv4 = (TextView)convertView.findViewById(R.id.add_tv04);
            holder.mTvCk = (TextView)convertView.findViewById(R.id.tv_address);
            holder.mCb = (CheckBox)convertView.findViewById(R.id.box);
            holder.mLinSz = (LinearLayout)convertView.findViewById(R.id.lin_shezhi);
            convertView.setTag(holder);

        }else {

            holder = (ViewHolder)convertView.getTag();
        }

        holder.tv1.setText((String)list.get(position).get("name"));
        holder.tv2.setText((String)list.get(position).get("title"));
        holder.tv3.setText((String)list.get(position).get("info"));
        holder.tv4.setText((String)list.get(position).get("add"));

        flag = false;
        holder.mLinSz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){

                }
            }
        });

        return convertView;
    }

}
