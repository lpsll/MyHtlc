package com.jrjz_project.adapter;

import java.util.List;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap.Config;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.base.MyBaseAdapter;
import com.jrjz_project.bean.OrderStatusCate;
import com.jrjz_project.view.pager.AllOrderPager;
import com.lidroid.xutils.BitmapUtils;

public class OrderAdapter extends MyBaseAdapter<String,ListView> {
    private Context context ;
    private BitmapUtils bitmapUtils;
    private AlertDialog alertDialog;
    public OrderAdapter(Context context, List<String> selfcareList) {
        super(context, selfcareList);
//        bitmapUtils=new BitmapUtils(context);
//        bitmapUtils.configDefaultBitmapConfig(Config.ARGB_4444);
        this.context=context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView==null) {
            convertView=View.inflate(context, R.layout.order_all_item, null);
        }
        return convertView;
    }

}
