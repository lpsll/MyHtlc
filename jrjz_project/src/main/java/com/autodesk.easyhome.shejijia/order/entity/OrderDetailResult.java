package com.autodesk.easyhome.shejijia.order.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

/**
 * Created by John_Libo on 2016/9/1.
 */
public class OrderDetailResult extends BaseEntity {
    OrderDetailsEntity data;
    public OrderDetailsEntity getData() {
        return data;
    }

    public void setData(OrderDetailsEntity data) {
        this.data = data;
    }


}
