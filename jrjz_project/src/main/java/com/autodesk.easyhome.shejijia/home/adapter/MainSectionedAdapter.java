package com.autodesk.easyhome.shejijia.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.adapter.SectionedBaseAdapter;
import com.autodesk.easyhome.shejijia.common.utils.ImageLoaderUtils;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.home.entity.ClassificationServicesEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John_Libo on 2016/8/22.
 */
public class MainSectionedAdapter extends SectionedBaseAdapter {
    private final ArrayList<Boolean> flagArray;
    private final ArrayList<String> mList;
    private final ArrayList<ArrayList<String>> sList;
    private final ArrayList<ArrayList<String>> iList;
    private Context mContext;
    List<ClassificationServicesEntity> entity;



    public MainSectionedAdapter(Context context, ArrayList<String> mList, ArrayList<ArrayList<String>> sList, ArrayList<ArrayList<String>> iList, ArrayList<Boolean> flagArray) {
        this.mContext = context;
        this.flagArray = flagArray;
        this.mList = mList;
        this.sList = sList;
        this.iList = iList;
    }

    @Override
    public Object getItem(int section, int position) {
        return sList.get(section).get(position);
    }

    @Override
    public long getItemId(int section, int position) {
        return position;
    }

    @Override
    public int getSectionCount() {
        return mList.size();
    }

    @Override
    public int getCountForSection(int section) {
        return  sList.get(section).size();
    }



    private class Holder {
        private TextView tv01,tv02;
        private LinearLayout right_item;
        private ImageView imageItem;

        public void updataView(int section, final int position) {
            LogUtils.e("updataView----",""+sList.get(section).get(position));
            if(sList.get(section).get(position).equals("")){
                tv01.setText("");
                imageItem.setVisibility(View.GONE);


            }else {
                imageItem.setVisibility(View.VISIBLE);
                tv01.setText(sList.get(section).get(position));
                ImageLoaderUtils.displayImage(iList.get(section).get(position), imageItem);
            }

//            tv02.setText(str[section][position]);
            if (flagArray.get(section)) {
                right_item.setBackgroundColor(Color.parseColor("#E0FFFF"));
            } else {
                right_item.setBackgroundColor(Color.TRANSPARENT);
            }

        }

    }

    ArrayList<String>  aList ;
    ArrayList<String>  bList ;

    @Override
    public View getItemView(final int section, final int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.right_list_item, null);
            holder.tv01 = (TextView) convertView.findViewById(R.id.rl_it_tv01);
            holder.tv02 = (TextView) convertView.findViewById(R.id.rl_it_tv02);
            holder.imageItem = (ImageView) convertView.findViewById(R.id.imageItem);
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
        ((TextView) layout.findViewById(R.id.textItem)).setText(mList.get(section));
        if (flagArray.get(section)) {
            LogUtils.e("getSectionHeaderView---flagArray--if",""+flagArray);
            ((TextView) layout.findViewById(R.id.textItem)).setBackgroundColor(Color.parseColor("#E0FFFF"));
        } else {
            LogUtils.e("getSectionHeaderView---flagArray--else",""+flagArray);
            ((TextView) layout.findViewById(R.id.textItem)).setBackgroundColor(Color.TRANSPARENT);
        }
        return layout;
    }


}
