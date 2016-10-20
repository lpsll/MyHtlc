package com.autodesk.easyhome.shejijia.home.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.home.dto.LookNewsDTO;
import com.autodesk.easyhome.shejijia.home.entity.AddAddressResult;
import com.autodesk.easyhome.shejijia.home.entity.NewsEntity;
import com.autodesk.easyhome.shejijia.home.entity.NewsEvent;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * 消息中心之消息详情
 */
public class NewsDetailsActivity extends BaseTitleActivity {
    NewsEntity entity;
    @Bind(R.id.tv01)
    TextView tv01;
    @Bind(R.id.tv02)
    TextView tv02;
    @Bind(R.id.tv03)
    TextView tv03;

    @Override
    protected int getContentResId() {
        return R.layout.activity_news;
    }

    @Override
    public void initView() {
        setTitleText("消息详情");
        entity = (NewsEntity) getIntent().getBundleExtra("bundle").getSerializable("entity");
        LogUtils.e("entity---",""+entity);
        tv01.setText(entity.getSmtitle());
        tv02.setText(entity.getSmcontent());
        tv03.setText(entity.getCreatedate());

    }

    @Override
    public void initData() {
        reqRead();//查看消息
    }

    private void reqRead() {
        LookNewsDTO dto = new LookNewsDTO();
        long time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();
        dto.setAccessToken(AppContext.get("accessToken", ""));
        dto.setRandom(random);
        dto.setUid(AppContext.get("uid", ""));
        dto.setTimestamp(time);
        dto.setSign(AppContext.get("uid", "") + time + random);
        dto.setMessageId(entity.getId());
        CommonApiClient.lookNews(this, dto, new CallBack<AddAddressResult>() {
            @Override
            public void onSuccess(AddAddressResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("查看消息成功");
                    EventBus.getDefault().post(
                            new NewsEvent("10"));
                }

            }
        });
    }

}
