package com.jrjz_project.base;

import java.util.List;


import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.htlc.jrjz.jrjz_project.R;

public abstract class MyBaseAdapter<T,Q> extends BaseAdapter {

	
	public Context context;
	
	public View q;
	
	public List<T> lists;
	public String[] serviceArray;
	
//	public SelfInfoCarePager notInfoCarepCarePager;
	
	
	
	
	public MyBaseAdapter(Context context, List<T> lists) {
		super();
		this.context = context;
		this.lists = lists;
	}
//	public MyBaseAdapter(Context context, List<T> lists,SelfInfoCarePager notInfoCarepCarePager) {
//		super();
//		this.context = context;
//		this.lists = lists;
//		this.notInfoCarepCarePager=notInfoCarepCarePager;
//	}

	public MyBaseAdapter(Context context, View q, List<T> lists) {
		super();
		this.context = context;
		this.q = q;
		this.lists = lists;
	}

     //扩大View的触摸和点击响应范围,最大不超过其父View范围 
    public static void expandViewTouchDelegate(final View view, final int top,  
            final int bottom, final int left, final int right) {  
  
        ((View) view.getParent()).post(new Runnable() {  
            @Override  
            public void run() {  
                Rect bounds = new Rect();  
                view.setEnabled(true);  
                view.getHitRect(bounds);  
  
                bounds.top -= top;  
                bounds.bottom += bottom;  
                bounds.left -= left;  
                bounds.right += right;  
  
                TouchDelegate touchDelegate = new TouchDelegate(bounds, view);  
  
                if (View.class.isInstance(view.getParent())) {  
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);  
                }  
            }  
        });  
    }
    
	public MyBaseAdapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

}
