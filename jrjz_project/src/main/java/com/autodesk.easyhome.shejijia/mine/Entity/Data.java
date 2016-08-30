package com.autodesk.easyhome.shejijia.mine.Entity;

/**
 * Created by Administrator on 2016/8/30.
 *  "id": 0,
 "realName": "",
 "name": "",
 "mobilePhone": "",
 "email": "",
 "sex": "",
 "address": "",
 "points": 0,
 "balance": 0
 */
public class Data {


    private int id;
    private String realName;
    private String name;
    private String mobilePhone;
    private String email;
    private String sex;
    private String address;
    private double points;
    private double balance;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
