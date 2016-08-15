package com.jrjz_project.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.base.BaseFragment;
import com.jrjz_project.utils.Common;
import com.jrjz_project.utils.Constant;
import com.jrjz_project.utils.GsonUtils;
import com.jrjz_project.utils.OkHttpRequest;
import com.jrjz_project.utils.SPUtil;
import com.jrjz_project.view.RollViewPager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

/**
 * 首页Fragment
 */
public class HomeFragment extends BaseFragment {
    private View view;
    private String url;
    private RollViewPager mViewPager;
    private List<View> mDotLists;
    private LinearLayout dots_ll;
    private TextView top_ad_title;
    private LinearLayout top_ad_viewpager;
    private List<String> imageLists;
    private List<String> titleLists;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View initView(LayoutInflater inflater) {
        view=inflater.inflate(R.layout.fragment_home1,null);
        return view;
    }

    @Override
    public void initData() {
//        String string = SPUtil.getString(context,Constant.HOME_RESULT_RES,"");
//        /**
//         * 判断网络是否可用。如果有网，则去请求最新的数据。否则加载本地缓存数据
//         */
//        if(Common.isNetworkAvailable(context)!=0){
//            getNewData(url);
//        }else{
//            parseData(string);
//            Toast.makeText(context,"网络不可用，请设置网络",Toast.LENGTH_SHORT).show();
//        }

    }


    /**
     * 每次进入页面，都要获取数据
     * @param url
     * @return
     */

    public String  getNewData(String url){
        FormBody formBody=new FormBody.Builder()
                .add("username","you name")
                .add("password","123456")
                .build();
        OkHttpRequest.getInstall().enqueue(OkHttpRequest.Request(url, formBody), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result=response.body().string();
                //保存到本地sp中
                SPUtil.saveString(context,Constant.HOME_RESULT_RES,result);
                //创建Message，发送数据到UI线程
                Message msg=new Message();
                msg.what=1;
                Bundle bundle=new Bundle();
                bundle.putString("result_str",result);
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        });
        return "";
    }

    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    String string=(String)msg.getData().get("result_str");
                    parseData(string);
            }
            return false;
        }
    });

    public void parseData(String result){
        Gson gson=new Gson();
        Common common=GsonUtils.changeGsonToBean(result,Common.class);
    }
    /**
     * 初始化顶部Title
     * @return
     */
    @Override
    public void initTitleView() {
    }

    public void processData(){
        /**
         * 轮播图代码实现
         */
        mViewPager = new RollViewPager(context, mDotLists,
                new RollViewPager.MyOnClickListener() {

                    @Override
                    public void pagerOnClickListener(
                            int position) {
                          /*
                           * 可以按照这种方法给跳动的页设置不同的点击事件
                           */
//                        if(position==0){
////										Intent intentt1=new Intent(context,DayofDessertActivity.class);
////										context.startActivity(intentt1);
//                            Intent intentt3=new Intent(context,CreativeGiftMatchActivity.class);
//                            intentt3.putExtra("flag", "");
//                            intentt3.putExtra("url", url);
//                            context.startActivity(intentt3);
//                        }else if (position==1) {
//                            Intent intentt2=new Intent(context,FoundNewProductActivity.class);
//                            intentt2.putExtra("url", url);
//                            context.startActivity(intentt2);
//                        }else if (position==2) {
//                            Intent intentt3=new Intent(context,CreativeGiftMatchActivity.class);
//                            intentt3.putExtra("flag", "");
//                            intentt3.putExtra("url", url);
//                            context.startActivity(intentt3);
//                        }else if (position==3) {
//                            Intent intentt4=new Intent(context,FoundNewProductActivity.class);
//                            intentt4.putExtra("url", url);
//                            context.startActivity(intentt4);
//                        }
                    }

                });

        // 设置图片
        mViewPager.setImageLists(imageLists);
        // 设置描述信息
        mViewPager.setTitleLists(top_ad_title, titleLists);
        // 让viewpager跳动起来
        mViewPager.startRoll();

        top_ad_viewpager.removeAllViews();
        // 把轮播图片丢到布局里面
        top_ad_viewpager.addView(mViewPager);
    }
    /**
     * 初始化跳动的点
     */
    private void initDot(int size) {
        dots_ll.removeAllViews();
        // 初始化点的集合
        mDotLists = new ArrayList<View>();
        for (int i = 0; i < size; i++) {
            // 初始化点
            View m = new View(context);
            // 设置点的宽和高
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    Common.dip2px(context, 5),
                    Common.dip2px(context, 5));
            // 设置左右的间距
            params.setMargins(5, 0, 5, 0);
            // 把宽和高。设置到view里面
            m.setLayoutParams(params);

            if (i == 0) {
                m.setBackgroundResource(R.mipmap.dot_focus);
            } else {
                m.setBackgroundResource(R.mipmap.dot_normal);
            }
            // 把点丢到布局文件里面
            dots_ll.addView(m);
            // 确保点是动态
            mDotLists.add(m);
        }
    }
}
