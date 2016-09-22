package com.autodesk.easyhome.shejijia.home.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by John_Libo on 2016/9/12.
 */
public class HomeServiceEntity extends BaseEntity {
    private String id;
    private String name;
    private String descr;
    private String siteImg;
    private String descImg;
    private String address;
    private String contact;
    private String phone;
    private String latitude;
    private String longitude;
    List<ServicesEntity> services;


    public String getDescImg() {
        return descImg;
    }

    public void setDescImg(String descImg) {
        this.descImg = descImg;
    }

    public List<ServicesEntity> getServices() {
        return services;
    }

    public void setServices(List<ServicesEntity> services) {
        this.services = services;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getSiteImg() {
        return siteImg;
    }

    public void setSiteImg(String siteImg) {
        this.siteImg = siteImg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }




}
