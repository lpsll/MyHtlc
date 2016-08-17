package com.jrjz_project.mine.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.common.base.BaseTitleActivity;
import com.jrjz_project.common.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoreSettingActivity extends BaseTitleActivity {


    @Bind(R.id.rl_moresetting_clear_cache)
    RelativeLayout rlMoresettingClearCache;
    @Bind(R.id.tv_moresetting_exit)
    TextView tvMoresettingOk;
    @Bind(R.id.tv_moresetting_cache)
    TextView tvMoresettingCache;

    @Override
    public void initView() {
        setTitleText("设置");

    }

    @Override
    public void initData() {

    }

    @Override
    protected int getContentResId() {
        return R.layout.activity_more_setting;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_moresetting_clear_cache, R.id.tv_moresetting_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_moresetting_clear_cache:
                //清除缓存
                new AlertDialog.Builder(MoreSettingActivity.this).setMessage("确定清除缓存吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //清除缓存的操作

                                //改变ui
                                tvMoresettingCache.setText("0KB");

                                ToastUtils.showShort(MoreSettingActivity.this, "已清除数据");
                            }
                        })
                        .setNegativeButton("取消", null).show();
                break;
            case R.id.tv_moresetting_exit:
                //退出
                finish();
                break;
        }
    }
}
