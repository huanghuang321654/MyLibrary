package com.library.service.impl;

import com.library.dao.BookInforDao;
import com.library.dao.BookTypeDao;
import com.library.dao.impl.BookInforDaoImpl;
import com.library.dao.impl.BookTypeImpl;
import com.library.entity.BookType;
import com.library.service.BookTypeService;
import com.library.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookTypeServiceImpl implements BookTypeService {
    BookTypeDao bookTypeDao = new BookTypeImpl();
    public static void main(String[] args) {
        BookTypeServiceImpl bookTypeService = new BookTypeServiceImpl();
        System.out.println(bookTypeService.queryAll());
    }

    @Override
    public int insert(BookType bookType) {
        return bookTypeDao.insert(bookType);
    }

    @Override
    public int delete(Integer id) {
        return bookTypeDao.delete(id);
    }

    @Override
    public int update(BookType bookType) {
        return bookTypeDao.update(bookType);
    }

    @Override
    public List<BookType> queryByBookTypeId(Integer id) {
        return bookTypeDao.queryByBookTypeId(id);
    }

    @Override
    public List<BookType> queryAll() {
        return bookTypeDao.queryAll();
    }

    @Override
    public List<BookType> queryByBookTypeName(String typeName) {
        return bookTypeDao.queryByBookTypeName(typeName);
    }

}
