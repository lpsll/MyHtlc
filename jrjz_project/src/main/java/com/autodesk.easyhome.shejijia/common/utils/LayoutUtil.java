package com.autodesk.easyhome.shejijia.common.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

/**
 * 布局计算工具
 */
public class LayoutUtil {
    /**
     * 根据现有宽度获取屏幕适配的高
     */
    public static int getAdaptiveHeight(Activity act, int curWidth,
                                        int oldHeight, int oldWidth) {
        int curHeight = curWidth * oldHeight / oldWidth;
        return curHeight;
    }

    /**
     * 根据现有高度获取屏幕适配的宽
     */
    public static int getAdaptiveWidth(Activity act, int curHeight,
                                       int oldHeight, int oldWidth) {
        int curWidth = curHeight * oldWidth / oldHeight;
        return curWidth;
    }

    /**
     * 重新计算控件高度
     */


//	public static void reMesureHeight(Activity act, View v, int curWidth,
//									  int oldHeight, int oldWidth) {
//		ViewGroup.LayoutParams layoutParam = v.getLayoutParams();
//		layoutParam.height = LayoutUtil.getAdaptiveHeight(act, curWidth,
//				oldHeight, oldWidth);
//		v.setLayoutParams(layoutParam);
//	}



    /**
     * 重新计算控件宽度
     */
    public static void reMesureWidth(Activity act, View v, int curHeight,
                                     int oldHeight, int oldWidth) {
        ViewGroup.LayoutParams layoutParam = v.getLayoutParams();
        layoutParam.height = LayoutUtil.getAdaptiveWidth(act, curHeight,
                oldHeight, oldWidth);
        v.setLayoutParams(layoutParam);
    }

    /**
     * 设置布局
     */
    public static void setLayout(View v, int curHeight, int curWidth) {
        ViewGroup.LayoutParams layoutParam = v.getLayoutParams();
        layoutParam.height = curHeight;
        layoutParam.width = curWidth;
        v.setLayoutParams(layoutParam);
    }

    /**
     * 设置布局高度
     */
    public static void setLayoutHeight(View v, int curHeight) {
        ViewGroup.LayoutParams layoutParam = v.getLayoutParams();
        layoutParam.height = curHeight;
        v.setLayoutParams(layoutParam);
    }

    /**
     * 设置布局宽度
     */
    public static void setLayoutWidth(View v, int curWidth) {
        ViewGroup.LayoutParams layoutParam = v.getLayoutParams();
        layoutParam.width = curWidth;
        v.setLayoutParams(layoutParam);
    }


    /**
     * 根据现有宽度获取屏幕适配的高
     */
    public static int getAdaptiveHeight(int curWidth,
                                        float oldHeight, float oldWidth) {
        int curHeight=0;
        float h=curWidth * oldHeight / oldWidth;
        if((curWidth * oldHeight) % oldWidth==0) {
            curHeight = (int)(h);
        }else{
            curHeight = (int)(h)+3;
        }
        return curHeight;
    }

    /**
     * 重新计算控件高度
     */
    public static void reMesureHeight(View v, int curWidth,
                                      float oldHeight, float oldWidth) {
        ViewGroup.LayoutParams layoutParam = v.getLayoutParams();
        layoutParam.height = LayoutUtil.getAdaptiveHeight(curWidth,
                oldHeight, oldWidth);
        v.setLayoutParams(layoutParam);
    }
}
