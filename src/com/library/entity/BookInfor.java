package com.library.entity;

import java.sql.Date;

public class BookInfor {
    private String ISBN;       //图书编号
    private Integer typeId;        //类别编号
    private String bookName;   //图书名称
    private String writer;     //作者
    private String translator; //译者
    private String publisher;  //出版社
    private Date date;         //出版日期
    private Double price;      //书籍价格
    private Integer quantity;  //书籍数量

    public BookInfor() {       //无参的构造函数
    }

    public BookInfor(String ISBN, Integer typeId, String bookName, String writer, String translator, String publisher, Date date, Double price, Integer quantity) {
        this.ISBN = ISBN;
        this.typeId = typeId;
        this.bookName = bookName;
        this.writer = writer;
        this.translator = translator;
        this.publisher = publisher;
        this.date = date;
        this.price = price;
        this.quantity = quantity;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
