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
import com.jrjz_project.common.utils.LogUtils;
import com.jrjz_project.home.activity.ClassificationActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by John_Libo on 2016/8/17.
 */
public class ClassificationAdapter extends BaseAdapter {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;
    private ArrayList<String> data = new ArrayList<String>();
    private ArrayList<String> mData = new ArrayList<String>();
    private LayoutInflater inflater;
    private TreeSet<Integer> set = new TreeSet<Integer>();

    public ClassificationAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void addItem(String item) {
        data.add(item);
    }

    public void addNewItem(String item) {
        mData.add(item);
    }

    public void addSeparatorItem(String item) {
        data.add(item);
        set.add(data.size() - 1);
    }

    public int getItemViewType(int position) {
        return set.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int type = getItemViewType(position);
        LogUtils.e("type----",""+type);
        if (convertView == null) {
            holder = new ViewHolder();
            switch (type) {
                case TYPE_SEPARATOR:
                    LogUtils.e("TYPE_ITEM----","TYPE_ITEM");
                    convertView = inflater.inflate(R.layout.item_tv, null);
                    holder.textView = (TextView) convertView
                            .findViewById(R.id.item_tv);
                    holder.textView.setText(data.get(position));
                    break;
                case TYPE_ITEM:
                    LogUtils.e("TYPE_SEPARATOR----","TYPE_SEPARATOR");
                    convertView = inflater.inflate(R.layout.item_item_cf, null);
                    holder.tv1 = (TextView) convertView
                            .findViewById(R.id.item_tv1);
                    holder.tv2 = (TextView) convertView
                            .findViewById(R.id.item_tv2);
                    holder.tv1.setText(data.get(position));
                    holder.tv2.setText(mData.get(position));
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }



    public static class ViewHolder {
        public TextView textView;
        public TextView tv1,tv2;
    }
}
