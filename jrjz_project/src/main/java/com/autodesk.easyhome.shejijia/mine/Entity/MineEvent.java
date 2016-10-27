package com.autodesk.easyhome.shejijia.mine.entity;

/**
 * Created by John_Libo on 2016/10/26.
 */
public class MineEvent {
    private String msg;
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public MineEvent(String msg) {
        super();
        this.msg = msg;
    }
}
