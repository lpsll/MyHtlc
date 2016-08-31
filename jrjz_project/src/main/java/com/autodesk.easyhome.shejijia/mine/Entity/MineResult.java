package com.autodesk.easyhome.shejijia.mine.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;
import com.autodesk.easyhome.shejijia.order.entity.OrderEntity;

import java.util.List;

/**
 * Created by John_Libo on 2016/8/30.
 */
public class MineResult extends BaseEntity {
    List<MineEntity> data;
    public List<MineEntity> getData() {
        return data;
    }

    public void setData(List<MineEntity> data) {
        this.data = data;
    }


}
