package com.autodesk.easyhome.shejijia;

import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import com.autodesk.easyhome.shejijia.common.base.BaseActivity;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.autodesk.easyhome.shejijia.R;

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
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        LogUtils.e("initView---","initView");
        // 获取屏幕宽高（方法1）
        int screenWidth = getWindowManager().getDefaultDisplay().getWidth(); // 屏幕宽（像素，如：480px）
        int screenHeight = getWindowManager().getDefaultDisplay().getHeight(); // 屏幕高（像素，如：800p）
        AppContext.set("screenWidth",screenWidth);
        AppContext.set("screenHeight", screenHeight);
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
                Thread.sleep(1000);
                Message msg = new Message();
                msg.what = 1;
                tHandler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
