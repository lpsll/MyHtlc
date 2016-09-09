package com.autodesk.easyhome.shejijia;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.autodesk.easyhome.shejijia.common.base.BaseActivity;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 启动页
 */
public class GuideActivity extends BaseActivity{

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
                    HomeUiGoto.gotoSplash(GuideActivity.this);
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
