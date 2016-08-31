package com.autodesk.easyhome.shejijia.mine.fragment;

import android.os.Bundle;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.common.base.BaseListFragment;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.mine.activity.MineCouponActivity;
import com.autodesk.easyhome.shejijia.mine.adapter.MineCouponAdapter;
import com.autodesk.easyhome.shejijia.mine.dto.MineCouponDTO;
import com.autodesk.easyhome.shejijia.mine.entity.MineCouponResult;
import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * 我的优惠券fragment
 */
public class CouponSubclassFragment extends BaseListFragment<MineEntity> {
    private static final String TYPE = "type";
    private int type;

    public static CouponSubclassFragment newInstance(int type) {
        CouponSubclassFragment fragment = new CouponSubclassFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);//传递Type
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public BaseRecyclerAdapter<MineEntity> createAdapter() {
        return new MineCouponAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "CouponSubclassFragment";
    }

    @Override
    public List<MineEntity> readList(Serializable seri) {
        return ((MineResult)seri).getData();
    }

    @Override
    protected void sendRequestData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt(TYPE, MineCouponActivity.TAB_A);
            LogUtils.e("type---",""+type);

        }
        MineCouponDTO dto = new MineCouponDTO();
        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();
        dto.setAccessToken(AppContext.get("accessToken",""));
        dto.setRandom(random);
        dto.setUid(AppContext.get("uid",""));
        dto.setTimestamp(time);
        dto.setSign(AppContext.get("uid","")+time+random);
        dto.setPage("0");
        dto.setSize("10");
        if(type ==1){
            dto.setUseStatus("1");
        }
        else if(type ==2){
            dto.setUseStatus("2");
        }
        else if(type ==3){
            dto.setUseStatus("3");
        }

        CommonApiClient.mineCoupon(getActivity(), dto, new CallBack<MineCouponResult>() {
            @Override
            public void onSuccess(MineCouponResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("我的优惠券成功");
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
