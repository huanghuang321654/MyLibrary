package com.library.entity;

import java.sql.Date;

public class Operator {
    private Integer id;                       //操作员编号
    private String name;                  //用户名
    private String sex;                   //操作员性别
    private Integer age;                      //操作员年龄
    private String identityCard;          //证件号码
    private Date workDate;                //工作时间
    private String tel;                   //电话号码
    private Integer admin;                    //是否为管理员
    private String password;              //密码

    public Operator() {
    }

    public Operator(Integer id, String name, String sex, Integer age, String identityCard, Date workDate, String tel, Integer admin, String password) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.identityCard = identityCard;
        this.workDate = workDate;
        this.tel = tel;
        this.admin = admin;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", identityCard='" + identityCard + '\'' +
                ", workDate=" + workDate +
                ", tel='" + tel + '\'' +
                ", admin=" + admin +
                ", password='" + password + '\'' +
                '}';
    }
}
