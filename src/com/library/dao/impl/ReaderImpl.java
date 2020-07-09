package com.library.dao.impl;

import com.library.dao.ReaderDao;
import com.library.entity.Reader;
import com.library.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderImpl implements ReaderDao {

    //读者信息插入语句
    @Override
    public int insert(Reader reader) {
        //SQL语句
        String sql = "INSERT INTO `table_reader` (`name`,`sex`,`age`,`identityCard`,`date`,`maxNum`,`tel`,`keepMoney`,`zj`,`zy`,`ISBN`,`bztime`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        //受影响的行数
        int result = 0;
        //数据库连接对象
        Connection connection = JDBCUtils.getConnection();
        //语句执行平台
        PreparedStatement preparedStatement = null;
        try {
            //连接数据库并且装入执行语句
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,reader.getName());
            preparedStatement.setString(2,reader.getSex());
            preparedStatement.setInt(3,reader.getAge());
            preparedStatement.setString(4,reader.getIdentityCard());
            preparedStatement.setDate(5,reader.getDate());
            preparedStatement.setInt(6,reader.getMaxNum());
            preparedStatement.setString(7,reader.getTel());
            preparedStatement.setDouble(8,reader.getKeepMoney());
            preparedStatement.setInt(9,reader.getZj());
            preparedStatement.setString(10,reader.getZy());
            preparedStatement.setString(11,reader.getISBN());
            preparedStatement.setDate(12,reader.getBztime());
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

    //读者信息删除
    @Override
    public int delete(String ISBN) {
        //sql语句
        String sql = "DELETE FROM `table_reader` WHERE `ISBN` = ?";
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

    //更改读者信息
    @Override
    public int update(Reader reader) {
        //SQL语句
        String sql = "UPDATE `table_reader` SET `name` = ?,`sex` = ?,`age` = ?,`identityCard` = ?,`date` = ?,`maxNum` = ?,`tel` = ?,`keepMoney` = ?,`zj` = ?,`zy` = ?,`bztime` = ? WHERE `ISBN` = ?";
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
            preparedStatement.setString(1,reader.getName());
            preparedStatement.setString(2,reader.getSex());
            preparedStatement.setInt(3,reader.getAge());
            preparedStatement.setString(4,reader.getIdentityCard());
            preparedStatement.setDate(5,reader.getDate());
            preparedStatement.setInt(6,reader.getMaxNum());
            preparedStatement.setString(7,reader.getTel());
            preparedStatement.setDouble(8,reader.getKeepMoney());
            preparedStatement.setInt(9,reader.getZj());
            preparedStatement.setString(10,reader.getZy());
            preparedStatement.setDate(11,reader.getBztime());
            preparedStatement.setString(12,reader.getISBN());
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

    //通过读者ISBN进行查询
    @Override
    public List<Reader> queryByReaderISBN(String ISBN) {
        //SQL语句
        String sql = "SELECT * FROM `table_reader` WHERE `ISBN` = ?";
        //数据库连接语句
        Connection connection = JDBCUtils.getConnection();
        //平台执行语句
        PreparedStatement preparedStatement = null;
        //查询遍历集合
        ResultSet resultSet = null;
        List<Reader> list = new ArrayList<Reader>();
        try {
            //连接数据库并且装入执行语句
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,ISBN);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                //通过循环得到resultSet返回的每一个元素，并装入list集合中返回
                Reader reader = new Reader();
                reader.setName(resultSet.getString(1));
                reader.setSex(resultSet.getString(2));
                reader.setAge(resultSet.getInt(3));
                reader.setIdentityCard(resultSet.getString(4));
                reader.setDate(resultSet.getDate(5));
                reader.setMaxNum(resultSet.getInt(6));
                reader.setTel(resultSet.getString(7));
                reader.setKeepMoney(resultSet.getDouble(8));
                reader.setZj(resultSet.getInt(9));
                reader.setZy(resultSet.getString(10));
                reader.setISBN(resultSet.getString(11));
                reader.setBztime(resultSet.getDate(12));
                list.add(reader);
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

    //查询所有读者信息
    @Override
    public List<Reader> queryAll() {
        //SQL语句
        String sql = "SELECT * FROM `table_reader`";
        //连接数据库
        Connection connection = JDBCUtils.getConnection();
        //语句执行平台
        PreparedStatement preparedStatement = null;
        //查询遍历集合
        ResultSet resultSet = null;
        List<Reader> list = new ArrayList<Reader>();
        try {
            //连接数据库并且装入执行语句
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                //通过循环得到resultSet返回的每一个元素，并装入list集合中返回
                Reader reader = new Reader();
                reader.setName(resultSet.getString(1));
                reader.setSex(resultSet.getString(2));
                reader.setAge(resultSet.getInt(3));
                reader.setIdentityCard(resultSet.getString(4));
                reader.setDate(resultSet.getDate(5));
                reader.setMaxNum(resultSet.getInt(6));
                reader.setTel(resultSet.getString(7));
                reader.setKeepMoney(resultSet.getDouble(8));
                reader.setZj(resultSet.getInt(9));
                reader.setZy(resultSet.getString(10));
                reader.setISBN(resultSet.getString(11));
                reader.setBztime(resultSet.getDate(12));
                list.add(reader);
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

    //通过读者姓名查询
    @Override
    public List<Reader> queryByReaderName(String name) {
        //SQL语句
        String sql = "SELECT * FROM `table_reader` WHERE `name` LIKE ?";
        //连接数据库
        Connection connection = JDBCUtils.getConnection();
        //平台执行语句
        PreparedStatement preparedStatement = null;
        //查询遍历集合
        ResultSet resultSet = null;
        List<Reader> list = new ArrayList<Reader>();
        try {
            //连接数据库并且装入执行语句
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //通过循环得到resultSet返回的每一个元素，并装入list集合中返回
                Reader reader = new Reader();
                reader.setName(resultSet.getString(1));
                reader.setSex(resultSet.getString(2));
                reader.setAge(resultSet.getInt(3));
                reader.setIdentityCard(resultSet.getString(4));
                reader.setDate(resultSet.getDate(5));
                reader.setMaxNum(resultSet.getInt(6));
                reader.setTel(resultSet.getString(7));
                reader.setKeepMoney(resultSet.getDouble(8));
                reader.setZj(resultSet.getInt(9));
                reader.setZy(resultSet.getString(10));
                reader.setISBN(resultSet.getString(11));
                reader.setBztime(resultSet.getDate(12));
                list.add(reader);
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
