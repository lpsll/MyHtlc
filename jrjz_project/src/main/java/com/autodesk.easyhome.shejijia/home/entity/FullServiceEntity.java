package com.autodesk.easyhome.shejijia.home.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

/**
 * Created by John_Libo on 2016/8/26.
 */
public class FullServiceEntity extends BaseEntity {
    private String id;
    private String creationTimestamp;
    private String modificationTimestamp;
    private String deleteRemark;
    private String name;
    private String descr;
    private String logo;
    private String pid;
    private String levelId;
    private String level;
    private String corder;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(String creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public String getModificationTimestamp() {
        return modificationTimestamp;
    }

    public void setModificationTimestamp(String modificationTimestamp) {
        this.modificationTimestamp = modificationTimestamp;
    }

    public String getDeleteRemark() {
        return deleteRemark;
    }

    public void setDeleteRemark(String deleteRemark) {
        this.deleteRemark = deleteRemark;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCorder() {
        return corder;
    }

    public void setCorder(String corder) {
        this.corder = corder;
    }


}
