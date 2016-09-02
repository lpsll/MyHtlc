package com.autodesk.easyhome.shejijia.mine.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by John_Libo on 2016/9/2.
 */
public class MineCouponData extends BaseEntity {
    private String page;
    private String pageCount;
    private String total;
    List<MineCouponEntity> data;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<MineCouponEntity> getData() {
        return data;
    }

    public void setData(List<MineCouponEntity> data) {
        this.data = data;
    }



}
