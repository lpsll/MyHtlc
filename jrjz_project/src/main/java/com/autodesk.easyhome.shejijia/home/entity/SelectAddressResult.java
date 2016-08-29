package com.autodesk.easyhome.shejijia.home.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by John_Libo on 2016/8/28.
 */
public class SelectAddressResult extends BaseEntity {
    List<SelectAddressEntity> data;
    public List<SelectAddressEntity> getData() {
        return data;
    }

    public void setData(List<SelectAddressEntity> data) {
        this.data = data;
    }


}
