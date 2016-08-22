package com.jrjz_project.home.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.common.base.BaseTitleActivity;
import com.jrjz_project.home.HomeUiGoto;
import com.jrjz_project.home.adapter.SelectAddressAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 常用地址页
 */
public class SelectAddressActivity extends BaseTitleActivity {
    @Bind(R.id.list)
    ListView mList;
    @Bind(R.id.select_lin)
    LinearLayout mSelectLin;


    @Override
    protected int getContentResId() {
        return R.layout.activity_select_address;
    }

    @Override
    public void initView() {
        setTitleText("常用地址");
        reqSelect();
    }

    private void reqSelect() {
    }

    @Override
    public void initData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", "王大拿" + i);
            map.put("title", "先生" + i);
            map.put("info", "13166666666");
            map.put("add", "北京市昌平区北京市昌平区北京市昌平区北京市昌平区");
            list.add(map);
        }
        mList.setAdapter(new SelectAddressAdapter(this, list));
    }


    @OnClick(R.id.select_lin)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_lin:
                HomeUiGoto.gotoAddress(this);
                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;

        }

    }
}
