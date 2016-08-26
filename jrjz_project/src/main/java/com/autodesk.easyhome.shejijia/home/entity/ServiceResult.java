package com.autodesk.easyhome.shejijia.home.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

/**
 * Created by John_Libo on 2016/8/26.
 */
public class ServiceResult extends BaseEntity {
    ServiceData data;
    public ServiceData getData() {
        return data;
    }

    public void setData(ServiceData data) {
        this.data = data;
    }


}
