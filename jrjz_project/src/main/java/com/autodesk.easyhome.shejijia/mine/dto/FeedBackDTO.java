package com.autodesk.easyhome.shejijia.mine.dto;

import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;

/**
 * Created by Administrator on 2016/8/27.
 */
public class FeedBackDTO  extends BaseDTO{

    private String accessToken;
    private String fcontent;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getFcontent() {
        return fcontent;
    }

    public void setFcontent(String fcontent) {
        this.fcontent = fcontent;
    }
}
