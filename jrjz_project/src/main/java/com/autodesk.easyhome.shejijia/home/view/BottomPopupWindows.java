package com.autodesk.easyhome.shejijia.home.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.autodesk.easyhome.shejijia.R;

/**
 * Created by Administrator on 2016/8/23.
 */
public class BottomPopupWindows extends PopupWindow {

    private View mView;
    public Button btnSaveProject, btnAbandonProject, btnCancelProject;

    public BottomPopupWindows(Activity context,
                              View.OnClickListener itemsOnClick) {
        super(context);


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.bottom_popuwindow, null);

        btnSaveProject = (Button) mView.findViewById(R.id.popupwindow_Button_from_photos);
        btnAbandonProject = (Button) mView.findViewById(R.id.popupwindow_Button_open_camera);
        btnCancelProject = (Button) mView.findViewById(R.id.popupwindow_cancelButton);

        // 设置按钮监听
        btnCancelProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "取消项目");
                dismiss();
            }
        });
        btnSaveProject.setOnClickListener(itemsOnClick);
        btnAbandonProject.setOnClickListener(itemsOnClick);


        //设置PopupWindow的View
        this.setContentView(mView);
        //设置PopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.Animation_bottom_popuwindow);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
    }
}
