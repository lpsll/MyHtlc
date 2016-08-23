package com.autodesk.easyhome.shejijia.mine.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.utils.ToastUtils;
import com.htlc.jrjz.jrjz_project.R;

import butterknife.ButterKnife;


public class MoreSettingActivity extends BaseTitleActivity {



    private RelativeLayout rlMoresettingClearCache;
    private TextView tvMoresettingOk;
    private  TextView tvMoresettingCache;;

    @Override
    public void initView() {
        setTitleText("设置");

        rlMoresettingClearCache = (RelativeLayout) findViewById(R.id.rl_moresetting_clear_cache);
        tvMoresettingCache = (TextView) findViewById(R.id.tv_moresetting_cache);
        tvMoresettingOk = (TextView) findViewById(R.id.tv_moresetting_exit);

        rlMoresettingClearCache.setOnClickListener(this);
        tvMoresettingOk.setOnClickListener(this);


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

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
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