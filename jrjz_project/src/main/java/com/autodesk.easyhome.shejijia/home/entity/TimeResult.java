package com.autodesk.easyhome.shejijia.home.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by John_Libo on 2016/9/2.
 */
public class TimeResult extends BaseEntity {
    List<TimeEntity> data;
    public List<TimeEntity> getData() {
        return data;
    }

    public void setData(List<TimeEntity> data) {
        this.data = data;
    }


}
