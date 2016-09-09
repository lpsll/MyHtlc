package com.autodesk.easyhome.shejijia.home.entity;

import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;

/**
 * Created by John_Libo on 2016/9/9.
 */
public class NewsEntity extends BaseEntity {
    private String createdate;
    private String id;
    private String smtype;
    private String smcontent;
    private String smtitle;
    private String readflag;

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSmtype() {
        return smtype;
    }

    public void setSmtype(String smtype) {
        this.smtype = smtype;
    }

    public String getSmcontent() {
        return smcontent;
    }

    public void setSmcontent(String smcontent) {
        this.smcontent = smcontent;
    }

    public String getSmtitle() {
        return smtitle;
    }

    public void setSmtitle(String smtitle) {
        this.smtitle = smtitle;
    }

    public String getReadflag() {
        return readflag;
    }

    public void setReadflag(String readflag) {
        this.readflag = readflag;
    }


}
