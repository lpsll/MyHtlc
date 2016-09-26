package com.autodesk.easyhome.shejijia.home.activity;

import android.content.Intent;
import android.widget.ImageView;

import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.utils.ImageLoaderUtils;

import butterknife.Bind;

public class HomeServiceImgActivtiy extends BaseTitleActivity {

    @Bind(R.id.img)
    ImageView img;
    private String name;
    private String imgurl;


    @Override
    protected int getContentResId() {
        return R.layout.activity_home_service_img;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        imgurl = intent.getStringExtra("descImg");


        setTitleText(name);
        showDialogLoading();

    }

    @Override
    public void initData() {
        ImageLoaderUtils.displayImage(imgurl,img);

        hideDialogLoading();

    }

}
