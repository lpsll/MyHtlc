package com.autodesk.easyhome.shejijia.home.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

/**
 * Created by John_Libo on 2016/9/4.
 */
public class WxResult extends BaseEntity {
    WxEntity data;
    public WxEntity getData() {
        return data;
    }

    public void setData(WxEntity data) {
        this.data = data;
    }


}
