package com.autodesk.easyhome.shejijia.campaign.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.campaign.adapter.CampaignFragmentAdapter;
import com.autodesk.easyhome.shejijia.campaign.entity.CampaignEntity;
import com.autodesk.easyhome.shejijia.campaign.entity.CampaignResult;
import com.autodesk.easyhome.shejijia.common.base.BaseFragment;
import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.widget.EmptyLayout;

import java.util.List;

/**
 * 活动页
 */
public class CampaignFragment extends BaseFragment {

    private RecyclerView recyclerview_campaign;
    private CampaignFragmentAdapter adapter;

    List<CampaignEntity> campData;

    @Override
    protected void retry() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_campaign;
    }

    @Override
    public void initView(View view) {
        recyclerview_campaign = (RecyclerView) rootView.findViewById(R.id.recyclerview_campaign);

    }

    @Override
    public void initData() {


        //获取服务器数据
        getDataFromService();



    }

    /**
     * 从服务器获取数据
     */
    private void getDataFromService() {

        CommonApiClient.campaign(getActivity(), new BaseDTO(), new CallBack<CampaignResult>() {
            @Override
            public void onSuccess(CampaignResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("获取活动页面成功");
                    if (result.getData() != null) {
                        if (result.getData().size() == 0) {
                            mErrorLayout.setVisibility(View.VISIBLE);
                            mErrorLayout.setErrorType(EmptyLayout.FLAG_NODATA);
                        } else {
                            mErrorLayout.setVisibility(View.GONE);

                            campData = result.getData();

                            setAdapter();
                        }


                    }

                }
            }
        });


    }

    private void setAdapter() {

        recyclerview_campaign.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置adapter
        adapter = new CampaignFragmentAdapter(getActivity(), campData);
        recyclerview_campaign.setAdapter(adapter);
    }


}
