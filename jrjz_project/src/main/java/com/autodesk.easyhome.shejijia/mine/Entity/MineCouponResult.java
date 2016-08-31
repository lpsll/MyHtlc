package com.autodesk.easyhome.shejijia.mine.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by John_Libo on 2016/8/31.
 */
public class MineCouponResult extends BaseEntity{
    List<MineCouponEntity> data;
    public List<MineCouponEntity> getData() {
        return data;
    }

    public void setData(List<MineCouponEntity> data) {
        this.data = data;
    }


}
