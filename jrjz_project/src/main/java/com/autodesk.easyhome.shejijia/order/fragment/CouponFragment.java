package com.autodesk.easyhome.shejijia.order.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseFragment;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.home.dto.DeleteAddressDTO;
import com.autodesk.easyhome.shejijia.home.entity.AddAddressResult;
import com.autodesk.easyhome.shejijia.order.activity.CouponActivity;
import com.autodesk.easyhome.shejijia.order.adapter.CouponAdapter;
import com.autodesk.easyhome.shejijia.order.dto.CouponDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by John_Libo on 2016/8/18.
 */
public class CouponFragment  extends BaseFragment {
    @Bind(R.id.coupon_list)
    ListView mList;

    private static final String TYPE = "type";
    private int type;


    public static CouponFragment newInstance(int type) {
        CouponFragment fragment = new CouponFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);//传递Type
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    protected void retry() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_coupon;
    }

    @Override
    public void initView(View view) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt(TYPE, CouponActivity.TAB_A);

        }
        reqCoupon();
    }

    private void reqCoupon() {
        CouponDTO dto = new CouponDTO();
        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();
        dto.setAccessToken(AppContext.get("accessToken",""));
        dto.setRandom(random);
        dto.setUid(AppContext.get("uid",""));
        dto.setTimestamp(time);
        dto.setSign(AppContext.get("uid","")+time+random);
        dto.setPage("6");
        dto.setSize("20");
        if(type ==1){
            dto.setUseStatus("1");
        }
        else if(type ==2){
            dto.setUseStatus("2");
        }
        else if(type ==3){
            dto.setUseStatus("3");
        }

        CommonApiClient.coupon(getActivity(), dto, new CallBack<AddAddressResult>() {
            @Override
            public void onSuccess(AddAddressResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("优惠券成功");
                }

            }
        });
    }

    @Override
    public void initData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("a", "20" + i);
            map.put("b", "优惠券" + i);
            map.put("c", "满100使用" + i);
            map.put("d", "可以使用" + i);
            map.put("e", "2016-2017到期" + i);
            list.add(map);
        }
        mList.setAdapter(new CouponAdapter(getActivity(), list));

    }
}
