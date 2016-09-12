package com.autodesk.easyhome.shejijia.home.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by John_Libo on 2016/9/12.
 */
public class HomeServiceData extends BaseEntity {
    List<HomeServiceEntity> classes;
    public List<HomeServiceEntity> getClasses() {
        return classes;
    }

    public void setClasses(List<HomeServiceEntity> classes) {
        this.classes = classes;
    }



}
