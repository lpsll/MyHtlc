package com.autodesk.easyhome.shejijia.home.dto;

import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;

/**
 * Created by John_Libo on 2016/9/4.
 */
public class ZfbDTO extends BaseDTO {
    private String dealType;
    private String dealId;
    private String tradetype;
    private String couponids;
    private String points;

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String getRandom() {
        return random;
    }

    @Override
    public void setRandom(String random) {
        this.random = random;
    }

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

    public String getTradetype() {
        return tradetype;
    }

    public void setTradetype(String tradetype) {
        this.tradetype = tradetype;
    }

    @Override
    public String getSign() {
        return sign;
    }

    @Override
    public void setSign(String sign) {
        this.sign = sign;
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
