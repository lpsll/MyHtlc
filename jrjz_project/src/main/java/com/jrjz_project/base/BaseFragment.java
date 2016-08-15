package com.jrjz_project.base;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public abstract class BaseFragment extends Fragment {
	public boolean is_load  =  false;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = initView(inflater);
		return view;
	}
	
	

	public abstract View initView(LayoutInflater inflater);
	public abstract void initTitleView();


	public View getRootView(){
	    return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onActivityCreated(savedInstanceState);
		initData();
	}
	public abstract void initData() ;
	public Context context = null;
	private View view;
//	public SlidingMenu sm;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.context = getActivity();
		initTitleView();
//		sm = ((MainActivity)getActivity()).getSlidingMenu();
	}
	
	
}
