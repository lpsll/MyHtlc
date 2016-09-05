package com.autodesk.easyhome.shejijia.mine.dto;

import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;

/**
 * Created by Administrator on 2016/9/4.
 * "accessToken": "",
 * "uid": "",
 * "timestamp": "",
 * "random": "",
 * "amount": 0,
 * "sign": ""
 */
public class zfbTopUpDTO extends BaseDTO {

//    public int amount;
//
//    public int getAmount() {
//        return amount;
//    }
//
//    public void setAmount(int amount) {
//        this.amount = amount;
//    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double amount;
}
