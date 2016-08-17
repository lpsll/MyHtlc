package com.jrjz_project.campaign.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.common.base.BaseFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * 活动页
 */
public class CampaignFragment extends BaseFragment {

    private RecyclerView recyclerview_campaign;
    private List mChongzhi;  //数据的集合
    private List mZengsong;  //数据的集合
    private CampaignFragmentAdapter adapter;

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
        mChongzhi = new ArrayList();
        for (int i = 0; i < 5; i++) {
            mChongzhi.add("1000");
        }

        mZengsong = new ArrayList();
        for (int i = 0; i < 5; i++) {
            mZengsong.add("500");
        }

        recyclerview_campaign.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置adapter
        adapter = new CampaignFragmentAdapter(getActivity(),mChongzhi,mZengsong);
        recyclerview_campaign.setAdapter(adapter);

//        adapter.setOnItemClickLitener(new CampaignFragmentAdapter.OnItemClickLitener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Log.i("TAG","aaaaa");
//            }
//        });

    }


}
