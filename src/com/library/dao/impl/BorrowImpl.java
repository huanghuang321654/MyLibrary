package com.library.dao.impl;

import com.library.dao.BorrowDao;
import com.library.entity.Borrow;
import com.library.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowImpl implements BorrowDao {

    //借阅记录插入语句
    @Override
    public int insert(Borrow borrow) {
        //SQL语句
        String sql = "INSERT INTO `table_borrow` (`bookISBN`,`operatorId`,`readerISBN`,`isback`,`borrowDate`,`backDate`,`fk`) VALUES (?,?,?,?,?,?,?)";
        //受影响的行数
        int result = 0;
        //数据库连接对象
        Connection connection = JDBCUtils.getConnection();
        //语句执行平台
        PreparedStatement preparedStatement = null;
        try {
            //连接数据库并且装入执行语句
            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1,borrow.getId());
            preparedStatement.setString(1,borrow.getBookISBN());
            preparedStatement.setInt(2,borrow.getOperatorId());
            preparedStatement.setString(3,borrow.getReaderISBN());
            preparedStatement.setInt(4,borrow.getIsBack());
            preparedStatement.setDate(5,borrow.getBorrowDate());
            preparedStatement.setDate(6,borrow.getBackDate());
            preparedStatement.setDouble(7,borrow.getFk());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭资源
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //图书借阅记录删除语句
    @Override
    public int delete(Integer id) {
        //SQL语句
        String sql = "DELETE FROM `table_borrow` WHERE `id` = ?";
        //影响行数
        int result = 0;
        //连接语句
        Connection connection = JDBCUtils.getConnection();
        //语句执行平台
        PreparedStatement preparedStatement = null;
        try {
            //连接数据库并且装入执行语句
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭资源
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //图书借阅记录更新技术
    @Override
    public int update(Borrow borrow) {
        //SQL语句
        String sql = "UPDATE `table_borrow` SET `bookISBN` = ?,`operatorId` = ?,`readerISBN` = ?,`isback` = ?,`borrowDate` = ?,`backDate` = ? ,`fk` = ? WHERE `id` = ?";
        //受影响的行数
        int result = 0;
        //数据库连接对象
        Connection connection = null;
        //语句执行平台
        PreparedStatement preparedStatement = null;
        try {
            //连接数据库并且装入执行语句
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,borrow.getBookISBN());
            preparedStatement.setInt(2,borrow.getOperatorId());
            preparedStatement.setString(3,borrow.getReaderISBN());
            preparedStatement.setInt(4,borrow.getIsBack());
            preparedStatement.setDate(5,borrow.getBorrowDate());
            preparedStatement.setDate(6,borrow.getBackDate());
            preparedStatement.setDouble(7,borrow.getFk());
            preparedStatement.setInt(8,borrow.getId());
            result = preparedStatement.executeUpdate(); //受影响的行数赋值
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭资源
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //通过借阅ID进行查询
    @Override
    public List<Borrow> queryByBorrowId(Integer id) {
        //SQL语句
        String sql = "SELECT * FROM `table_borrow` WHERE `id` = ?";
        //数据库连接语句
        Connection connection = JDBCUtils.getConnection();
        //平台执行语句
        PreparedStatement preparedStatement = null;
        //查询遍历集合
        ResultSet resultSet = null;
        List<Borrow> list = new ArrayList<Borrow>();
        try {
            //连接数据库并且装入执行语句
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                //通过循环得到resultSet返回的每一个元素，并装入list集合中返回
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
                //关闭资源
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    //查询所有借阅记录
    @Override
    public List<Borrow> queryAll() {
        //SQL语句
        String sql = "SELECT * FROM `table_borrow`";
        //数据库连接语句
        Connection connection = JDBCUtils.getConnection();
        //平台执行语句
        PreparedStatement preparedStatement = null;
        //查询遍历集合
        ResultSet resultSet = null;
        List<Borrow> list = new ArrayList<Borrow>();
        try {
            //连接数据库并且装入执行语句
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                //通过循环得到resultSet返回的每一个元素，并装入list集合中返回
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
                //关闭资源
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
