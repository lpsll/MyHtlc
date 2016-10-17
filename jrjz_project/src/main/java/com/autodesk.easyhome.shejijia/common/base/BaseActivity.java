package com.autodesk.easyhome.shejijia.common.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppManager;
import com.autodesk.easyhome.shejijia.common.eventbus.ErrorEvent;
import com.autodesk.easyhome.shejijia.common.http.BaseApiClient;
import com.autodesk.easyhome.shejijia.common.interf.IBaseActivity;
import com.autodesk.easyhome.shejijia.common.utils.DialogUtils;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.ToastUtils;
import com.autodesk.easyhome.shejijia.home.entity.WhetherEvent;

import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;
import de.greenrobot.event.EventBus;

/**
 Activity的基类
 */
public abstract class BaseActivity  extends FragmentActivity implements
        View.OnClickListener, IBaseActivity {

    private static Dialog mLoadingDialog;
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        AppManager.getAppManager().addActivity(this);
        onBeforeSetContentLayout();
        if (getLayoutResId() != 0) {
            setContentView(getLayoutResId());
        }
        onAfterSetContentLayout();
        ButterKnife.bind(this);
        registerEventBus();

        initView();
        initData();

        JPushInterface.init(getApplicationContext());

        ShareSDK.initSDK(this,"16ab104736290");
    }



    /**
     * 资源文件Layout ID
     *
     * @return
     */
    protected int getLayoutResId() {
        return 0;
    }

    /**
     * 注册EventBus通信组件
     */
    protected void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    /**
     * 取消注册EventBus通信组件
     */
    protected void unRegisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    public abstract void initView();

    public abstract void initData();


    /**
     * 设置布局前要进行的操作
     */
    protected void onBeforeSetContentLayout() {
    }

    /**
     * 设置布局后要进行的操作
     */
    protected void onAfterSetContentLayout() {

    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 加载中效果
     */
    public void showDialogLoading() {
        if(mLoadingDialog!=null&&mLoadingDialog.isShowing()){
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
        mLoadingDialog = DialogUtils.showLoading(this);
    }

    /**
     * 加载中效果
     *
     * @param msg 提示信息
     */
    public void showDialogLoading(String msg) {
        mLoadingDialog = DialogUtils.showLoading(this, msg);
    }

    /**
     * 隐藏遮罩的dialog
     */
    public void hideDialogLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        BaseApiClient.cancelCall(this);
        ButterKnife.unbind(this);
        unRegisterEventBus();
        super.onDestroy();
    }



    public void onEventMainThread(ErrorEvent event) {
        hideDialogLoading();
        String status = event.getCode();
        String message = event.getMsg();
        if (event.getTag().equals(this)) {
            if(!AppConfig.SUCCESS.equals(status)) {
                LogUtils.e("status---",""+status);
                LogUtils.e("message---",""+message);
                ToastUtils.showShort(this,message);
            }

        }
    }

//    public void onEventMainThread(WhetherEvent event) {
//        hideDialogLoading();
//        String status = event.getCode();
//        String message = event.getMsg();
//        if (event.getTag().equals(this)) {
//            if(!AppConfig.SUCCESS.equals(status)) {
//                LogUtils.e("event.getTag()---","event.getTag()");
//                LogUtils.e("message---",""+message);
//                ToastUtils.showShort(this,message);
//            }
//
//        }
//    }


    protected void showMsg(int resId){
        ToastUtils.showShort(this,resId);
    }

    protected void showMsg(String message){
        ToastUtils.showShort(this,message);
    }
}
