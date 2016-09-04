package com.autodesk.easyhome.shejijia.order.dto;

import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;

/**
 * Created by John_Libo on 2016/9/4.
 */
public class EvaluateDTO extends BaseDTO {
    private String orderId;
    private String timeIs;
    private String serviceIs;
    private String problemIs;
    private String statementIs;
    private String chargeforIs;
    private String opinion;
    private String imagePath;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTimeIs() {
        return timeIs;
    }

    public void setTimeIs(String timeIs) {
        this.timeIs = timeIs;
    }

    public String getServiceIs() {
        return serviceIs;
    }

    public void setServiceIs(String serviceIs) {
        this.serviceIs = serviceIs;
    }

    public String getProblemIs() {
        return problemIs;
    }

    public void setProblemIs(String problemIs) {
        this.problemIs = problemIs;
    }

    public String getStatementIs() {
        return statementIs;
    }

    public void setStatementIs(String statementIs) {
        this.statementIs = statementIs;
    }

    public String getChargeforIs() {
        return chargeforIs;
    }

    public void setChargeforIs(String chargeforIs) {
        this.chargeforIs = chargeforIs;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


}
