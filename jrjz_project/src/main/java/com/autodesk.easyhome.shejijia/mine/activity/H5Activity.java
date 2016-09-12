package com.autodesk.easyhome.shejijia.mine.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;

import butterknife.Bind;

public class H5Activity extends BaseTitleActivity {

    private WebSettings settings;
    private String url;
    private String title;

    @Bind(R.id.webview_aboutjuran)
    WebView webviewAboutjuran;

    @Override
    protected int getContentResId() {
        return R.layout.activity_h5;
    }

    @Override
    public void initView() {


        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        setTitleText(title);
        showDialogLoading();

        addH5();

    }

    @Override
    public void initData() {


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    private void addH5() {

        //一定要加上下面的,防止页面出现太大的情况
        //设置支持JavaScript
        settings = webviewAboutjuran.getSettings();
        settings.setJavaScriptEnabled(true);
        //启用页面上放大缩小按钮
        settings.setBuiltInZoomControls(true);
        //启用页面双击缩放功能
        settings.setUseWideViewPort(true);

        webviewAboutjuran.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideDialogLoading();
            }
        });

        webviewAboutjuran.loadUrl(url);
    }


}
