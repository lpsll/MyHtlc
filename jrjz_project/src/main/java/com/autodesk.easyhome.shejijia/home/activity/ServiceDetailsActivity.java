package com.autodesk.easyhome.shejijia.home.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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
import com.autodesk.easyhome.shejijia.common.utils.ImageLoaderUtils;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.autodesk.easyhome.shejijia.home.dto.AppointmentDTO;
import com.autodesk.easyhome.shejijia.home.entity.AddAddressResult;
import com.autodesk.easyhome.shejijia.home.entity.DfaultEntity;
import com.autodesk.easyhome.shejijia.home.entity.DfaultResult;
import com.autodesk.easyhome.shejijia.order.OrderUiGoto;

import butterknife.Bind;
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
    LinearLayout llHousekeepingDetailSeviceType;
    @Bind(R.id.tv_housekeeping_detail_sevice_time)
    TextView tvHousekeepingDetailSeviceTime;
    @Bind(R.id.rl_housekeeping_detail_sevice_time)
    RelativeLayout rlHousekeepingDetailSeviceTime;
    @Bind(R.id.tv_housekeeping_detail_special_request)
    EditText tvHousekeepingDetailSpecialRequest;
    @Bind(R.id.rl_housekeeping_detail_special_request)
    RelativeLayout rlHousekeepingDetailSpecialRequest;
    @Bind(R.id.tv_housekeeping_detail_ok)
    TextView tvHousekeepingDetailOk;
    private String mSelName,mSelPhone,mSelAddress,mTm;
    boolean login;
    DfaultEntity data;
    private String img,descr,price,id,name,prejectName,phone;

    @Override
    protected int getContentResId() {
        return R.layout.housekeeping_detail;
    }

    @Override
    public void initView() {
        String title= getIntent().getBundleExtra("bundle").getString("title");

        setTitleText(title);
        login = AppContext.get("IS_LOGIN",false);
        name= getIntent().getBundleExtra("bundle").getString("name");
        img = getIntent().getBundleExtra("bundle").getString("img");
        descr = getIntent().getBundleExtra("bundle").getString("descr");
        price = getIntent().getBundleExtra("bundle").getString("price");
        id= getIntent().getBundleExtra("bundle").getString("id");
        phone= getIntent().getBundleExtra("bundle").getString("phone");
        prejectName= getIntent().getBundleExtra("bundle").getString("preject");
        tvHousekeepingDetailTitle.setText(descr);
        tvHousekeepingDetailPrice.setText("¥"+price);
//        tvHousekeepingDetailShopName.setText(name);
        tvHousekeepingDetailProvider.setText("本服务由["+name+""+"]提供");
        ImageLoaderUtils.displayImage(img, imgHousekeepingDetail);
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

    @OnClick({R.id.tv_housekeeping_detail_shop_name,R.id.lin_address, R.id.rl_housekeeping_detail_sevice_time, R.id.rl_housekeeping_detail_special_request, R.id.tv_housekeeping_detail_ok, R.id.ll_housekeeping_detail_contact})
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
                if(addTv02.getText().toString().equals("")){
                    DialogUtils.showPrompt(this, "提示","请选择地址", "知道了");
                }
                else if(tvHousekeepingDetailSeviceTime.getText().toString().equals("")||tvHousekeepingDetailSeviceTime.getText().toString().equals("请选择服务时间")){
                    DialogUtils.showPrompt(this, "提示","请选择时间", "知道了");
                }
                else {
                    reqAppointment();//预约
                }

                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
            case R.id.ll_housekeeping_detail_contact:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                        + phone));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

            case R.id.tv_housekeeping_detail_shop_name:

                Bundle bundle = new Bundle();
                bundle.putString("url","http://101.200.167.130:8080/jrjz-api/c/service/detail?id="+id);
                HomeUiGoto.gotoBrowser(this,bundle);

                break;
        }
    }

    private void reqAppointment() {
        AppointmentDTO dto = new AppointmentDTO();
        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();
        dto.setAccessToken(AppContext.get("accessToken",""));
        dto.setRandom(random);
        dto.setUid(AppContext.get("uid",""));
        dto.setTimestamp(time);
        dto.setSign(AppContext.get("uid","")+time+random);
        dto.setCustName(addTv01.getText().toString());
        dto.setPhone(addTv02.getText().toString());
        dto.setAddress(addTv04.getText().toString());
        dto.setServiceItemId(id);
        dto.setServiceTime(mTm);
        dto.setDescr(tvHousekeepingDetailSpecialRequest.getText().toString());
        dto.setHomeVisitFee(price);
        CommonApiClient.appointment(this, dto, new CallBack<AddAddressResult>() {
            @Override
            public void onSuccess(AddAddressResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("预约成功");
                    Bundle bundle = new Bundle();
                    bundle.putString("id",result.getData());
                    AppContext.set("statu",true);
                    OrderUiGoto.gotoOrderNew(ServiceDetailsActivity.this,bundle);
                }

            }
        });
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
