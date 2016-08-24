package com.autodesk.easyhome.shejijia;

import android.os.Handler;
import android.os.Message;

import com.autodesk.easyhome.shejijia.common.base.BaseActivity;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.htlc.jrjz.jrjz_project.R;

/**
 * Created by John_Libo on 2016/8/23.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }
    @Override
    public void initView() {
        LogUtils.e("initView---","initView");
//        HomeUiGoto.gotoMain(SplashActivity.this);

    }

    @Override
    public void initData() {
        new Thread(new ThreadShow()).start();

    }

    // handler类接收数据
    Handler tHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    HomeUiGoto.gotoMain(SplashActivity.this);
                    finish();
                    break;
            }
        };
    };

    // 线程类
    class ThreadShow implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                Message msg = new Message();
                msg.what = 1;
                tHandler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
