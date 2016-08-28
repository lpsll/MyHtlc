package com.autodesk.easyhome.shejijia.home.activity;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.autodesk.easyhome.shejijia.AppConfig;
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

//    private String[] mList = new String[]{"家装维修", "家电维修", "家具维修", "家政服务", "社区服务"};

//    private boolean[] flagArray = {true, false, false, false, false};

    private String[][] rightStr = new String[][]{{"长城干红", "燕京鲜啤", "青岛鲜啤","果盘","果啤"},
            {"拌粉丝", "大拌菜", "菠菜花生","凉拌菜","花菜"}, {"小食组", "紫薯", "红薯", "白薯", "绿薯"},
            {"小米粥", "大米粥", "南瓜粥", "玉米粥", "紫米粥"}, {"儿童小汽车", "悠悠球", "熊大", " 熊二", "光头强","","","",""}
    };
    private String[][] rightStr1 = new String[][]{{"长城", "燕京", "青岛","果","啤"},
            {"拌丝", "大菜", "花生","凉菜","菜"}, {"小组", "薯", "红", "白", "绿"},
            {"小粥", "大粥", "南粥", "玉粥", "紫粥"}, {"儿童", "悠球", "大", " 二", "头强","","","",""}
    };
    private String[] img = new String[]{"url2","url3","url4","url5","url1",};

    List<ClassificationEntity> data;
    List<ClassificationServicesEntity> entity;

    @Override
    protected int getContentResId() {
        return R.layout.activity_test;
    }

    @Override
    public void initView() {
        setTitleText("分类");
        reqClassification();

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
//        aList = new ArrayList<>();
        bList = new ArrayList<>();

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
            LogUtils.e("entity---",""+entity);
            for(int j= 0;j<entity.size();j++){
                aList = new ArrayList<>();
                aList.add(entity.get(j).getName());
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
        final MainSectionedAdapter sectionedAdapter = new MainSectionedAdapter(this, mList, data,flagArray);
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
                    for (int i = 0; i < rightStr.length; i++) {
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
                HomeUiGoto.gotoApt(ClassificationActivity.this);
            }

            @Override
            public void onSectionClick(AdapterView<?> adapterView, View view, int section, long id) {

            }
        });
    }

    @Override
    public void initData() {


    }
}
