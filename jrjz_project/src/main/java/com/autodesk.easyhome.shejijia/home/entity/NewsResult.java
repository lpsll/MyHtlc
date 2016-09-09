package com.autodesk.easyhome.shejijia.home.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by John_Libo on 2016/9/9.
 */
public class NewsResult extends BaseEntity{
    NewsData data;
    public NewsData getData() {
        return data;
    }

    public void setData(NewsData data) {
        this.data = data;
    }

}
