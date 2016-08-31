package com.autodesk.easyhome.shejijia.campaign.entity;

/**
 * Created by Administrator on 2016/8/31.
 *
 * "id": "3",
 * "startDate": "2016-08-01 00:00:00",
 * "amountLevel": "2",
 * "atitle": "return app",
 * "endDate": "2016-09-30 00:00:00",
 * "couponAmount": "10",
 * "rechargeAmount": "1000"
 * }
 * ]
 * }
 */
public class CampaignEntity {

    private String id;
    private String startDate;
    private String amountLevel;
    private String atitle;
    private String endDate;
    private String couponAmount;
    private String rechargeAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getAmountLevel() {
        return amountLevel;
    }

    public void setAmountLevel(String amountLevel) {
        this.amountLevel = amountLevel;
    }

    public String getAtitle() {
        return atitle;
    }

    public void setAtitle(String atitle) {
        this.atitle = atitle;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(String couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(String rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }
}
