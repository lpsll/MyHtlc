package com.autodesk.easyhome.shejijia.order.fragment;

import android.os.Bundle;
import android.view.View;

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
import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;

import java.io.Serializable;
import java.util.List;

import butterknife.OnItemClick;

/**
 * 服务优惠券
 */
public class ServiceCouponFragment  extends BaseListFragment<ServiceCouponEntity> {
    private String serviceId,menoy;
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
        Bundle b  = getArguments();
        if(b!=null){
            serviceId = b.getString("ServiceId");
            menoy = b.getString("total");
        }
        ServiceCouponDTO dto = new ServiceCouponDTO();
        String time = TimeUtils.getSignTime();
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

    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        super.onItemClick(itemView, itemBean, position);
        ServiceCouponEntity entity = (ServiceCouponEntity) itemBean;
        double t1 = Double.parseDouble(menoy);
        double t2 = Double.parseDouble(entity.getFace_amount());
        if(t1>t2||t1==t2){
            AppContext.set("couponMenoy",entity.getValue_amount());
            AppContext.set("couponMenoy_id",entity.getId());
            getActivity().finish();
        }else {
            DialogUtils.showPrompt(getActivity(), "提示","不可使用", "知道了");
        }

    }
}
