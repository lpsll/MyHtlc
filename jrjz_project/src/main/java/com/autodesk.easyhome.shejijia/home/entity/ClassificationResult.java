package com.autodesk.easyhome.shejijia.home.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by John_Libo on 2016/8/27.
 */
public class ClassificationResult extends BaseEntity {
    List<ClassificationEntity> data;
    public List<ClassificationEntity> getData() {
        return data;
    }

    public void setData(List<ClassificationEntity> data) {
        this.data = data;
    }


}
