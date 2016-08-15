package com.jrjz_project.base;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.htlc.jrjz.jrjz_project.R;

public abstract class BasePager implements OnClickListener {

	public boolean is_load  =  false;
	
	public View view;
    public Context context;
    public TextView txt_title;
//	private HttpUtils httpUtils;
	public BasePager(Context context) {
		super();
		this.context = context;
		view = initView();
	}

	public void initTitleView(View view) {
//		Button btn_left = (Button) view.findViewById(R.id.btn_left);
//		btn_left.setVisibility(View.GONE);
//		ImageButton imgbtn_left = (ImageButton) view.findViewById(R.id.imgbtn_left);
////		imgbtn_left.setImageResource(R.drawable.img_menu);
//		txt_title = (TextView) view.findViewById(R.id.txt_title);
//		ImageButton btn_right = (ImageButton) view.findViewById(R.id.btn_right);
//		btn_right.setVisibility(View.VISIBLE);
//
//		if(imgbtn_left != null){
//			imgbtn_left.setOnClickListener(this);
//		}
	}
	
//	public void load(HttpMethod method,String url,RequestParams params,RequestCallBack callBack){
//		System.out.println("====jinlaile");
//		if(httpUtils == null){
//			httpUtils = new HttpUtils();
//		}
//		if(params == null){
//			params = new RequestParams();
//		}
//		System.out.println("====jinlaile111");
////		int networkAvailable = CommonUtil.isNetworkAvailable(context);
////		System.out.println("====networkavailabel");
////		if(networkAvailable == 0){
////			System.out.println("=====test_network");
////			Toast.makeText(context, "请设置网络", 0).show();
////		}else{
////			System.out.println("=====发送send请求");
////			httpUtils.send(method, url, params, callBack);
////		}
//
//		httpUtils.send(method, url, params, callBack);
//	}
	
	
	public abstract View initView();
	
	public View getRootView(){
		System.out.println("serwe");
		return view;
	}

	public abstract void initData() ;
	@Override
	public void onClick(View v) {
		
		
	}
}
