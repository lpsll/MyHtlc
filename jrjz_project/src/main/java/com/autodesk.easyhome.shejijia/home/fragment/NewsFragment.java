package com.autodesk.easyhome.shejijia.home.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseListFragment;
import com.autodesk.easyhome.shejijia.common.eventbus.ErrorEvent;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.DialogUtils;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.TextViewUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.autodesk.easyhome.shejijia.home.adapter.NewsAdapter;
import com.autodesk.easyhome.shejijia.home.dto.LookNewsDTO;
import com.autodesk.easyhome.shejijia.home.entity.AddAddressResult;
import com.autodesk.easyhome.shejijia.home.entity.NewsData;
import com.autodesk.easyhome.shejijia.home.entity.NewsEntity;
import com.autodesk.easyhome.shejijia.home.entity.NewsEvent;
import com.autodesk.easyhome.shejijia.home.entity.NewsResult;
import com.autodesk.easyhome.shejijia.order.dto.OrderDTO;
import com.autodesk.easyhome.shejijia.order.entity.OrderEntity;
import com.autodesk.easyhome.shejijia.order.entity.OrderResult;
import com.autodesk.easyhome.shejijia.order.fragment.OrderFragment;
import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;

import java.io.Serializable;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * 系统消息
 */
public class NewsFragment extends BaseListFragment<NewsEntity> {
    boolean login;
    NewsData data;
    NewsAdapter adapter = new NewsAdapter();
    @Override
    public BaseRecyclerAdapter<NewsEntity> createAdapter() {
        return adapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.e("onCreate---","onCreate");
        login = AppContext.get("IS_LOGIN",false);
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "NewsFragment";
    }

    @Override
    public List<NewsEntity> readList(Serializable seri) {
        return ((NewsResult)seri).getData().getData();
    }

    @Override
    protected void sendRequestData() {
        LogUtils.e("login---",""+login);
        if(login){
            OrderDTO dto = new OrderDTO();
            final long time = TimeUtils.getSignTime();
            final String random = TimeUtils.genNonceStr();
            dto.setAccessToken(AppContext.get("accessToken",""));
            dto.setRandom(random);
            dto.setUid(AppContext.get("uid",""));
            dto.setTimestamp(time);
            dto.setSign(AppContext.get("uid","")+time+random);
            dto.setPage(String.valueOf(mCurrentPage));
            dto.setSize(String.valueOf(PAGE_SIZE));
            CommonApiClient.news(getActivity(), dto, new CallBack<NewsResult>() {
                    @Override
                    public void onSuccess(NewsResult result) {
                        if (AppConfig.SUCCESS.equals(result.getCode())) {
                            LogUtils.e("消息成功");
                            data = result.getData();
                            mErrorLayout.setErrorMessage("暂无消息记录",mErrorLayout.FLAG_NODATA);
                            mErrorLayout.setErrorImag(R.drawable.siaieless1,mErrorLayout.FLAG_NODATA);
                            requestDataSuccess(result);
                            setDataResult(result.getData().getData());
                        }

                    }
                });
            }

        else {
            DialogUtils.confirm(getActivity(), "您尚未登录，是否去登录？", listener);
        }
    }

    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            HomeUiGoto.gotoLogin(getActivity());
        }
    };

    @Override
    public void initData() {
        LogUtils.e("initData---","initData");
    }

    public boolean autoRefreshIn(){
        if(login){
            return true;
        }else {
            DialogUtils.confirm(getActivity(), "您尚未登录，是否去登录？", listener);
            return false;

        }

    }

    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        super.onItemClick(itemView, itemBean, position);
        NewsEntity entity= (NewsEntity) itemBean;
        Bundle bundle = new Bundle();
        bundle.putSerializable("entity",entity);
        HomeUiGoto.gotoNewsDetails(getActivity(),bundle);
    }

    public void onEventMainThread(NewsEvent event) {
        String msg = event.getMsg();
        LogUtils.e("mainActivity---msg---", "" + msg);
        if (null == msg) {

        } else {
            if (msg.equals("10")) {
//                OrderDTO dto = new OrderDTO();
//                final long time = TimeUtils.getSignTime();
//                final String random = TimeUtils.genNonceStr();
//                dto.setAccessToken(AppContext.get("accessToken", ""));
//                dto.setRandom(random);
//                dto.setUid(AppContext.get("uid", ""));
//                dto.setTimestamp(time);
//                dto.setSign(AppContext.get("uid", "") + time + random);
//                dto.setPage(String.valueOf(mCurrentPage));
//                dto.setSize(String.valueOf(PAGE_SIZE));
//                CommonApiClient.news(getActivity(), dto, new CallBack<NewsResult>() {
//                    @Override
//                    public void onSuccess(NewsResult result) {
//                        if (AppConfig.SUCCESS.equals(result.getCode())) {
//                            LogUtils.e("刷新消息成功");
//                            adapter.notifyDataSetChanged();
//
//                        }
//
//                    }
//                });

                mCurrentPage = 0;
                sendRequestData();
            }


        }

    }


}
