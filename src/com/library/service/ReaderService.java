package com.library.service;

import com.library.entity.Reader;

import java.util.List;

public interface ReaderService {
    //插入方法
    int insert(Reader reader);

    //删除方法
    int delete(String ISBN);

    //修改方法
    int update(Reader reader);

    //通过读者编号进行查询的方法
    List<Reader> queryByReaderISBN(String ISBN);

    //查询全部读者信息
    List<Reader> queryAll();

    ////通过读者名字进行查询的方法
    List<Reader> queryByReaderName(String name);
}
