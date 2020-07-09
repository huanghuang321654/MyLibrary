package com.library.entity;

import java.sql.Date;

public class Reader {
    private String name;                 //读者姓名
    private String sex;                  //读者性别
    private Integer age;                     //读者年龄
    private String identityCard;         //证件号码
    private Date date;                   //会员证有效日期
    private Integer maxNum;                  //最大借书量
    private String tel;                  //电话号码
    private Double keepMoney;            //押金
    private Integer zj;                      //证件类型
    private String zy;                   //职业
    private String ISBN;                 //读者编号
    private Date bztime;                 //办证日期

    public Reader() {
    }

    public Reader(String name, String sex, Integer age, String identityCard, Date date, Integer maxNum, String tel, Double keepMoney, Integer zj, String zy, String ISBN, Date bztime) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.identityCard = identityCard;
        this.date = date;
        this.maxNum = maxNum;
        this.tel = tel;
        this.keepMoney = keepMoney;
        this.zj = zj;
        this.zy = zy;
        this.ISBN = ISBN;
        this.bztime = bztime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Double getKeepMoney() {
        return keepMoney;
    }

    public void setKeepMoney(Double keepMoney) {
        this.keepMoney = keepMoney;
    }

    public Integer getZj() {
        return zj;
    }

    public void setZj(Integer zj) {
        this.zj = zj;
    }

    public String getZy() {
        return zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Date getBztime() {
        return bztime;
    }

    public void setBztime(Date bztime) {
        this.bztime = bztime;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", identityCard='" + identityCard + '\'' +
                ", date=" + date +
                ", maxNum=" + maxNum +
                ", tel='" + tel + '\'' +
                ", keepMoney=" + keepMoney +
                ", zj=" + zj +
                ", zy='" + zy + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", bztime=" + bztime +
                '}';
    }
}
