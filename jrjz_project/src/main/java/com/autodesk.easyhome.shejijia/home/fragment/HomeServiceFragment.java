package com.autodesk.easyhome.shejijia.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BasePullScrollViewFragment;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.ImageLoaderUtils;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.widget.EmptyLayout;
import com.autodesk.easyhome.shejijia.common.widget.FullyLinearLayoutManager;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.autodesk.easyhome.shejijia.home.adapter.HomeServiceItemAdapter;
import com.autodesk.easyhome.shejijia.home.dto.HomeServiceDTO;
import com.autodesk.easyhome.shejijia.home.entity.HomeServiceData;
import com.autodesk.easyhome.shejijia.home.entity.HomeServiceEntity;
import com.autodesk.easyhome.shejijia.home.entity.HomeServiceResult;
import com.autodesk.easyhome.shejijia.home.entity.ServicesEntity;
import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;
import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 家政服务
 */
public class HomeServiceFragment extends BasePullScrollViewFragment {
    @Bind(R.id.homeservice_list)
    RecyclerView mList;
    BaseSimpleRecyclerAdapter mListAdapter;
    HomeServiceItemAdapter mItemAdapter;
    private String mId;
    ImageView imageItem,imageHousekeeping;
    RecyclerView mItemList;
    List<ServicesEntity> services;
    ArrayList<List<ServicesEntity>> list;
    ServicesEntity data;
    HomeServiceData hsdata;
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_homeservice;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        Bundle b  = getArguments();
        mId = b.getString("mId");
        mList.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        mListAdapter = new BaseSimpleRecyclerAdapter<HomeServiceEntity>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_hs_top;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, final HomeServiceEntity homeServiceEntity, int position) {
                LogUtils.e("mListAdapter---position---",""+position);
                holder.setText(R.id.tv_housekeeping_item_shop_name,homeServiceEntity.getName());
                imageItem = holder.getView(R.id.img_housekeeping_item_big_img);
                ImageLoaderUtils.displayImage(homeServiceEntity.getSiteImg(), imageItem);

                mItemList = holder.getView(R.id.homeservice_item_list);
                mItemList.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
                mItemAdapter = new HomeServiceItemAdapter(hsdata.getClasses().get(position).getServices());
                mItemAdapter.removeAll();
                mItemAdapter.append(hsdata.getClasses().get(position).getServices());
                mItemList.setAdapter(mItemAdapter);
                mItemAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, Object itemBean, int position) {
                        data = (ServicesEntity) itemBean;
                        Bundle bundle = new Bundle();
                        bundle.putString("name",homeServiceEntity.getName());
                        bundle.putString("img",data.getLogo());
                        bundle.putString("descr",data.getDescr());
                        bundle.putString("price",data.getPrice());
                        bundle.putString("id",data.getId());
                        bundle.putString("preject",data.getName());
                        HomeUiGoto.gotoServiceDetials(getActivity(),bundle);
                    }
                });
            }


        };
        mList.setAdapter(mListAdapter);

        mListAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View itemView, Object itemBean, int position) {


            }
        });



    }
    @Override
    public void initData() {
        sendRequestData();

    }

    @Override
    protected void sendRequestData() {
        reqHomeService();//家政服务
    }

    private void reqHomeService() {
        HomeServiceDTO dto = new HomeServiceDTO();
        dto.setClassId(Integer.parseInt(mId));
        CommonApiClient.homeService(getActivity(), dto, new CallBack<HomeServiceResult>() {
            @Override
            public void onSuccess(HomeServiceResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("家政服务成功");
                    mErrorLayout.setErrorMessage("暂无家政服务记录",mErrorLayout.FLAG_NODATA);
                    mErrorLayout.setErrorImag(R.drawable.siaieless1,mErrorLayout.FLAG_NODATA);
                    list = new ArrayList<List<ServicesEntity>>();
                    hsdata = result.getData();
                    if(null==hsdata){
                        mErrorLayout.setErrorType(EmptyLayout.NODATA);
                    }else {
                        mListAdapter.removeAll();
                        mListAdapter.append(hsdata.getClasses());

                        for(int i =0;i<result.getData().getClasses().size();i++){
                            list.add(( result.getData().getClasses().get(i).getServices()));
//                            for(int j =0;j<result.getData().getClasses().get(i).getServices().size();j++){
//                                list.add(( result.getData().getClasses().get(i).getServices().get(j)));
//                            }

                        }

                        refreshComplete();
                    }

                }

            }
        });
    }

    @Override
    public boolean pulltoRefresh() {
        return true;
    }
}
