package com.autodesk.easyhome.shejijia.home.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

/**
 * Created by John_Libo on 2016/9/12.
 */
public class HomeServiceResult extends BaseEntity {
    HomeServiceData data;
    public HomeServiceData getData() {
        return data;
    }

    public void setData(HomeServiceData data) {
        this.data = data;
    }


}
