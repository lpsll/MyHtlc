package com.autodesk.easyhome.shejijia.home.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by John_Libo on 2016/8/26.
 */
public class FullServiceResult extends BaseEntity{
    List<FullServiceEntity> data;
    public List<FullServiceEntity> getData() {
        return data;
    }

    public void setData(List<FullServiceEntity> data) {
        this.data = data;
    }


}
