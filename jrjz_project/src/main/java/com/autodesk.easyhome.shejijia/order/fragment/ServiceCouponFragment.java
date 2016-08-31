package com.autodesk.easyhome.shejijia.order.fragment;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.common.base.BaseListFragment;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.home.entity.AddAddressResult;
import com.autodesk.easyhome.shejijia.order.adapter.CouponAdapter;
import com.autodesk.easyhome.shejijia.order.dto.ServiceCouponDTO;
import com.autodesk.easyhome.shejijia.order.entity.ServiceCouponEntity;
import com.autodesk.easyhome.shejijia.order.entity.ServiceCouponResult;
import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * 服务优惠券
 */
public class ServiceCouponFragment  extends BaseListFragment<ServiceCouponEntity> {
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
        return ((ServiceCouponResult)seri).getData();
    }

    @Override
    protected void sendRequestData() {
        ServiceCouponDTO dto = new ServiceCouponDTO();
        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();
        dto.setAccessToken(AppContext.get("accessToken",""));
        dto.setRandom(random);
        dto.setUid(AppContext.get("uid",""));
        dto.setTimestamp(time);
        dto.setSign(AppContext.get("uid","")+time+random);
        dto.setPage("0");
        dto.setSize("10");
        dto.setServiceId("");
        CommonApiClient.serviceCoupon(getActivity(), dto, new CallBack<AddAddressResult>() {
            @Override
            public void onSuccess(AddAddressResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("服务优惠券成功");
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
}
