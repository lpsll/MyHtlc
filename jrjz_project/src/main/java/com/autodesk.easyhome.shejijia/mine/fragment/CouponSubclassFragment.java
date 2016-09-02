package com.autodesk.easyhome.shejijia.mine.fragment;

import android.os.Bundle;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseListFragment;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.mine.activity.MineCouponActivity;
import com.autodesk.easyhome.shejijia.mine.adapter.MineCouponAdapter;
import com.autodesk.easyhome.shejijia.mine.dto.MineCouponDTO;
import com.autodesk.easyhome.shejijia.mine.entity.MineCouponEntity;
import com.autodesk.easyhome.shejijia.mine.entity.MineCouponResult;
import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * 我的优惠券fragment
 */
public class CouponSubclassFragment extends BaseListFragment<MineCouponEntity> {
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt(TYPE, MineCouponActivity.TAB_A);

        }
    }

    @Override
    public BaseRecyclerAdapter<MineCouponEntity> createAdapter() {
        return new MineCouponAdapter(type);
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "CouponSubclassFragment";
    }

    @Override
    public List<MineCouponEntity> readList(Serializable seri) {
        return ((MineCouponResult)seri).getData().getData();
    }

    @Override
    protected void sendRequestData() {
        MineCouponDTO dto = new MineCouponDTO();
        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();
        dto.setAccessToken(AppContext.get("accessToken",""));
        dto.setRandom(random);
        dto.setUid(AppContext.get("uid",""));
        dto.setTimestamp(time);
        dto.setSign(AppContext.get("uid","")+time+random);
        dto.setPage(String.valueOf(mCurrentPage));
        dto.setSize(String.valueOf(PAGE_SIZE));
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
}
