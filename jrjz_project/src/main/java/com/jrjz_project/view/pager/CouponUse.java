package com.jrjz_project.view.pager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.adapter.OrderAdapter;
import com.jrjz_project.base.BasePager;
import com.jrjz_project.bean.OrderStatusCate;
import com.jrjz_project.utils.OkHttpRequest;
import com.jrjz_project.utils.SPUtil;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

/**
 * Created by John_Libo on 2016/8/2.
 */
public class CouponUse extends BasePager{

    private ListView lv_order;
    private View view;
    private String url;
    public CouponUse(Context context, String flag){
        super(context);
    }
    @Override
    public View initView() {
        view=View.inflate(context,R.layout.order_all_item,null);
        return view;
    }

    @Override
    public void initData() {
        lv_order=(ListView) view.findViewById(R.id.lv_order);
        String result = SPUtil.getString(context,
                url, "");
//        if (!result.isEmpty()) {
//            processData(result);
//        }
//
//        getNewData(url);
    }

    @Override
    public void initTitleView(View view) {
        super.initTitleView(view);
    }

    public  void processData(String result){
        //用Gson解析数据
//        GsonUtils.changeGsonToBean(result,)
//        OrderAdapter orderAdapter=new OrderAdapter(context, new ArrayList<OrderStatusCate>());
        OrderAdapter orderAdapter=new OrderAdapter(context, new ArrayList<String>());
        lv_order.setAdapter(orderAdapter);


    }

    /**
     * 获取最新的数据
     * @param url
     */
    public void getNewData(String url){
        FormBody formBody=new FormBody.Builder()
                .add("","")
                .add("","")
                .build();
        OkHttpRequest.getInstall().enqueue(OkHttpRequest.Request(url, formBody), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"请求失败，请稍后重试~~！",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resultStr=response.body().string();
                Message msg=new Message();
                msg.what=1;
                Bundle bundle=new Bundle();
                bundle.putString("result",resultStr);
                msg.setData(bundle);

            }
        });
    }
}
