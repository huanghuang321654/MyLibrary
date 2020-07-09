package com.library.dao.impl;

import com.library.dao.BookTypeDao;
import com.library.entity.BookType;
import com.library.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookTypeImpl implements BookTypeDao {

    //图书类别插入语句
    @Override
    public int insert(BookType bookType) {
        //SQL语句
        String sql = "INSERT INTO `table_booktype` (`id`,`typeName`,`days`,`Fk`) VALUES (?,?,?,?)";
        //受影响的行数
        int result = 0;
        //数据库连接对象
        Connection connection = JDBCUtils.getConnection();
        //语句执行平台
        PreparedStatement preparedStatement = null;
        try {
            //连接数据库并且装入执行语句
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookType.getId());
            preparedStatement.setString(2, bookType.getTypeName());
            preparedStatement.setInt(3, bookType.getDays());
            preparedStatement.setDouble(4, bookType.getFk());
            //执行数据库执行语句并且返回受影响行数
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

    //图书类别删除语句
    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM `table_booktype` WHERE `id` = ?";
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

    //图书类别更新语句
    @Override
    public int update(BookType bookType) {
        //SQL语句
        String sql = "UPDATE `table_booktype` SET `typeName` = ?,`days` = ?,`Fk` = ? WHERE `id` = ?";
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
            preparedStatement.setString(1, bookType.getTypeName());
            preparedStatement.setInt(2, bookType.getDays());
            preparedStatement.setDouble(3, bookType.getFk());
            preparedStatement.setInt(4, bookType.getId());
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

    //通过图书类别ID查询语句
    @Override
    public List<BookType> queryByBookTypeId(Integer id) {
        //SQL语句
        String sql = "SELECT * FROM `table_booktype` WHERE `id` = ?";
        //数据库连接语句
        Connection connection = JDBCUtils.getConnection();
        //平台执行语句
        PreparedStatement preparedStatement = null;
        //查询遍历集合
        ResultSet resultSet = null;
        List<BookType> list = new ArrayList<BookType>();
        try {
            //连接数据库并且装入执行语句
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //通过循环得到resultSet返回的每一个元素，并装入list集合中返回
                BookType bookType = new BookType();
                bookType.setId(resultSet.getInt(1));
                bookType.setTypeName(resultSet.getString(2));
                bookType.setDays(resultSet.getInt(3));
                bookType.setFk(resultSet.getDouble(4));
                list.add(bookType);
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

    //查询所有图书类别
    @Override
    public List<BookType> queryAll() {
        //SQL语句
        String sql = "SELECT * FROM `table_booktype`";
        //数据连接语句
        Connection connection = JDBCUtils.getConnection();
        //平台执行语句
        PreparedStatement preparedStatement = null;
        //查询遍历集合
        ResultSet resultSet = null;
        List<BookType> list = new ArrayList<BookType>();
        try {
            //连接数据库并且装入执行语句
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //通过循环得到resultSet返回的每一个元素，并装入list集合中返回
                BookType bookType = new BookType();
                bookType.setId(resultSet.getInt(1));
                bookType.setTypeName(resultSet.getString(2));
                bookType.setDays(resultSet.getInt(3));
                bookType.setFk(resultSet.getDouble(4));
                list.add(bookType);
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

    //通过图书类别名查询
    @Override
    public List<BookType> queryByBookTypeName(String typeName) {
        //SQL语句
        String sql = "SELECT * FROM `table_booktype` WHERE `typeName` LIKE ?";
        //数据连接语句
        Connection connection = JDBCUtils.getConnection();
        //平台执行语句
        PreparedStatement preparedStatement = null;
        //查询遍历集合
        ResultSet resultSet = null;
        List<BookType> list = new ArrayList<BookType>();
        try {
            //连接数据库并且装入执行语句
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + typeName + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //通过循环得到resultSet返回的每一个元素，并装入list集合中返回
                BookType bookType = new BookType();
                bookType.setId(resultSet.getInt(1));
                bookType.setTypeName(resultSet.getString(2));
                bookType.setDays(resultSet.getInt(3));
                bookType.setFk(resultSet.getDouble(4));
                list.add(bookType);
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
