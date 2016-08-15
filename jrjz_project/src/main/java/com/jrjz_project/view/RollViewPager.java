package com.jrjz_project.view;

import java.util.List;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.news.DetailActivity;
//import com.example.news.R;
//import com.example.news.menu.NewCategoryPager;
import com.htlc.jrjz.jrjz_project.R;
import com.lidroid.xutils.BitmapUtils;

public class RollViewPager extends ViewPager {
	public Context context = null;
    private List<View> mDotLists;
	

	public RollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected void onDetachedFromWindow() {
		handler.removeCallbacksAndMessages(null);
		super.onDetachedFromWindow();
	}
     private MyOnClickListener myOnClickListener;
	// 长 * 宽 * 像素字节数
	public RollViewPager(Context context, List<View> mDotLists, MyOnClickListener myOnClickListener2) {
		super(context);
		this.context = context;
		this.mDotLists  =  mDotLists;
		bitmapUtils = new BitmapUtils(context);
		bitmapUtils.configDefaultBitmapConfig(Config.ARGB_4444);
		taskRunnable = new TaskRunnable();
		myOnTouchListener = new MyOnTouchListener();
		RollViewPager.this.setOnTouchListener(myOnTouchListener);
        this.myOnClickListener = myOnClickListener2;
	}
	public interface MyOnClickListener{
		public void pagerOnClickListener(int position);
	}
	
	private class MyOnTouchListener implements OnTouchListener{

		private long downTime;
		private float x;

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				System.out.println("哥没有执行");
				downTime = System.currentTimeMillis();
				handler.removeCallbacksAndMessages(null);
				x = event.getX();
				break;

			case MotionEvent.ACTION_UP:
				long duration = System.currentTimeMillis() - downTime;
				float currentX = event.getX();
//				if(duration < 500){
				if(duration < 80){
					System.out.println("点击事件");
					myOnClickListener.pagerOnClickListener(mCurrentPosition);
				}
               startRoll();
				break;
			}
			return false;
		}
		
	}
	
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
//			getParent().requestDisallowInterceptTouchEvent(true);
			downX = ev.getX();
			downY = ev.getY();
			
			break;

		case MotionEvent.ACTION_MOVE:
			float currentX = ev.getX();
			float currentY = ev.getY();
			if(Math.abs(currentX - downX) > Math.abs(currentY - downY)){
				getParent().requestDisallowInterceptTouchEvent(true);
			}else{
				getParent().requestDisallowInterceptTouchEvent(false);
			}
		
			break;
		}
		
		return super.dispatchTouchEvent(ev);
	}
	

	private class TaskRunnable implements Runnable {
		@Override
		public void run() {

			mCurrentPosition = (mCurrentPosition + 1) % imageLists.size();
			handler.obtainMessage().sendToTarget();
		}
	}

	private List<String> imageLists;

	public void setImageLists(List<String> imageLists) {
		// TODO Auto-generated method stub
		this.imageLists = imageLists;
	}

	private List<String> titleLists;
	private TextView top_news_title;
	private BitmapUtils bitmapUtils;

	public void setTitleLists(TextView top_news_title, List<String> titleLists) {
		this.top_news_title = top_news_title;
		this.titleLists = titleLists;

		if (top_news_title != null && titleLists != null
				&& titleLists.size() > 0) {
			top_news_title.setText(titleLists.get(0));
		}
	}

	private boolean flag = false;

	// 可以滚动的viewpager
	public void startRoll() {
		if (!flag) {
			flag = true;
			ViewPagerAdapter adapter = new ViewPagerAdapter();
			RollViewPager.this.setAdapter(adapter);
			RollViewPager.this
					.setOnPageChangeListener(new MyOnPageChangeListener());
		}
		handler.postDelayed(taskRunnable, 5000);
	}

	private int mCurrentPosition = 0; 
	private int oldPosition = 0;

	private class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int position) {
			mCurrentPosition = position;
           
			if(top_news_title != null && titleLists!= null && titleLists.size() > 0){
				top_news_title.setText(titleLists.get(position));
			}
			
			if(mDotLists != null){
				mDotLists.get(position).setBackgroundResource(R.mipmap.dot_focus);
				mDotLists.get(oldPosition).setBackgroundResource(R.mipmap.dot_normal);
			}
			oldPosition = position;
		}

	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			RollViewPager.this.setCurrentItem(mCurrentPosition);
			startRoll();
		};
	};
	private TaskRunnable taskRunnable;
	private float downX;
	private float downY;
	private MyOnTouchListener myOnTouchListener;

	private class ViewPagerAdapter extends PagerAdapter {

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((ImageView) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = View.inflate(context, R.layout.viewpager_item, null);
			ImageView image = (ImageView) view.findViewById(R.id.image);
			bitmapUtils.display(image, imageLists.get(position));
			container.addView(image);
			return image;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageLists.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

	}
}
