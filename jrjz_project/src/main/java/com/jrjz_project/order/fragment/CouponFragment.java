package com.jrjz_project.order.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.common.base.BaseFragment;
import com.jrjz_project.home.adapter.ClassificationAdapter;
import com.jrjz_project.order.adapter.CouponAdapter;

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
