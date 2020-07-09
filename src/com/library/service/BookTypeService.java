package com.library.service;

import com.library.entity.BookType;

import java.util.List;

public interface BookTypeService {
    //插入方法
    int insert(BookType bookType);

    //删除方法
    int delete(Integer id);

    //修改方法
    int update(BookType bookType);

    //通过ID进行查询的方法
    List<BookType> queryByBookTypeId(Integer id);

    //查询全部图书类别
    List<BookType> queryAll();

    ////通过名字进行查询的方法
    List<BookType> queryByBookTypeName(String typeName);
}
