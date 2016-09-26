package com.autodesk.easyhome.shejijia.home.entity;

/**
 * Created by Administrator on 2016/9/26.
 */
public class OrderPayEvent {
    private String msg;
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public OrderPayEvent(String msg) {
        super();
        this.msg = msg;
    }
}
