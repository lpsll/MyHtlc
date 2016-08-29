package com.autodesk.easyhome.shejijia.order.dto;

import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;

/**
 * Created by John_Libo on 2016/8/29.
 */
public class CouponDTO extends BaseDTO {
    private String page;
    private String size;
    private String useStatus;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }



}
