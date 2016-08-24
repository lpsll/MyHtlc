package com.autodesk.easyhome.shejijia.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.htlc.jrjz.jrjz_project.R;
import com.autodesk.easyhome.shejijia.common.adapter.SectionedBaseAdapter;

/**
 * Created by John_Libo on 2016/8/22.
 */
public class MainSectionedAdapter extends SectionedBaseAdapter {
    private final String[][] str;
    private final String[] img;
    private final boolean[] flagArray;
    private Context mContext;
    private String[] leftStr;
    private String[][] rightStr;

    public MainSectionedAdapter(Context context, String[] leftStr, String[][] str, String[][] rightStr, String[] img, boolean[] flagArray) {
        this.mContext = context;
        this.leftStr = leftStr;
        this.rightStr = rightStr;
        this.str = str;
        this.img = img;
        this.flagArray = flagArray;
    }

    @Override
    public Object getItem(int section, int position) {
        return rightStr[section][position];
    }

    @Override
    public long getItemId(int section, int position) {
        return position;
    }

    @Override
    public int getSectionCount() {
        return leftStr.length;
    }

    @Override
    public int getCountForSection(int section) {
        return rightStr[section].length;
    }



    private class Holder {
        private TextView tv01,tv02;
        private LinearLayout right_item;

        public void updataView(int section, final int position) {
            tv01.setText(rightStr[section][position]);
            tv02.setText(str[section][position]);
//            if (flagArray[section]) {
//                LogUtils.e("updataView---flagArray--if",""+flagArray+section);
//                right_item.setBackgroundColor(Color.parseColor("#bbffff"));
//            } else {
//                LogUtils.e("updataView---flagArray--else",""+flagArray+section);
//                right_item.setBackgroundColor(Color.TRANSPARENT);
//            }

        }

    }

    @Override
    public View getItemView(final int section, final int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.right_list_item, null);
            holder.tv01 = (TextView) convertView.findViewById(R.id.rl_it_tv01);
            holder.tv02 = (TextView) convertView.findViewById(R.id.rl_it_tv02);
            holder.right_item = (LinearLayout) convertView.findViewById(R.id.right_item);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.updataView(section,position);

        return convertView;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        LinearLayout layout = null;
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (LinearLayout) inflator.inflate(R.layout.header_item, null);
        } else {
            layout = (LinearLayout) convertView;
        }
        layout.setClickable(false);
        ((TextView) layout.findViewById(R.id.textItem)).setText(leftStr[section]);
        if (flagArray[section]) {
            LogUtils.e("getSectionHeaderView---flagArray--if",""+flagArray);
            ((TextView) layout.findViewById(R.id.textItem)).setBackgroundColor(Color.parseColor("#bbffff"));
        } else {
            LogUtils.e("getSectionHeaderView---flagArray--else",""+flagArray);
            ((TextView) layout.findViewById(R.id.textItem)).setBackgroundColor(Color.TRANSPARENT);
        }
        return layout;
    }
}
