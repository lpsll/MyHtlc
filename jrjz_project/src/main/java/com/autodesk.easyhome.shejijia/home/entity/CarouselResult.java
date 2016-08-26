package com.autodesk.easyhome.shejijia.home.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by John_Libo on 2016/8/26.
 */
public class CarouselResult extends BaseEntity {
    List<CarouselEntity> data;
    public List<CarouselEntity> getData() {
        return data;
    }

    public void setData(List<CarouselEntity> data) {
        this.data = data;
    }


}
