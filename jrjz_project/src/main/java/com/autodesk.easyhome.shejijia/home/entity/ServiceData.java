package com.autodesk.easyhome.shejijia.home.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by John_Libo on 2016/8/26.
 */
public class ServiceData extends BaseEntity{
    List<ServiceClasses> classes;
    public List<ServiceClasses> getClasses() {
        return classes;
    }

    public void setClasses(List<ServiceClasses> classes) {
        this.classes = classes;
    }


}
