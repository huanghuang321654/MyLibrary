package com.library.entity;

import java.sql.Date;

public class Borrow {
    private Integer id;               //借阅编号
    private String bookISBN;      //书籍编号
    private Integer operatorId;       //操作员编号
    private String readerISBN;    //读者编号
    private Integer isBack;           //是否归还
    private Date borrowDate;      //借书日期
    private Date backDate;        //应还日期
    private Double fk;             //罚款金额

    public Borrow() {
    }

    public Borrow(Integer id, String bookISBN, Integer operatorId, String readerISBN, Integer isBack, Date borrowDate, Date backDate, Double fk) {
        this.id = id;
        this.bookISBN = bookISBN;
        this.operatorId = operatorId;
        this.readerISBN = readerISBN;
        this.isBack = isBack;
        this.borrowDate = borrowDate;
        this.backDate = backDate;
        this.fk = fk;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getReaderISBN() {
        return readerISBN;
    }

    public void setReaderISBN(String readerISBN) {
        this.readerISBN = readerISBN;
    }

    public Integer getIsBack() {
        return isBack;
    }

    public void setIsBack(Integer isBack) {
        this.isBack = isBack;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getBackDate() {
        return backDate;
    }

    public void setBackDate(Date backDate) {
        this.backDate = backDate;
    }

    public Double getFk() {
        return fk;
    }

    public void setFk(Double fk) {
        this.fk = fk;
    }
}
