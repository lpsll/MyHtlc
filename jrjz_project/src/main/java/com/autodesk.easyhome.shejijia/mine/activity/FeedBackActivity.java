package com.autodesk.easyhome.shejijia.mine.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.common.utils.ToastUtils;
import com.autodesk.easyhome.shejijia.mine.dto.FeedBackDTO;
import com.autodesk.easyhome.shejijia.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 意见反馈页
 */
public class FeedBackActivity extends BaseTitleActivity {


    @Bind(R.id.et_feedback)
    EditText etFeedback;
    @Bind(R.id.tv_feedback_commit)
    TextView tvFeedbackCommit;

    @Override
    public void initView() {
        setTitleText("意见反馈");

    }

    @Override
    public void initData() {

    }

    @Override
    protected int getContentResId() {
        return R.layout.suggest_feedback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_feedback_commit)
    public void onClick() {
        String feedBack = etFeedback.getText().toString();
        if (TextUtils.isEmpty(feedBack)) {
            new AlertDialog.Builder(FeedBackActivity.this).setTitle("请填写您的意见！")
                    .setPositiveButton("确定", null).show();
        } else {
            //提交意见反馈
            feedBackCommit();
        }
    }

    /**
     * 提交意见反馈
     */
    private void feedBackCommit() {

        FeedBackDTO feedBackDTO = new FeedBackDTO();

        String accessToken = AppContext.get(AppConfig.ACCESSTOKEN, "");
        String uid = AppContext.get(AppConfig.UID, "");
        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();

        feedBackDTO.setUid(uid);
        feedBackDTO.setAccessToken(accessToken);
        feedBackDTO.setFcontent(etFeedback.getText().toString());
        feedBackDTO.setTimestamp(time);
        feedBackDTO.setRandom(random);
        // 	 签名【生成规则 uid+timestamp+random 后md5加密串】
        feedBackDTO.setSign(uid + time + random);

        CommonApiClient.feedBack(this, feedBackDTO, new CallBack<BaseEntity>() {
            @Override
            public void onSuccess(BaseEntity result) {
                    if (AppConfig.SUCCESS.equals(result.getCode())) {
                        LogUtils.e("意见反馈提交成功");
                        ToastUtils.showShort(FeedBackActivity.this, "提交成功");
                        finish();
                    }
            }
        });
    }
}
