package com.autodesk.easyhome.shejijia.home.dto;

import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;

/**
 * Created by John_Libo on 2016/9/4.
 */
public class WxDTO extends BaseDTO {
    private String dealType;
    private String dealId;
    private String couponids;
    private String points;
    private String tradetype;
    private String code;

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

    public String getTradetype() {
        return tradetype;
    }

    public void setTradetype(String tradetype) {
        this.tradetype = tradetype;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
