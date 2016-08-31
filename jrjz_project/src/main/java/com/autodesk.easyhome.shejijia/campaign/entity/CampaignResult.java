package com.autodesk.easyhome.shejijia.campaign.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */
public class CampaignResult extends BaseEntity {

   private List<CampaignEntity> data;

    public List<CampaignEntity> getData() {
        return data;
    }

    public void setData(List<CampaignEntity> data) {
        this.data = data;
    }
}
