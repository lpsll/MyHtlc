package com.autodesk.easyhome.shejijia.home.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by John_Libo on 2016/8/26.
 */
public class ServiceClasses extends BaseEntity{
    private String id;
    private String name;
    private String logo;
    List<ServiceServicesEntity> services;

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<ServiceServicesEntity> getServices() {
        return services;
    }

    public void setServices(List<ServiceServicesEntity> services) {
        this.services = services;
    }


}
