package com.library.dao.impl;

import com.library.dao.OrderDao;
import com.library.entity.Order;
import com.library.utils.JDBCUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderImpl implements OrderDao {

    @Override
    public int insert(Order order) {
        //SQL语句
        String sql = "INSERT INTO `table_order` (`ISBN`,`typeId`,`bookname`,`writer`,`translator`,`publisher`,`publicationdate`,`price`,`date`,`number`,`operator`,`checkAndAccept`,`zk`,`orderprice`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //受影响的行数
        int result = 0;
        //数据库连接对象
        Connection connection = JDBCUtils.getConnection();
        //语句执行平台
        PreparedStatement preparedStatement = null;
        try {
            //连接数据库并且装入执行语句
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,order.getISBN());
            preparedStatement.setInt(2,order.getTypeId());
            preparedStatement.setString(3,order.getBookName());
            preparedStatement.setString(4,order.getWriter());
            preparedStatement.setString(5,order.getTranslator());
            preparedStatement.setString(6,order.getPublisher());
            preparedStatement.setDate(7,order.getPublicationDate());
            preparedStatement.setDouble(8,order.getPrice());
            preparedStatement.setDate(9,order.getDate());
            preparedStatement.setInt(10,order.getNumber());
            preparedStatement.setString(11,order.getOperator());
            preparedStatement.setInt(12,order.getCheckAndAccept());
            preparedStatement.setDouble(13,order.getZk());
            preparedStatement.setDouble(14,order.getOrderprice());
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

    //订单删除功能
    @Override
    public int delete(String ISBN) {
        //SQL语句
        String sql = "DELETE FROM `table_order` WHERE `ISBN` = ?";
        //影响行数
        int result = 0;
        //连接语句
        Connection connection = JDBCUtils.getConnection();
        //语句执行平台
        PreparedStatement preparedStatement = null;
        try {
            //连接数据库并且装入执行语句
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

    //订单信息更新功能
    @Override
    public int update(Order order) {
        //SQL语句
        String sql = "UPDATE `table_order` SET `typeId` = ?,`bookname` = ?,`writer` = ?,`translator` = ?,`publisher` = ?,`publicationdate` = ?,`price` = ?,`date` = ?,`number` = ?,`operator` = ?,`checkAndAccept` = ?,`zk` = ?,`orderprice` = ? WHERE `ISBN` = ?";
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
            preparedStatement.setInt(1,order.getTypeId());
            preparedStatement.setString(2,order.getBookName());
            preparedStatement.setString(3,order.getWriter());
            preparedStatement.setString(4,order.getTranslator());
            preparedStatement.setString(5,order.getPublisher());
            preparedStatement.setDate(6,order.getPublicationDate());
            preparedStatement.setDouble(7,order.getPrice());
            preparedStatement.setDate(8,order.getDate());
            preparedStatement.setInt(9,order.getNumber());
            preparedStatement.setString(10,order.getOperator());
            preparedStatement.setInt(11,order.getCheckAndAccept());
            preparedStatement.setDouble(12,order.getZk());
            preparedStatement.setDouble(13,order.getOrderprice());
            preparedStatement.setString(14,order.getISBN());
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

    //通过ISBN查询
    @Override
    public List<Order> queryByISBN(String ISBN) {
        //SQL语句
        String sql = "SELECT * FROM `table_order` WHERE `ISBN` = ?";
        //数据库连接语句
        Connection connection = JDBCUtils.getConnection();
        //平台执行语句
        PreparedStatement preparedStatement = null;
        //查询遍历集合
        ResultSet resultSet = null;
        List<Order> list = new ArrayList<Order>();
        try {
            //连接数据库并且装入执行语句
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,ISBN);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                //通过循环得到resultSet返回的每一个元素，并装入list集合中返回
                Order order = new Order();
                order.setISBN(resultSet.getString(1));
                order.setTypeId(resultSet.getInt(2));
                order.setBookName(resultSet.getString(3));
                order.setWriter(resultSet.getString(4));
                order.setTranslator(resultSet.getString(5));
                order.setPublisher(resultSet.getString(6));
                order.setPublicationDate(resultSet.getDate(7));
                order.setPrice(resultSet.getDouble(8));
                order.setDate(resultSet.getDate(9));
                order.setNumber(resultSet.getInt(10));
                order.setOperator(resultSet.getString(11));
                order.setCheckAndAccept(resultSet.getInt(12));
                order.setZk(resultSet.getDouble(13));
                order.setOrderprice(resultSet.getDouble(14));
                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //查询语句
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    //查询所有
    @Override
    public List<Order> queryAll() {
        //SQL语句
        String sql = "SELECT * FROM `table_order`";
        //数据库连接语句
        Connection connection = JDBCUtils.getConnection();
        //平台执行语句
        PreparedStatement preparedStatement = null;
        //查询遍历集合
        ResultSet resultSet = null;
        List<Order> list = new ArrayList<Order>();
        try {
            //连接数据库并且装入执行语句
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                //通过循环得到resultSet返回的每一个元素，并装入list集合中返回
                Order order = new Order();
                order.setISBN(resultSet.getString(1));
                order.setTypeId(resultSet.getInt(2));
                order.setBookName(resultSet.getString(3));
                order.setWriter(resultSet.getString(4));
                order.setTranslator(resultSet.getString(5));
                order.setPublisher(resultSet.getString(6));
                order.setPublicationDate(resultSet.getDate(7));
                order.setPrice(resultSet.getDouble(8));
                order.setDate(resultSet.getDate(9));
                order.setNumber(resultSet.getInt(10));
                order.setOperator(resultSet.getString(11));
                order.setCheckAndAccept(resultSet.getInt(12));
                order.setZk(resultSet.getDouble(13));
                order.setOrderprice(resultSet.getDouble(14));
                list.add(order);
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
