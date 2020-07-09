package com.library.service;

import com.library.entity.Order;

import java.util.List;

public interface OrderService {
    //插入方法
    int insert(Order order);

    //删除方法
    int delete(String ISBN);

    //修改方法
    int update(Order order);

    //通过书籍编号对订购信息进行查询
    List<Order> queryByISBN(String ISBN);

    //查询全部订购信息
    List<Order> queryAll();
}
