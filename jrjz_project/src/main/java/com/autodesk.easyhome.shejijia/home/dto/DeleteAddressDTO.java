package com.autodesk.easyhome.shejijia.home.dto;

import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;

/**
 * Created by John_Libo on 2016/8/28.
 */
public class DeleteAddressDTO extends BaseDTO {
    private String id;
    private String area;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }




}
