package com.jrjz_project.mine.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.common.base.BaseTitleActivity;
import com.jrjz_project.common.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 意见反馈页
 */
public class FeedBackActivity extends BaseTitleActivity {


    @Bind(R.id.tv_feedback_commit)
    TextView tvFeedbackCommit;
    @Bind(R.id.et_feedback)
    EditText etFeedback;

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
        String feedback = etFeedback.getText().toString();
        if(TextUtils.isEmpty(feedback)) {
            new AlertDialog.Builder(FeedBackActivity.this)
                    .setTitle("请输入您的意见")
                    .setPositiveButton("确定",null)
                    .show();
        }else {
            ToastUtils.showShort(FeedBackActivity.this,"提交成功！");
            finish();
        }
    }
}
