package com.autodesk.easyhome.shejijia.common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.common.bean.ViewFlowBean;
import com.autodesk.easyhome.shejijia.common.utils.ImageLoaderUtils;
import com.autodesk.easyhome.shejijia.common.utils.LayoutUtil;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.TDevice;
import com.autodesk.easyhome.shejijia.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 轮播图
 */
public class ViewFlowLayout extends RelativeLayout {

    private Context context_;
    private ViewFlipper flipper;
    private LinearLayout linear;
    private float startX;

    public ViewFlowLayout(Context context) {
        super(context);
        this.context_ = context;
        initLayout();
    }

    public ViewFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context_ = context;
        initLayout();
    }

    private void initLayout() {
        LayoutInflater inflater = LayoutInflater.from(context_);
        View viewfolw = inflater.inflate(R.layout.view_viewflow_layout, null);
        addView(viewfolw, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        flipper = (ViewFlipper) viewfolw.findViewById(R.id.id_vfow_flipper);
        linear = (LinearLayout) viewfolw.findViewById(R.id.id_vfow_linear);

    }

    private void startListen() {
        autoScrollHandler.removeCallbacks(runnable);
        autoScrollHandler.postDelayed(runnable, 3000);
    }

    private Handler autoScrollHandler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            myShowNext();
        }
    };

    private void myShowNext() {
        flipper.setInAnimation(context_, R.anim.push_left_in);
        flipper.setOutAnimation(context_, R.anim.push_left_out);
        flipper.showNext();
        setupDotImage();
        startListen();
    }

    private void myShowPrevious() {
        flipper.setInAnimation(context_, R.anim.push_right_in);
        flipper.setOutAnimation(context_, R.anim.push_right_out);
        flipper.showPrevious();
        setupDotImage();
        startListen();
    }

    private void setupDotImage() {
        int currentIndex = flipper.getDisplayedChild();
        if (linear.getChildCount() != 0) {
            for (int i = 0; i < linear.getChildCount(); i++) {
                if (i == currentIndex) {
                    ((ImageView) linear.getChildAt(i)).setImageResource(R.drawable.discount_dot_sel);
                } else {
                    ((ImageView) linear.getChildAt(i)).setImageResource(R.drawable.discount_dot_unsel);
                }

            }
        }

    }


    int count,opintion;
    boolean isfirst = true;
    HashMap map ;
    private  String imgUrl;
    boolean flag = false;


    public void updateView(final ArrayList<ViewFlowBean> beans) {
        flipper.removeAllViews();
        linear.removeAllViews();
        map = new HashMap();
        final int size = beans.size();
        ImageView imageView = new ImageView(context_);
        LogUtils.e("size---",""+size);
        for (int i = 0; i < size; i++) {
            final ViewFlowBean bean = beans.get(i);
            imageView.setTag(bean);
            imgUrl = bean.getImgUrl();


            ImageLoader.getInstance().loadImage(bean.getImgUrl(), ImageLoaderUtils.getDefaultOptions(), new SimpleImageLoadingListener() {

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                    int screenWidth = AppContext.get("screenWidth", 0);
                    if (isfirst) {
                        isfirst = false;
                        LayoutUtil.reMesureHeight(ViewFlowLayout.this,
                                screenWidth,
                                TDevice.px2dip(getContext(), loadedImage.getHeight()),
                                TDevice.px2dip(getContext(), loadedImage.getWidth()));
                        LayoutUtil.reMesureHeight(flipper,
                                screenWidth,
                                TDevice.px2dip(getContext(), loadedImage.getHeight()),
                                TDevice.px2dip(getContext(), loadedImage.getWidth()));
                    }

                    imgUrl = imageUri;
                    map.put(imgUrl, loadedImage);
                    count++;
                    opintion++;
                    LogUtils.e("map----",""+map);
                    LogUtils.e("count----map--",""+count);

                    ImageView dot = new ImageView(context_);
                    MarginLayoutParams lp = new LinearLayout.LayoutParams(18, 18);
                    lp.rightMargin = 20;
                    dot.setLayoutParams(lp);
                    dot.setImageResource(R.drawable.discount_dot_unsel);
                    linear.addView(dot);

                    if (count == size) {
                        for (int i = 0; i < map.size(); i++) {
                            ImageView imageView = new ImageView(context_);
                            imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                            imageView.setImageBitmap((Bitmap) map.get(beans.get(i).getImgUrl()));
                            flipper.addView(imageView);
                        }


                        if(size ==1||map.size()==1){
                            linear.setVisibility(View.GONE);
                            count =0;
                        }else {
                            linear.setVisibility(View.VISIBLE);
                            flipper.setDisplayedChild(0);
                            ((ImageView) linear.getChildAt(0)).setImageResource(R.drawable.discount_dot_sel);
                            //图片全部加载完毕
                            startListen();
                            count =0;
                        }


                    }


                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    super.onLoadingFailed(imageUri, view, failReason);
                    LogUtils.e("onLoadingFailed----", "onLoadingFailed");
                    count++;
                    flag = true;
                    if (count == size) {
                            for (int k = 0; k < map.size(); k++) {
                                ImageView imageView = new ImageView(context_);
                                imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                                imageView.setImageBitmap((Bitmap) map.get(beans.get(k).getImgUrl()));
                                flipper.addView(imageView);
                            }


                            if (size == 1 || map.size() == 1) {
                                linear.setVisibility(View.GONE);
                                count = 0;
                            } else {
                                linear.setVisibility(View.VISIBLE);
                                flipper.setDisplayedChild(0);
                                ((ImageView) linear.getChildAt(0)).setImageResource(R.drawable.discount_dot_sel);
                                //图片全部加载完毕
                                startListen();
                                count = 0;
                            }
                    }
                }
            });


            }

    }



    public void setLoadCompleteListener(LoadCompleteListener loadCompleteListener) {
        this.loadCompleteListener = loadCompleteListener;
    }

    LoadCompleteListener loadCompleteListener;
    public interface  LoadCompleteListener{
        void loadComplete();
    }

    int cc=0;
    int ccsize=0;

    public void checkSplView(){
        if(cc==ccsize&&loadCompleteListener!=null){
            loadCompleteListener.loadComplete();
            startListen();
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (event.getX() - startX > 100) {
                    myShowPrevious();
                } else if (startX - event.getX() > 100) {
                    myShowNext();
                } else if (Math.abs(startX - event.getX()) <= 10) {
                    if (onItemClickListener != null) {
                        int position = flipper.getDisplayedChild();
                        View itemView = flipper.getChildAt(position);
                        if (itemView != null) {
                            onItemClickListener.onItemClick(position);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                break;
        }
        return true;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
