package com.jrjz_project.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.htlc.jrjz.jrjz_project.R;

import java.util.List;

/**
 * Created by John_Libo on 2016/8/18.
 */
public class NewAdapter extends BaseAdapter {
    private final Context context;
    private final List<String> aList;


    public NewAdapter(Context context, List<String> aList) {
        this.context = context;
        this.aList = aList;
    }

    @Override
    public int getCount() {
        return aList.size();
    }

    @Override
    public Object getItem(int position) {
        return aList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder
    {
        public TextView tv1,tv2;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_item_cf, null);
            holder.tv1 = (TextView)convertView.findViewById(R.id.item_tv1);
            holder.tv2 = (TextView)convertView.findViewById(R.id.item_tv2);
            convertView.setTag(holder);

        }else {

            holder = (ViewHolder)convertView.getTag();
        }
        holder.tv1.setText(aList.get(position));



        return convertView;
    }
}
