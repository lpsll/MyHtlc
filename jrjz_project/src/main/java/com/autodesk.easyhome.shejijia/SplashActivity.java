package com.autodesk.easyhome.shejijia;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.autodesk.easyhome.shejijia.common.base.BaseActivity;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 广告页
 */
public class SplashActivity extends BaseActivity {

    @Bind(R.id.ad_tv)
    Button mAdTv;
    @Bind(R.id.splash_img)
    ImageView mSplashImg;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        LogUtils.e("initView---", "initView");
        // 获取屏幕宽高（方法1）
        int screenWidth = getWindowManager().getDefaultDisplay().getWidth(); // 屏幕宽（像素，如：480px）
        int screenHeight = getWindowManager().getDefaultDisplay().getHeight(); // 屏幕高（像素，如：800p）
        AppContext.set("screenWidth", screenWidth);
        AppContext.set("screenHeight", screenHeight);

    }

    @Override
    public void initData() {
        preparation();

    }

    /**
     * 准备
     */
    private void preparation() {
        mAdTv.setVisibility(View.VISIBLE);
        handler = new ReadyHandler(4, new ReadyListener() {

            @Override
            public void onGoMain(int time) {
                if (time > 0) {
                    Button btn = (Button) findViewById(R.id.ad_tv);
                    LogUtils.e("time----", "" + time);
                    btn.setText("跳过" + String.valueOf(time));
                } else {
                    gotoMain();
                }
            }
        });
        handler.sendEmptyMessage(1);
    }

    public void gotoMain() {
        LogUtils.e("gotoMain----", "true");
        HomeUiGoto.gotoMain(SplashActivity.this);
        overridePendingTransition(0, 0);
        SplashActivity.this.finish();

    }

    private ReadyHandler handler = null;



    @OnClick(R.id.ad_tv)
    public void onClick(View v) {
        super.onClick(v);
        gotoMain();
    }

    private class ReadyHandler extends Handler {
        private int outTime;
        private ReadyListener listener;

        public ReadyHandler(int outTime, ReadyListener l) {
            this.outTime = outTime;
            this.listener = l;
        }

        public ReadyHandler() {
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    this.listener.onGoMain(--outTime);
                    if (outTime > 0) {
                        handler.sendEmptyMessageDelayed(1, 1000);
                    }
                    break;
            }

            super.handleMessage(msg);
        }
    }

    private interface ReadyListener {
        void onGoMain(int time);
    }
}
