package com.autodesk.easyhome.shejijia.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.R;

import java.util.ArrayList;

/**
 * Created by John_Libo on 2016/8/22.
 */
public class LeftListAdapter extends BaseAdapter {
    private ArrayList<String> leftStr;
    ArrayList<Boolean> flagArray;
    private Context context;

    public LeftListAdapter(Context context, ArrayList<String> leftStr, ArrayList<Boolean> flagArray) {
        this.leftStr = leftStr;
        this.context = context;
        this.flagArray = flagArray;
    }

    @Override
    public int getCount() {
        return leftStr.size();
    }

    @Override
    public Object getItem(int arg0) {
        return leftStr.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        Holder holder = null;
        if (arg1 == null) {
            holder = new Holder();
            arg1 = LayoutInflater.from(context).inflate(R.layout.left_list_item, null);
            holder.left_list_item = (TextView) arg1.findViewById(R.id.left_list_item);
            holder.left_view = (View) arg1.findViewById(R.id.left_view);
            arg1.setTag(holder);
        } else {
            holder = (Holder) arg1.getTag();
        }
        holder.updataView(arg0);
        return arg1;
    }

    private class Holder {
        private TextView left_list_item;
        private View left_view;

        public void updataView(final int position) {
            left_list_item.setText(leftStr.get(position));
            if (flagArray.get(position)) {
                left_list_item.setBackgroundColor(Color.parseColor("#E0FFFF"));
                left_list_item.setTextColor(Color.parseColor("#008ce7"));
                left_view.setVisibility(View.VISIBLE);
            } else {
                left_list_item.setBackgroundColor(Color.TRANSPARENT);
                left_list_item.setTextColor(Color.parseColor("#333333"));
                left_view.setVisibility(View.GONE);
            }
        }

    }
}
