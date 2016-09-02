package com.autodesk.easyhome.shejijia.order.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by John_Libo on 2016/9/2.
 */
public class ServiceCouponData extends BaseEntity {
    private String page;
    private String pageCount;
    private String total;
    List<ServiceCouponEntity> data;

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

    public List<ServiceCouponEntity> getData() {
        return data;
    }

    public void setData(List<ServiceCouponEntity> data) {
        this.data = data;
    }


}
