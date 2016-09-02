package com.autodesk.easyhome.shejijia.order.dto;

import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;

/**
 * Created by John_Libo on 2016/9/2.
 */
public class NewPaymentDTO extends BaseDTO{
    private String dealType;
    private String dealId;
    private String couponids;
    private String points;


    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public String getCouponids() {
        return couponids;
    }

    public void setCouponids(String couponids) {
        this.couponids = couponids;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }


}
