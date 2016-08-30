package com.autodesk.easyhome.shejijia.mine.activity;

import android.content.DialogInterface;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;
import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.DialogUtils;
import com.autodesk.easyhome.shejijia.common.utils.GetFileSizeUtil;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.common.utils.ToastUtils;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;

import butterknife.Bind;
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

        rlMoresettingClearCache = (RelativeLayout) findViewById(R.id.rl_moresetting_clear_cache);
        tvMoresettingCache = (TextView) findViewById(R.id.tv_moresetting_cache);
        tvMoresettingOk = (TextView) findViewById(R.id.tv_moresetting_exit);

        rlMoresettingClearCache.setOnClickListener(this);
        tvMoresettingOk.setOnClickListener(this);

        //获取缓存数据
        try {
            getCache();
        } catch (Exception e) {
            LogUtils.d("获取缓存异常=========="+e.getMessage());
            e.printStackTrace();
        }
        

    }

    @Override
    public void initData() {

    }

    /**
     *获取缓存大小
     */
    private void getCache() throws Exception {
        DiskCache diskCache = ImageLoader.getInstance().getDiskCache();
        File directory = diskCache.getDirectory();
        long fileSizes = GetFileSizeUtil.getInstance().getFileSize(directory);
        String size = GetFileSizeUtil.getInstance().FormetFileSize(fileSizes);
        LogUtils.d("缓存大小==========" + size);
        tvMoresettingCache.setText(size);

    }


    @Override
    protected int getContentResId() {
        return R.layout.activity_more_setting;
    }


    @OnClick({R.id.rl_moresetting_clear_cache, R.id.tv_moresetting_exit})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.rl_moresetting_clear_cache:
                //清除缓存
                DialogUtils.confirm(MoreSettingActivity.this, "确定清除缓存吗？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clearCache();
                        //改变ui
                        tvMoresettingCache.setText("0KB");
                        ToastUtils.showShort(MoreSettingActivity.this, "已清除数据");
                    }
                });


                break;
            case R.id.tv_moresetting_exit:
                //退出
                DialogUtils.confirm(MoreSettingActivity.this, "确定要退出吗？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //保存用户信息

                        exit();



                    }
                });

                break;
        }
    }

    /**
     * 退出操作
     * sign:签名【生成规则uid+timestamp+random 后md5加密串】
     */
    private void exit() {

        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();

        BaseDTO baseDTO = new BaseDTO();
        baseDTO.setUid(AppContext.get("uid",""));
        baseDTO.setAccessToken(AppContext.get("accessToken",""));
        baseDTO.setRandom(random);
        baseDTO.setTimestamp(time);
        baseDTO.setSign(AppContext.get("uid","")+time+random);

        CommonApiClient.logout(this, baseDTO, new CallBack<BaseEntity>() {
            @Override
            public void onSuccess(BaseEntity result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("退出成功");
                    ToastUtils.showShort(MoreSettingActivity.this, "退出成功");

                }
            }
        });


        AppContext.set("uid", "");
        AppContext.set("accessToken", "");
        AppContext.set("IS_LOGIN", false);
        setResult(1001);
        finish();

    }

    /**
     * 清除缓存的操作
     */
    private void clearCache() {
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.clearMemoryCache();
        imageLoader.clearDiskCache();
    }

    
}
