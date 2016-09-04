package com.autodesk.easyhome.shejijia.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.widget.PinnedHeaderListView;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.autodesk.easyhome.shejijia.home.adapter.LeftListAdapter;
import com.autodesk.easyhome.shejijia.home.adapter.MainSectionedAdapter;
import com.autodesk.easyhome.shejijia.home.entity.ClassificationEntity;
import com.autodesk.easyhome.shejijia.home.entity.ClassificationResult;
import com.autodesk.easyhome.shejijia.home.entity.ClassificationServicesEntity;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.mine.MineUiGoto;
import com.autodesk.easyhome.shejijia.mine.fragment.MineFragment;
import com.autodesk.easyhome.shejijia.order.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 分类页
 */
public class ClassificationActivity extends BaseTitleActivity {
    @Bind(R.id.left_listview)
    ListView leftListview;
    @Bind(R.id.pinnedListView)
    PinnedHeaderListView pinnedListView;
    private boolean isScroll = true;
    private LeftListAdapter adapter;

    List<ClassificationEntity> data;
    List<ClassificationServicesEntity> entity;
    boolean login;

    @Override
    protected int getContentResId() {
        return R.layout.activity_test;
    }

    @Override
    public void initView() {
        setTitleText("分类");



    }

    private void reqClassification() {
        BaseDTO dto = new BaseDTO();
        CommonApiClient.classification(this, dto, new CallBack<ClassificationResult>() {
            @Override
            public void onSuccess(ClassificationResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("分类成功");
                    setResult(result);

                }

            }
        });
    }

    ArrayList<String>  mList ;
    ArrayList<ArrayList<String>> sList ;
    ArrayList<ArrayList<String>>  iList ;
    ArrayList<String>  aList ;
    ArrayList<String>  bList ;
    ArrayList<Boolean>  flagArray  = new ArrayList<>();;
   
    private void setResult(ClassificationResult result) {
        data = result.getData();

        mList = new ArrayList<>();
        sList = new ArrayList<>();
        iList = new ArrayList<>();


        flagArray.add(true);
        for(int i= 0;i<data.size()-1;i++){
            flagArray.add(false);
        }
        LogUtils.e("flagArray----",""+flagArray);


        for(int i= 0;i<data.size();i++){
            mList.add(data.get(i).getName());
        }
        LogUtils.e("mList---",""+mList);
        LogUtils.e("mList.size---",""+mList.size());


        for(int i= 0;i<data.size();i++){
            entity = data.get(i).getServices();
            aList = new ArrayList<>();
            bList = new ArrayList<>();
            for(int j= 0;j<entity.size();j++){
                aList.add(entity.get(j).getName());
                if(i==(data.size()-1)&&j==(entity.size()-1)){
                    aList.add("");
                    aList.add("");
                    aList.add("");
                    aList.add("");
                    aList.add("");
                    aList.add("");
                }
            }
            sList.add(aList);

            for(int k= 0;k<entity.size();k++){
                bList.add(AppConfig.BASE_IMG_URL+entity.get(k).getLogo());
            }
            iList.add(bList);
        }
        LogUtils.e("sList---",""+sList);
        LogUtils.e("sList.size---",""+sList.size());
        LogUtils.e("iList---",""+iList);
        LogUtils.e("iList.size---",""+iList.size());
        bindResult();

    }

    private void bindResult() {
        final MainSectionedAdapter sectionedAdapter = new MainSectionedAdapter(this, mList, sList,iList,flagArray);
        pinnedListView.setAdapter(sectionedAdapter);
        adapter = new LeftListAdapter(this, mList, flagArray);
        leftListview.setAdapter(adapter);

        leftListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
                isScroll = false;

                for (int i = 0; i < mList.size(); i++) {
                    if (i == position) {
                        flagArray.set(i, true);
                    } else {
                        flagArray.set(i, false);
                    }
                }
                adapter.notifyDataSetChanged();
                sectionedAdapter.notifyDataSetChanged();
                int rightSection = 0;
                for (int i = 0; i < position; i++) {
                    rightSection += sectionedAdapter.getCountForSection(i) + 1;
                }
                pinnedListView.setSelection(rightSection);

            }

        });

        pinnedListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView arg0, int scrollState) {
                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 判断滚动到底部
                        if (pinnedListView.getLastVisiblePosition() == (pinnedListView.getCount() - 1)) {
                            leftListview.setSelection(ListView.FOCUS_DOWN);
                        }

                        // 判断滚动到顶部
                        if (pinnedListView.getFirstVisiblePosition() == 0) {
                            leftListview.setSelection(0);
                        }

                        break;
                }
            }

            int y = 0;
            int x = 0;
            int z = 0;

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (isScroll) {
                    for (int i = 0; i < sList.size(); i++) {
                        if (i == sectionedAdapter.getSectionForPosition(pinnedListView.getFirstVisiblePosition())) {
                            flagArray.set(i, true);
                            x = i;
                        } else {
                            flagArray.set(i, false);
                        }
                    }
                    if (x != y) {
                        adapter.notifyDataSetChanged();
                        sectionedAdapter.notifyDataSetChanged();
                        y = x;
                        //左侧ListView滚动到最后位置
                        if (y == leftListview.getLastVisiblePosition()) {
                            leftListview.setSelection(z);
                        }
                        //左侧ListView滚动到第一个位置
                        if (x == leftListview.getFirstVisiblePosition()) {
                            leftListview.setSelection(z);
                        }
                        if (firstVisibleItem + visibleItemCount == totalItemCount - 1) {
                            leftListview.setSelection(ListView.FOCUS_DOWN);
                        }
                    }
                } else {
                    isScroll = true;
                }
            }
        });

        pinnedListView.setOnItemClickListener(new PinnedHeaderListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int section, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("mName",data.get(section).getServices().get(position).getName());
                bundle.putString("mId",data.get(section).getServices().get(position).getId());
                HomeUiGoto.gotoApt(ClassificationActivity.this, bundle);
            }

            @Override
            public void onSectionClick(AdapterView<?> adapterView, View view, int section, long id) {

            }
        });
    }

    @Override
    public void initData() {
        login = AppContext.get("IS_LOGIN",false);
        if(login){
        reqClassification();
        }
        else {
            HomeUiGoto.gotoLoginClass(this);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == HomeUiGoto.LC_REQUEST) {
            initData();
        }
    }


}
