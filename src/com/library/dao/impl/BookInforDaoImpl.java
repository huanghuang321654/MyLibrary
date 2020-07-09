package com.library.dao.impl;

import com.library.dao.BookInforDao;
import com.library.entity.BookInfor;
import com.library.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookInforDaoImpl implements BookInforDao {

    //插入图书的方法
    @Override
    public int insert(BookInfor bookInfor) {
        //SQL语句
        String sql = "INSERT INTO `table_bookinfo` (`ISBN`,`typeId`,`bookname`,`writer`,`translator`,`publisher`,`date`,`price`,`quantity`) VALUES (?,?,?,?,?,?,?,?,?)";
        //受影响的行数
        int result = 0;
        //数据库连接对象
        Connection connection = JDBCUtils.getConnection();
        //语句执行平台
        PreparedStatement preparedStatement = null;
        try {
            //连接数据库并且进行数据数据语句装入
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bookInfor.getISBN());
            preparedStatement.setInt(2, bookInfor.getTypeId());
            preparedStatement.setString(3, bookInfor.getBookName());
            preparedStatement.setString(4, bookInfor.getWriter());
            preparedStatement.setString(5, bookInfor.getTranslator());
            preparedStatement.setString(6, bookInfor.getPublisher());
            preparedStatement.setDate(7, bookInfor.getDate());
            preparedStatement.setDouble(8, bookInfor.getPrice());
            preparedStatement.setInt(9, bookInfor.getQuantity());
            //得到受影响行数
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

    //图书信息删除方法
    @Override
    public int delete(String ISBN) {
        String sql = "DELETE FROM `table_bookinfo` WHERE `ISBN` = ?";
        //影响行数
        int result = 0;
        //连接语句
        Connection connection = JDBCUtils.getConnection();
        //语句执行平台
        PreparedStatement preparedStatement = null;
        try {
            //连接数据库并且进行数据数据语句装入
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ISBN);
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

    //图书信息更新方法
    @Override
    public int update(BookInfor bookInfor) {
        //SQL语句
        String sql = "UPDATE `table_bookinfo` SET `typeId` = ?,`bookName` = ?,`writer` = ?,`translator` = ?,`publisher` = ?,`date` = ?,`price` = ?,`quantity` = ? WHERE `ISBN` = ?";
        //受影响的行数
        int result = 0;
        //数据库连接对象
        Connection connection = null;
        //语句执行平台
        PreparedStatement preparedStatement = null;
        try {
            //连接数据库并且进行数据数据语句装入
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookInfor.getTypeId());
            preparedStatement.setString(2, bookInfor.getBookName());
            preparedStatement.setString(3, bookInfor.getWriter());
            preparedStatement.setString(4, bookInfor.getTranslator());
            preparedStatement.setString(5, bookInfor.getPublisher());
            preparedStatement.setDate(6, bookInfor.getDate());
            preparedStatement.setDouble(7, bookInfor.getPrice());
            preparedStatement.setInt(8, bookInfor.getQuantity());
            preparedStatement.setString(9, bookInfor.getISBN());
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

    @Override
    public List<BookInfor> queryByISBN(String ISBN) {
        //SQL语句
        String sql = "SELECT * FROM `table_bookinfo` WHERE `ISBN` = ?";
        //数据库连接语句
        Connection connection = JDBCUtils.getConnection();
        //平台执行语句
        PreparedStatement preparedStatement = null;
        //查询遍历集合
        ResultSet resultSet = null;
        List<BookInfor> list = new ArrayList<BookInfor>();
        try {
            //连接数据库并且进行数据数据语句装入
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ISBN);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //使用循环将resultSet中的每一个元素装入list集合中
                BookInfor bookInfor = new BookInfor();
                bookInfor.setISBN(resultSet.getString(1));
                bookInfor.setTypeId(resultSet.getInt(2));
                bookInfor.setBookName(resultSet.getString(3));
                bookInfor.setWriter(resultSet.getString(4));
                bookInfor.setTranslator(resultSet.getString(5));
                bookInfor.setPublisher(resultSet.getString(6));
                bookInfor.setDate(resultSet.getDate(7));
                bookInfor.setPrice(resultSet.getDouble(8));
                bookInfor.setQuantity(resultSet.getInt(9));
                list.add(bookInfor);
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

    //查询所有的图书信息
    @Override
    public List<BookInfor> queryAll() {
        String sql = "SELECT * FROM `table_bookinfo`";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<BookInfor> list = new ArrayList<BookInfor>();
        try {
            //连接数据库并且进行数据数据语句装入
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //使用循环将resultSet中的每一个元素装入list集合中
                BookInfor bookInfor = new BookInfor();
                bookInfor.setISBN(resultSet.getString(1));
                bookInfor.setTypeId(resultSet.getInt(2));
                bookInfor.setBookName(resultSet.getString(3));
                bookInfor.setWriter(resultSet.getString(4));
                bookInfor.setTranslator(resultSet.getString(5));
                bookInfor.setPublisher(resultSet.getString(6));
                bookInfor.setDate(resultSet.getDate(7));
                bookInfor.setPrice(resultSet.getDouble(8));
                bookInfor.setQuantity(resultSet.getInt(9));
                list.add(bookInfor);
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

    //通过书名进行图书查询
    @Override
    public List<BookInfor> queryByBookName(String bookName) {
        //SQL语句
        String sql = "SELECT * FROM `table_bookinfo` WHERE `bookname` LIKE ?";
        //数据库连接语句
        Connection connection = JDBCUtils.getConnection();
        //平台执行语句
        PreparedStatement preparedStatement = null;
        //查询遍历集合
        ResultSet resultSet = null;
        List<BookInfor> list = new ArrayList<BookInfor>();
        try {
            //连接数据库并且进行数据数据语句装入
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + bookName + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //使用循环将resultSet中的每一个元素装入list集合中
                BookInfor bookInfor = new BookInfor();
                bookInfor.setISBN(resultSet.getString(1));
                bookInfor.setTypeId(resultSet.getInt(2));
                bookInfor.setBookName(resultSet.getString(3));
                bookInfor.setWriter(resultSet.getString(4));
                bookInfor.setTranslator(resultSet.getString(5));
                bookInfor.setPublisher(resultSet.getString(6));
                bookInfor.setDate(resultSet.getDate(7));
                bookInfor.setPrice(resultSet.getDouble(8));
                bookInfor.setQuantity(resultSet.getInt(9));
                list.add(bookInfor);
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

    //通过书本型号进行书本信息查询
    @Override
    public List<BookInfor> queryByBookType(Integer bookType) {
        //SQL语句
        String sql = "SELECT * FROM `table_bookinfo` WHERE `typeId` = ?";
        //数据库连接语句
        Connection connection = JDBCUtils.getConnection();
        //平台执行语句
        PreparedStatement preparedStatement = null;
        //查询遍历集合
        ResultSet resultSet = null;
        List<BookInfor> list = new ArrayList<BookInfor>();
        try {
            //连接数据库并且进行数据数据语句装入
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookType);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //使用循环将resultSet中的每一个元素装入list集合中
                BookInfor bookInfor = new BookInfor();
                bookInfor.setISBN(resultSet.getString(1));
                bookInfor.setTypeId(resultSet.getInt(2));
                bookInfor.setBookName(resultSet.getString(3));
                bookInfor.setWriter(resultSet.getString(4));
                bookInfor.setTranslator(resultSet.getString(5));
                bookInfor.setPublisher(resultSet.getString(6));
                bookInfor.setDate(resultSet.getDate(7));
                bookInfor.setPrice(resultSet.getDouble(8));
                bookInfor.setQuantity(resultSet.getInt(9));
                list.add(bookInfor);
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
