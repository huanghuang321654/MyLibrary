package com.library.service;

import com.library.entity.Borrow;

import java.util.List;

public interface BorrowService {
    //插入方法
    int insert(Borrow borrow);

    //删除方法
    int delete(Integer id);

    //修改方法
    int update(Borrow borrow);

    //通过借阅记录的ID进行查询
    List<Borrow> queryByBorrowId(Integer id);

    //查询全部借阅信息
    List<Borrow> queryAll();

    List<Borrow> queryByBookISBN(String ISBN);

    List<Borrow> queryByReaderISBN(String ISBN);
}
