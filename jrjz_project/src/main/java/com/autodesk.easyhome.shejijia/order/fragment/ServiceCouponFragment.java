package com.autodesk.easyhome.shejijia.order.fragment;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.SplashActivity;
import com.autodesk.easyhome.shejijia.common.base.BaseListFragment;
import com.autodesk.easyhome.shejijia.common.base.SimpleActivity;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.DialogUtils;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.common.widget.PinnedHeaderListView;
import com.autodesk.easyhome.shejijia.home.entity.AddAddressResult;
import com.autodesk.easyhome.shejijia.order.adapter.CouponAdapter;
import com.autodesk.easyhome.shejijia.order.dto.ServiceCouponDTO;
import com.autodesk.easyhome.shejijia.order.entity.ServiceCouponEntity;
import com.autodesk.easyhome.shejijia.order.entity.ServiceCouponResult;
import com.lidong.photopicker.Image;
import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.OnItemClick;

/**
 * 服务优惠券
 */
public class ServiceCouponFragment  extends BaseListFragment<ServiceCouponEntity> {
    private String serviceId,menoy;
    List<String> aList = new ArrayList<>();
    List<String> bList = new ArrayList<>();
    List<Boolean> cList = new ArrayList<>();
    @Override
    public BaseRecyclerAdapter<ServiceCouponEntity> createAdapter() {
        return new CouponAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "ServiceCouponFragment";
    }

    @Override
    public List<ServiceCouponEntity> readList(Serializable seri) {
        return ((ServiceCouponResult)seri).getData().getData();
    }

    @Override
    protected void sendRequestData() {
        AppContext.set("couponMenoy","");
        AppContext.set("couponMenoy_id","");
        Bundle b  = getArguments();
        if(b!=null){
            serviceId = b.getString("ServiceId");
            menoy = b.getString("total");
        }
        ServiceCouponDTO dto = new ServiceCouponDTO();
        long time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();
        dto.setAccessToken(AppContext.get("accessToken",""));
        dto.setRandom(random);
        dto.setUid(AppContext.get("uid",""));
        dto.setTimestamp(time);
        dto.setSign(AppContext.get("uid","")+time+random);
        dto.setPage(String.valueOf(mCurrentPage));
        dto.setSize(String.valueOf(PAGE_SIZE));
        dto.setServiceId(serviceId);
        CommonApiClient.serviceCoupon(getActivity(), dto, new CallBack<ServiceCouponResult>() {
            @Override
            public void onSuccess(ServiceCouponResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("服务优惠券成功");
                    mErrorLayout.setErrorMessage("暂无优惠券记录",mErrorLayout.FLAG_NODATA);
                    mErrorLayout.setErrorImag(R.drawable.siaieless1,mErrorLayout.FLAG_NODATA);
                    requestDataSuccess(result);
                    setDataResult(result.getData().getData());
                    if(null==result.getData().getData()){
                        return;

                    }else {
                        for(int i =0;i<result.getData().getData().size();i++){
                            aList.add(i,result.getData().getData().get(i).getId());
                        }
                        for(int i =0;i<result.getData().getData().size();i++){
                            bList.add(i,result.getData().getData().get(i).getValue_amount());
                        }
                        for(int i =0;i<result.getData().getData().size();i++){
                            cList.add(i,true);
                        }
                    }


                }

            }
        });
    }

    @Override
    public void initData() {
    }

    public boolean autoRefreshIn(){
        return true;
    }



    private String mId,mAmount;
    Double mMoney;
    List<String> mList = new ArrayList<>();
    List<String> tList = new ArrayList<>();
    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        super.onItemClick(itemView, itemBean, position);
        LinearLayout linearLayout = (LinearLayout) itemView;
        ImageView img =(ImageView)linearLayout.findViewById(R.id.coupon_img);
        if(cList.get(position)){
            img.setVisibility(View.VISIBLE);
            linearLayout.setEnabled(false);
            cList.set(position,false);
            ServiceCouponEntity entity = (ServiceCouponEntity) itemBean;
            mList.add(aList.get(position));
            tList.add(bList.get(position));
            LogUtils.e("mList---",""+mList+"--size"+mList.size());
            LogUtils.e("tList---",""+tList+"--size"+tList.size());
//            double t1 = Double.parseDouble(menoy);
//            double t2 = Double.parseDouble(entity.getFace_amount());
//            if(t1>t2||t1==t2){
//                if(null==mAmount){
//                    mAmount = tList.get(0);
//                    mMoney = Double.parseDouble(mAmount);
//                }else {
//                    mMoney = Double.parseDouble(mAmount);
//                    for(int i=1;i<tList.size();i++){
//                        mMoney+=Double.parseDouble(tList.get(i));
//
//                    }
//                }
//
//                LogUtils.e("mMoney---",""+mMoney);
//                AppContext.set("couponMenoy",String.valueOf(mMoney));
//                if(null==mId){
//                    mId=mList.get(0);
//                }else {
//                    mId=mList.get(0);
//                    for(int i=1;i<mList.size();i++){
//                        mId+=","+mList.get(i);
//                    }
//                }
//
//                LogUtils.e("mId---",""+mId);
//                AppContext.set("couponMenoy_id",mId);
//
//            }else {
//                DialogUtils.showPrompt(getActivity(), "提示","不可使用", "知道了");
//            }

            if(null==mAmount){
                mAmount = tList.get(0);
                mMoney = Double.parseDouble(mAmount);
            }else {
                mMoney = Double.parseDouble(mAmount);
                for(int i=1;i<tList.size();i++){
                    mMoney+=Double.parseDouble(tList.get(i));

                }
            }

            LogUtils.e("mMoney---",""+mMoney);
            AppContext.set("couponMenoy",String.valueOf(mMoney));
            if(null==mId){
                mId=mList.get(0);
            }else {
                mId=mList.get(0);
                for(int i=1;i<mList.size();i++){
                    mId+=","+mList.get(i);
                }
            }

            LogUtils.e("mId---",""+mId);
            AppContext.set("couponMenoy_id",mId);
        }else {
            img.setVisibility(View.GONE);
        }



    }
}
