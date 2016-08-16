package com.jrjz_project.order.entity;

import com.jrjz_project.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by John_Libo on 2016/8/15.
 */
public class OrderResult extends BaseEntity {
    List<OrderEntity> data;

    public List<OrderEntity> getData() {
        return data;
    }

    public void setData(List<OrderEntity> data) {
        this.data = data;
    }

}
