package com.autodesk.easyhome.shejijia.order.fragment;

import android.os.Bundle;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseListFragment;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.order.adapter.OrderInsideAdapter;
import com.autodesk.easyhome.shejijia.order.dto.OrderDTO;
import com.autodesk.easyhome.shejijia.order.entity.OrderEntity;
import com.autodesk.easyhome.shejijia.order.entity.OrderResult;
import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by John_Libo on 2016/8/15.
 */
public class OrderInsideFragment extends BaseListFragment<OrderEntity> {
    private static final String TYPE = "type";
    private int type;

    public static OrderInsideFragment newInstance(int type) {
        OrderInsideFragment fragment = new OrderInsideFragment();
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
            type = bundle.getInt(TYPE, OrderFragment.TAB_A);

        }
    }

    @Override
    public BaseRecyclerAdapter<OrderEntity> createAdapter() {
        LogUtils.e("createAdapter------","createAdapter");
        return new OrderInsideAdapter(getActivity(),type);
    }

    @Override
    protected String getCacheKeyPrefix() {
        LogUtils.e("getCacheKeyPrefix------","getCacheKeyPrefix");
        return "OrderInsideFragment"+type+"_";
    }

    @Override
    public List<OrderEntity> readList(Serializable seri) {
        LogUtils.e("readList------","readList");
        return ((OrderResult)seri).getData().getData();
    }

    @Override
    protected void sendRequestData() {
        LogUtils.e("sendRequestData------","sendRequestData");
        OrderDTO dto = new OrderDTO();
        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();
        dto.setAccessToken(AppContext.get("accessToken",""));
        dto.setRandom(random);
        dto.setUid(AppContext.get("uid",""));
        dto.setTimestamp(time);
        dto.setSign(AppContext.get("uid","")+time+random);
        dto.setPage("0");
        dto.setSize("10");
        LogUtils.e("type----",""+type);
        if(type ==1){
            CommonApiClient.unfinished(getActivity(), dto, new CallBack<OrderResult>() {
                @Override
                public void onSuccess(OrderResult result) {
                    if (AppConfig.SUCCESS.equals(result.getCode())) {
                        LogUtils.e("未完成订单成功");
                        mErrorLayout.setErrorMessage("暂无订单记录",mErrorLayout.FLAG_NODATA);
                        mErrorLayout.setErrorImag(R.drawable.siaieless1,mErrorLayout.FLAG_NODATA);
                        requestDataSuccess(result);
                        setDataResult(result.getData().getData());
                    }

                }
            });
        }
        else if(type ==2){
            CommonApiClient.tobePaid(getActivity(), dto, new CallBack<OrderResult>() {
                @Override
                public void onSuccess(OrderResult result) {
                    if (AppConfig.SUCCESS.equals(result.getCode())) {
                        LogUtils.e("待支付订单成功");
                        mErrorLayout.setErrorMessage("暂无订单记录",mErrorLayout.FLAG_NODATA);
                        mErrorLayout.setErrorImag(R.drawable.siaieless1,mErrorLayout.FLAG_NODATA);
                        requestDataSuccess(result);
                        setDataResult(result.getData().getData());
                    }

                }
            });
        }
        else if(type ==3){
            CommonApiClient.tobeEvaluated(getActivity(), dto, new CallBack<OrderResult>() {
                @Override
                public void onSuccess(OrderResult result) {
                    if (AppConfig.SUCCESS.equals(result.getCode())) {
                        LogUtils.e("待评价订单成功");
                        mErrorLayout.setErrorMessage("暂无订单记录",mErrorLayout.FLAG_NODATA);
                        mErrorLayout.setErrorImag(R.drawable.siaieless1,mErrorLayout.FLAG_NODATA);
                        requestDataSuccess(result);
                        setDataResult(result.getData().getData());
                    }

                }
            });
        }
        else if(type ==4){
            CommonApiClient.completed(getActivity(), dto, new CallBack<OrderResult>() {
                @Override
                public void onSuccess(OrderResult result) {
                    if (AppConfig.SUCCESS.equals(result.getCode())) {
                        LogUtils.e("已完成订单成功");
                        mErrorLayout.setErrorMessage("暂无订单记录",mErrorLayout.FLAG_NODATA);
                        mErrorLayout.setErrorImag(R.drawable.siaieless1,mErrorLayout.FLAG_NODATA);
                        requestDataSuccess(result);
                        setDataResult(result.getData().getData());
                    }

                }
            });
        }
        else if(type ==5){
            CommonApiClient.whole(getActivity(), dto, new CallBack<OrderResult>() {
                @Override
                public void onSuccess(OrderResult result) {
                    if (AppConfig.SUCCESS.equals(result.getCode())) {
                        LogUtils.e("全部订单成功");
                        mErrorLayout.setErrorMessage("暂无订单记录",mErrorLayout.FLAG_NODATA);
                        mErrorLayout.setErrorImag(R.drawable.siaieless1,mErrorLayout.FLAG_NODATA);
                        requestDataSuccess(result);
                        setDataResult(result.getData().getData());
                    }

                }
            });
        }



    }

    @Override
    public void initData() {
        LogUtils.e("initData------","initData");
    }

    public boolean autoRefreshIn(){
        LogUtils.e("autoRefreshIn------","autoRefreshIn");
        return true;
    }
}
