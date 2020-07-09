package com.library.entity;

import java.sql.Date;

public class Order {
    private String ISBN;            //书籍编号
    private Integer typeId;         //书籍类型
    private String bookName;        //书籍名字
    private String writer;          //作者
    private String translator;        //译者
    private String publisher;       //出版社
    private Date publicationDate;   //出版日期
    private Double price;           //书籍价格
    private Date date;              //订阅日期
    private Integer number;             //订购数量
    private String operator;        //操作员
    private Integer checkAndAccept;     //是否验收
    private Double zk;              //书籍的折扣
    private Double orderprice;         //书籍数量

    public Order() {
    }

    public Order(String ISBN, Integer typeId, String bookName, String writer, String translator, String publisher, Date publicationDate, Double price, Date date, Integer number, String operator, Integer checkAndAccept, Double zk, Double orderprice) {
        this.ISBN = ISBN;
        this.typeId = typeId;
        this.bookName = bookName;
        this.writer = writer;
        this.translator = translator;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.price = price;
        this.date = date;
        this.number = number;
        this.operator = operator;
        this.checkAndAccept = checkAndAccept;
        this.zk = zk;
        this.orderprice = orderprice;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getCheckAndAccept() {
        return checkAndAccept;
    }

    public void setCheckAndAccept(Integer checkAndAccept) {
        this.checkAndAccept = checkAndAccept;
    }

    public Double getZk() {
        return zk;
    }

    public void setZk(Double zk) {
        this.zk = zk;
    }

    public Double getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(Double orderprice) {
        this.orderprice = orderprice;
    }
}
