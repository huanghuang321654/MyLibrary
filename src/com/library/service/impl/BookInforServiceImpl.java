package com.library.service.impl;

import com.library.dao.BookInforDao;
import com.library.dao.impl.BookInforDaoImpl;
import com.library.entity.BookInfor;
import com.library.service.BookInforService;

import java.awt.print.Book;
import java.util.List;

public class BookInforServiceImpl implements BookInforService {
    BookInforDao bookInforDao = new BookInforDaoImpl();

    public static void main(String[] args) {
        BookInforServiceImpl bookInforService = new BookInforServiceImpl();
        System.out.println(bookInforService.queryAll());
    }

    @Override
    public int insert(BookInfor bookInfor) {
        return bookInforDao.insert(bookInfor);
    }

    @Override
    public int delete(String ISBN) {
        return bookInforDao.delete(ISBN);
    }

    @Override
    public int update(BookInfor bookInfor) {
        return bookInforDao.update(bookInfor);
    }

    @Override
    public List<BookInfor> queryByISBN(String ISBN) {
        return bookInforDao.queryByISBN(ISBN);
    }

    @Override
    public List<BookInfor> queryAll() {
        return bookInforDao.queryAll();
    }

    @Override
    public List<BookInfor> queryByBookName(String bookName) {
        return bookInforDao.queryByBookName(bookName);
    }

    @Override
    public List<BookInfor> queryByBookType(Integer bookType) {
        return bookInforDao.queryByBookType(bookType);
    }

}
