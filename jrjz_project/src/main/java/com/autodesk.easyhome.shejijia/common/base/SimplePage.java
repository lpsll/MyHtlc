package com.autodesk.easyhome.shejijia.common.base;

import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.home.fragment.HomeServiceFragment;
import com.autodesk.easyhome.shejijia.home.fragment.NewsFragment;
import com.autodesk.easyhome.shejijia.home.fragment.SelectAddressFragment;
import com.autodesk.easyhome.shejijia.order.fragment.ServiceCouponFragment;

/**
 * Created by John_Libo on 2016/8/18.
 */
public enum SimplePage {

    SERVICE_COUPON(1, R.string.cp, ServiceCouponFragment.class),
    NEWS(2, R.string.news, NewsFragment.class),
    HOMESERVICE(3, R.string.home_service, HomeServiceFragment.class),
    ;

    private int title;
    private Class<?> clz;
    private int value;

    SimplePage(int value, int title, Class<?> clz) {
        this.value = value;
        this.title = title;
        this.clz = clz;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static SimplePage getPageByValue(int val) {
        for (SimplePage p : values()) {
            if (p.getValue() == val)
                return p;
        }
        return null;
    }
}
