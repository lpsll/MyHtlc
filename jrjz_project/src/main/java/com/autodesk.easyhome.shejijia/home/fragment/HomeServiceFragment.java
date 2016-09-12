package com.autodesk.easyhome.shejijia.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BasePullFragment;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.ImageLoaderUtils;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.common.widget.EmptyLayout;
import com.autodesk.easyhome.shejijia.common.widget.FullyLinearLayoutManager;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.autodesk.easyhome.shejijia.home.adapter.HomeServiceItemAdapter;
import com.autodesk.easyhome.shejijia.home.dto.HomeServiceDTO;
import com.autodesk.easyhome.shejijia.home.entity.CarouselEntity;
import com.autodesk.easyhome.shejijia.home.entity.HomeServiceEntity;
import com.autodesk.easyhome.shejijia.home.entity.HomeServiceResult;
import com.autodesk.easyhome.shejijia.order.dto.ServiceCouponDTO;
import com.autodesk.easyhome.shejijia.order.entity.ServiceCouponResult;
import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;
import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;

import butterknife.Bind;

/**
 * 家政服务
 */
public class HomeServiceFragment extends BasePullFragment {
    @Bind(R.id.homeservice_list)
    RecyclerView mList;
    BaseSimpleRecyclerAdapter mListAdapter;
    HomeServiceItemAdapter mItemAdapter;
    private String mId;
    ImageView imageItem,imageHousekeeping;
    RecyclerView mItemList;
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
        mItemAdapter = new HomeServiceItemAdapter();
        mListAdapter = new BaseSimpleRecyclerAdapter<HomeServiceEntity>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_hs_top;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, HomeServiceEntity homeServiceEntity, int position) {
                holder.setText(R.id.tv_housekeeping_item_shop_name,homeServiceEntity.getName());
                imageItem = holder.getView(R.id.img_housekeeping_item_big_img);
                ImageLoaderUtils.displayImage(homeServiceEntity.getSiteImg(), imageItem);

                mItemList = holder.getView(R.id.homeservice_item_list);
                mItemList.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
                mItemList.setAdapter(mItemAdapter);
            }


        };
        mList.setAdapter(mListAdapter);
        mItemAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View itemView, Object itemBean, int position) {
                HomeUiGoto.gotoServiceDetials(getActivity());
            }
        });
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
                    if(null==result.getData()){
                        mErrorLayout.setErrorType(EmptyLayout.NODATA);
                    }else {
                        mListAdapter.removeAll();
                        mListAdapter.append(result.getData().getClasses());
                        mItemAdapter.removeAll();
                        mItemAdapter.append(result.getData().getClasses());
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
