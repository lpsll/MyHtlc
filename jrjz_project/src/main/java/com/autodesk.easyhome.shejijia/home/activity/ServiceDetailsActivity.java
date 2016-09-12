package com.autodesk.easyhome.shejijia.home.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.DialogUtils;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.PhotoSystemUtils;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.autodesk.easyhome.shejijia.home.entity.DfaultEntity;
import com.autodesk.easyhome.shejijia.home.entity.DfaultResult;
import com.lidong.photopicker.PhotoPickerActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 服务详情页
 */
public class ServiceDetailsActivity extends BaseTitleActivity {
    @Bind(R.id.img_housekeeping_detail)
    ImageView imgHousekeepingDetail;
    @Bind(R.id.tv_housekeeping_detail_title)
    TextView tvHousekeepingDetailTitle;
    @Bind(R.id.tv_housekeeping_detail_provider)
    TextView tvHousekeepingDetailProvider;
    @Bind(R.id.tv_housekeeping_detail_price)
    TextView tvHousekeepingDetailPrice;
    @Bind(R.id.tv_housekeeping_detail_shop_name)
    TextView tvHousekeepingDetailShopName;
    @Bind(R.id.ll_housekeeping_detail_contact)
    LinearLayout llHousekeepingDetailContact;
    @Bind(R.id.add_tv01)
    TextView addTv01;
    @Bind(R.id.add_tv02)
    TextView addTv02;
    @Bind(R.id.add_tv03)
    TextView addTv03;
    @Bind(R.id.add_tv04)
    TextView addTv04;
    @Bind(R.id.lin_address)
    LinearLayout linAddress;
    @Bind(R.id.tv_housekeeping_detail_sevice_type)
    TextView tvHousekeepingDetailSeviceType;
    @Bind(R.id.ll_housekeeping_detail_sevice_type)
    LinearLayout llHousekeepingDetailSeviceType;
    @Bind(R.id.tv_housekeeping_detail_sevice_time)
    TextView tvHousekeepingDetailSeviceTime;
    @Bind(R.id.rl_housekeeping_detail_sevice_time)
    RelativeLayout rlHousekeepingDetailSeviceTime;
    @Bind(R.id.tv_housekeeping_detail_special_request)
    TextView tvHousekeepingDetailSpecialRequest;
    @Bind(R.id.rl_housekeeping_detail_special_request)
    RelativeLayout rlHousekeepingDetailSpecialRequest;
    @Bind(R.id.tv_housekeeping_detail_ok)
    TextView tvHousekeepingDetailOk;
    private String mSelName,mSelPhone,mSelAddress,mTm;
    boolean login;
    DfaultEntity data;

    @Override
    protected int getContentResId() {
        return R.layout.housekeeping_detail;
    }

    @Override
    public void initView() {
        setTitleText("服务详情");
        login = AppContext.get("IS_LOGIN",false);
        if(login) {
            reqDfault();//获取默认地址
        }
    }

    @Override
    public void initData() {

    }

    private void reqDfault() {
        BaseDTO dto = new BaseDTO();
        dto.setUid(AppContext.get("uid",""));
        CommonApiClient.dfault(this, dto, new CallBack<DfaultResult>() {
            @Override
            public void onSuccess(DfaultResult result) {
                if(AppConfig.NOTHING.equals(result.getCode())){
                    LogUtils.e("无默认地址");

                }
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("获取默认地址成功");
                    bindDfault(result);
                }

            }
        });
    }
    private void bindDfault(DfaultResult result) {
        data = result.getData();
        addTv01.setText(data.getName());
        addTv02.setText(data.getMobile());
        addTv04.setText(data.getCity()+data.getDistrict()+data.getArea()+data.getAddress());
    }

    @OnClick({R.id.lin_address, R.id.rl_housekeeping_detail_sevice_time, R.id.rl_housekeeping_detail_special_request, R.id.tv_housekeeping_detail_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_address:
                if(login){
                    HomeUiGoto.gotoSdSelect(this);
                }else {
                    DialogUtils.confirm(this, "您尚未登录，是否去登录？", listener);

                }

                break;
            case R.id.rl_housekeeping_detail_sevice_time:
                HomeUiGoto.gotoSdSt(this);
                break;
            case R.id.rl_housekeeping_detail_special_request:
                break;
            case R.id.tv_housekeeping_detail_ok:
                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
        }
    }

    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            HomeUiGoto.gotoLogin(ServiceDetailsActivity.this);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.e("data--------", "" + data);
        switch (requestCode) {
            case HomeUiGoto.SD_REQUEST:
                //选择地址
                if(resultCode==00001){

                    if(TextUtils.isEmpty(AppContext.get("mSelName",""))){
                        return;
                    }else
                    {
                        mSelName = AppContext.get("mSelName","");
                        mSelPhone = AppContext.get("mSelPhone","");
                        mSelAddress = AppContext.get("mSelAddress","");
                        addTv01.setText(mSelName);
                        addTv02.setText(mSelPhone);
                        addTv04.setText(mSelAddress);

                    }
                }
                //选择时间
                else if(resultCode==12){
                    mTm = AppContext.get("serviceTime","");
                    tvHousekeepingDetailSeviceTime.setText(mTm);
                }

                break;

            default:
                break;

        }

    }

}
