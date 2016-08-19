package com.jrjz_project.home.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.common.base.BaseTitleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 新增地址页
 */
public class AddAddressActivity extends BaseTitleActivity {
    @Bind(R.id.et_name)
    EditText mEtName;
    @Bind(R.id.et_phone)
    EditText mEtPhone;
    @Bind(R.id.tv_city)
    Spinner mTvCity;
    @Bind(R.id.lin01)
    LinearLayout mLin01;
    @Bind(R.id.tv_are)
    Spinner mTvAre;
    @Bind(R.id.lin02)
    LinearLayout mLin02;
    @Bind(R.id.tv_huan)
    Spinner mTvHuan;
    @Bind(R.id.lin03)
    LinearLayout mLin03;
    @Bind(R.id.et_address)
    EditText mEtAddress;
    @Bind(R.id.add_btn)
    Button mAddBtn;
    private ArrayAdapter<String> mAdapterCity,mAdapterAre,mAdapterHuan;
    private List<String> city_list,are_list,huan_list;

    @Override
    protected int getContentResId() {
        return R.layout.activity_addaddress;
    }

    @Override
    public void initView() {
        setTitleText("新增地址");


        //数据
        city_list = new ArrayList<String>();
        city_list.add("北京");
        city_list.add("上海");
        city_list.add("广州");
        city_list.add("深圳");

        //将可选内容与ArrayAdapter连接
        mAdapterCity=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,city_list);
        //设置下拉列表风格
        mAdapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter添加到spinner中
        mTvCity.setAdapter(mAdapterCity);


        //数据
        are_list = new ArrayList<String>();
        are_list.add("北京");
        are_list.add("上海");
        are_list.add("广州");
        are_list.add("深圳");
        are_list.add("深圳");
        are_list.add("深圳");
        are_list.add("深圳");
        are_list.add("深圳");
        are_list.add("深圳");
        are_list.add("深圳");
        are_list.add("深圳");
        are_list.add("深圳");
        are_list.add("深圳");
        are_list.add("深圳");
        are_list.add("深圳");
        //将可选内容与ArrayAdapter连接
        mAdapterAre=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,are_list);
        //设置下拉列表风格
        mAdapterAre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter添加到spinner中
        mTvAre.setAdapter(mAdapterAre);




        //数据
        huan_list = new ArrayList<String>();
        huan_list.add("三环内");
        huan_list.add("三环到五环");
        huan_list.add("五环之外");
        //将可选内容与ArrayAdapter连接
        mAdapterHuan=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,huan_list);
        //设置下拉列表风格
        mAdapterHuan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter添加到spinner中
        mTvHuan.setAdapter(mAdapterHuan);


        mTvCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view;
                tv.setTextSize(12.0f);    //设置大小
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mTvAre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view;
                tv.setTextSize(12.0f);    //设置大小
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mTvHuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view;
                tv.setTextSize(12.0f);    //设置大小
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.lin01, R.id.lin02, R.id.lin03, R.id.add_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin01:
                break;
            case R.id.lin02:
                break;
            case R.id.lin03:
                break;
            case R.id.add_btn:
                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
        }
    }
}
