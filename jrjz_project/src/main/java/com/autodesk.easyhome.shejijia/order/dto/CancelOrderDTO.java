package com.autodesk.easyhome.shejijia.order.dto;

import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;

/**
 * Created by John_Libo on 2016/8/30.
 */
public class CancelOrderDTO extends BaseDTO {
    private String orderId;
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


}
