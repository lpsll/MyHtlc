package com.autodesk.easyhome.shejijia.order.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by John_Libo on 2016/8/30.
 */
public class ServiceCouponResult extends BaseEntity {
    List<ServiceCouponEntity> data;
    public List<ServiceCouponEntity> getData() {
        return data;
    }

    public void setData(List<ServiceCouponEntity> data) {
        this.data = data;
    }



}
