package com.library.entity;

public class BookType {
    private Integer id;          //图书类别编号
    private String typeName; //图书类别名称
    private Integer days;        //可借天数
    private Double Fk;        //迟还一天的罚款数目

    public BookType() {
    }

    public BookType(Integer id, String typeName, Integer days, Double fk) {
        this.id = id;
        this.typeName = typeName;
        this.days = days;
        Fk = fk;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Double getFk() {
        return Fk;
    }

    public void setFk(Double fk) {
        Fk = fk;
    }

    @Override
    public String toString() {
        return "BookType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", days=" + days +
                ", Fk=" + Fk +
                '}';
    }
}
