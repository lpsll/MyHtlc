package com.autodesk.easyhome.shejijia.home.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by John_Libo on 2016/8/27.
 */
public class ClassificationEntity extends BaseEntity {
    private String id;
    private String name;
    List<ClassificationServicesEntity> services;

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

    public List<ClassificationServicesEntity> getServices() {
        return services;
    }

    public void setServices(List<ClassificationServicesEntity> services) {
        this.services = services;
    }


}
