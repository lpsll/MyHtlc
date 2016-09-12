package com.autodesk.easyhome.shejijia.home.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by John_Libo on 2016/8/17.
 */
public class ClassificationFragment extends BaseFragment {
    @Bind(R.id.cf_list)
    ListView mList;

    private static final String TYPE = "type";
    private int type;

    public static ClassificationFragment newInstance(int type) {
        ClassificationFragment fragment = new ClassificationFragment();
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
        return R.layout.fragment_classification;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", "利" + i);
            map.put("title", "先生" + i);
            list.add(map);
        }

    }
}
