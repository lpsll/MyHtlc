package com.autodesk.easyhome.shejijia.mine.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.utils.ToastUtils;
import com.htlc.jrjz.jrjz_project.R;

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
        if(TextUtils.isEmpty(feedBack)) {
            new AlertDialog.Builder(FeedBackActivity.this).setTitle("请填写您的意见！")
                    .setPositiveButton("确定",null).show();
        }else {
            ToastUtils.showShort(this,"提交成功！");
            finish();
        }

    }
}
