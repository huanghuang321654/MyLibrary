package com.library.service;

import com.library.entity.BookInfor;

import java.util.List;

public interface BookInforService {
    //插入方法
    int insert(BookInfor bookInfor);

    //删除方法
    int delete(String ISBN);

    //修改方法
    int update(BookInfor bookInfor);

    //通过编号进行查询
    List<BookInfor> queryByISBN(String ISBN);

    //查询所有图书信息
    List<BookInfor> queryAll();

    //通过书名进行查询
    List<BookInfor> queryByBookName(String bookName);

    List<BookInfor> queryByBookType(Integer bookType);
}
