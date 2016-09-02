package com.autodesk.easyhome.shejijia.mine.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

/**
 * Created by John_Libo on 2016/8/31.
 */
public class MineCouponEntity extends BaseEntity {
    private String value_amount;
    private String id;
    private String expire_date;
    private String serviceId;
    private String batchnumber;
    private String name;
    private String face_amount;
    private String effective_date;
    private String user_status;
    private String descr;
    private String serviceName;
    private String amount_level;

    public String getValue_amount() {
        return value_amount;
    }

    public void setValue_amount(String value_amount) {
        this.value_amount = value_amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(String expire_date) {
        this.expire_date = expire_date;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getBatchnumber() {
        return batchnumber;
    }

    public void setBatchnumber(String batchnumber) {
        this.batchnumber = batchnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFace_amount() {
        return face_amount;
    }

    public void setFace_amount(String face_amount) {
        this.face_amount = face_amount;
    }

    public String getEffective_date() {
        return effective_date;
    }

    public void setEffective_date(String effective_date) {
        this.effective_date = effective_date;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getAmount_level() {
        return amount_level;
    }

    public void setAmount_level(String amount_level) {
        this.amount_level = amount_level;
    }


}
