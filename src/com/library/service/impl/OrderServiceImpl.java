package com.library.service.impl;

import com.library.dao.OrderDao;
import com.library.dao.impl.OrderImpl;
import com.library.entity.Order;
import com.library.service.OrderService;
import com.library.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderImpl();

    @Override
    public int insert(Order order) {
        return orderDao.insert(order);
    }

    @Override
    public int delete(String ISBN) {
        return orderDao.delete(ISBN);
    }

    @Override
    public int update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public List<Order> queryByISBN(String ISBN) {
        return orderDao.queryByISBN(ISBN);
    }

    @Override
    public List<Order> queryAll() {
        return orderDao.queryAll();
    }
}
