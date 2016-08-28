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

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.widget.PinnedHeaderListView;
import com.autodesk.easyhome.shejijia.home.activity.ClassificationActivity;
import com.autodesk.easyhome.shejijia.home.entity.ClassificationEntity;
import com.autodesk.easyhome.shejijia.home.entity.ClassificationServicesEntity;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.adapter.SectionedBaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John_Libo on 2016/8/22.
 */
public class MainSectionedAdapter extends SectionedBaseAdapter {
    private final ArrayList<Boolean> flagArray;
    private final List<ClassificationEntity> data;
    private final ArrayList<String> mList;
    private Context mContext;
    List<ClassificationServicesEntity> entity;



    public MainSectionedAdapter(Context context, ArrayList<String> mList, List<ClassificationEntity> data, ArrayList<Boolean> flagArray) {
        this.mContext = context;
        this.flagArray = flagArray;
        this.mList = mList;
        this.data = data;
        aList = new ArrayList<>();
        bList = new ArrayList<>();
//        for(int i= 0;i<data.size();i++){
//            entity = data.get(i).getServices();
//            LogUtils.e("entity---",""+entity);
//            for(int j= 0;j<entity.size();j++){
//                aList.add(entity.get(j).getName());
//            }
//            for(int k= 0;k<entity.size();k++){
//                bList.add(AppConfig.BASE_IMG_URL+entity.get(k).getLogo());
//            }
//        }

    }

    @Override
    public Object getItem(int section, int position) {
        return data.get(section).getServices().get(position);
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
        return  data.size();
    }



    private class Holder {
        private TextView tv01,tv02;
        private LinearLayout right_item;

        public void updataView(int section, final int position) {
            LogUtils.e("section---",""+section);
            LogUtils.e("position---",""+position);
            LogUtils.e("updataView---",""+data.get(section).getServices().get(position).getName());
            tv01.setText( data.get(section).getServices().get(position).getName());
//            tv02.setText(str[section][position]);
            if (flagArray.get(section)) {
                right_item.setBackgroundColor(Color.parseColor("#bbffff"));
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
            ((TextView) layout.findViewById(R.id.textItem)).setBackgroundColor(Color.parseColor("#bbffff"));
        } else {
            LogUtils.e("getSectionHeaderView---flagArray--else",""+flagArray);
            ((TextView) layout.findViewById(R.id.textItem)).setBackgroundColor(Color.TRANSPARENT);
        }
        return layout;
    }


}
