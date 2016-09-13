package com.autodesk.easyhome.shejijia.home.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.DialogUtils;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.home.dto.AddAddressDTO;
import com.autodesk.easyhome.shejijia.home.entity.AddAddressResult;

import java.util.List;

import butterknife.Bind;
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
    private String mCity,mAre,mHuan;

    @Override
    protected int getContentResId() {
        return R.layout.activity_addaddress;
    }

    @Override
    public void initView() {
        setTitleText("新增地址");

        //将可选内容与ArrayAdapter连接
        mAdapterCity=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.city));
        //设置下拉列表风格
        mAdapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter添加到spinner中
        mTvCity.setAdapter(mAdapterCity);


        //将可选内容与ArrayAdapter连接
        mAdapterAre=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.area));
        //设置下拉列表风格
        mAdapterAre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter添加到spinner中
        mTvAre.setAdapter(mAdapterAre);


        //将可选内容与ArrayAdapter连接
        mAdapterHuan=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.range));
        //设置下拉列表风格
        mAdapterHuan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter添加到spinner中
        mTvHuan.setAdapter(mAdapterHuan);


        mTvCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view;
                tv.setTextSize(15.0f);    //设置大小
                mCity =tv.getText().toString();
                LogUtils.e("mTvCity----",""+mCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mTvAre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view;
                tv.setTextSize(15.0f);    //设置大小
                mAre =tv.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mTvHuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view;
                tv.setTextSize(15.0f);    //设置大小
                mHuan =tv.getText().toString();
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
                if(mEtName.getText().toString().equals("")){
                    DialogUtils.showPrompt(this, "提示","请输入名字", "知道了");
                }
                else if(mEtPhone.getText().toString().equals("")||mEtPhone.getText().toString().length()<11){
                    DialogUtils.showPrompt(this, "提示","请输入正确的电话号码", "知道了");
                }
                else if(mEtAddress.getText().toString().equals("")){
                    DialogUtils.showPrompt(this, "提示","请输入地址", "知道了");
                }
                else {
                    reqAdd();//新增地址
                }
                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
        }
    }

    private void reqAdd() {
        AddAddressDTO dto = new AddAddressDTO();
        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();
        dto.setAccessToken(AppContext.get("accessToken", ""));
        dto.setUid(AppContext.get("uid", ""));
        dto.setTimestamp(time);
        dto.setRandom(random);
        dto.setSign(AppContext.get("uid", "")+time+random);
        dto.setName(mEtName.getText().toString());
        dto.setMobile(mEtPhone.getText().toString());
        dto.setCity(mCity);
        dto.setDistrict(mAre);
        dto.setArea(mHuan);
        dto.setAddress(mEtAddress.getText().toString());
        dto.setDefaultAddress("true");//默认为是，是/true ,否/false
        CommonApiClient.addAddress(this, dto, new CallBack<AddAddressResult>() {
            @Override
            public void onSuccess(AddAddressResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("新增地址成功");
                    setResult(102);
                    finish();

                }

            }
        });
    }








}
