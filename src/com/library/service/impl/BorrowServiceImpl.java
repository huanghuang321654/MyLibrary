package com.library.service.impl;

import com.library.dao.BorrowDao;
import com.library.dao.impl.BorrowImpl;
import com.library.entity.Borrow;
import com.library.service.BorrowService;
import com.library.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowServiceImpl implements BorrowService {
    BorrowDao borrowDao = new BorrowImpl();

    @Override
    public int insert(Borrow borrow) {
        return borrowDao.insert(borrow);
    }

    @Override
    public int delete(Integer id) {
        return borrowDao.delete(id);
    }

    @Override
    public int update(Borrow borrow) {
        return borrowDao.update(borrow);
    }

    @Override
    public List<Borrow> queryByBorrowId(Integer id) {
        return borrowDao.queryByBorrowId(id);
    }

    @Override
    public List<Borrow> queryAll() {
        return borrowDao.queryAll();
    }

    @Override
    public List<Borrow> queryByBookISBN(String ISBN) {
        //SQL语句
        String sql = "SELECT * FROM `table_borrow` WHERE `bookISBN` = ?";
        //数据库连接语句
        Connection connection = JDBCUtils.getConnection();
        //平台执行语句
        PreparedStatement preparedStatement = null;
        //查询遍历集合
        ResultSet resultSet = null;
        List<Borrow> list = new ArrayList<Borrow>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,ISBN);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Borrow borrow = new Borrow();
                borrow.setId(resultSet.getInt(1));
                borrow.setBookISBN(resultSet.getString(2));
                borrow.setOperatorId(resultSet.getInt(3));
                borrow.setReaderISBN(resultSet.getString(4));
                borrow.setIsBack(resultSet.getInt(5));
                borrow.setBorrowDate(resultSet.getDate(6));
                borrow.setBackDate(resultSet.getDate(7));
                borrow.setFk(resultSet.getDouble(8));
                list.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public List<Borrow> queryByReaderISBN(String ISBN) {
        //SQL语句
        String sql = "SELECT * FROM `table_borrow` WHERE `readerISBN` = ?";
        //数据库连接语句
        Connection connection = JDBCUtils.getConnection();
        //平台执行语句
        PreparedStatement preparedStatement = null;
        //查询遍历集合
        ResultSet resultSet = null;
        List<Borrow> list = new ArrayList<Borrow>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,ISBN);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Borrow borrow = new Borrow();
                borrow.setId(resultSet.getInt(1));
                borrow.setBookISBN(resultSet.getString(2));
                borrow.setOperatorId(resultSet.getInt(3));
                borrow.setReaderISBN(resultSet.getString(4));
                borrow.setIsBack(resultSet.getInt(5));
                borrow.setBorrowDate(resultSet.getDate(6));
                borrow.setBackDate(resultSet.getDate(7));
                borrow.setFk(resultSet.getDouble(8));
                list.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
