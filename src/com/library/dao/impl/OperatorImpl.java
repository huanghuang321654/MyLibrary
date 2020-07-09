package com.library.dao.impl;

import com.library.dao.OperatorDao;
import com.library.entity.Operator;
import com.library.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperatorImpl implements OperatorDao {


    @Override
    public boolean login(Operator operator) {
        // 1.定义sql语句
        String sql = "SELECT * FROM `table_operator` WHERE `name` = ? AND `Password` = ?";
        // 2.定义并获取链接
        Connection connection = JDBCUtils.getConnection();
        // 3.定义预编译语句执行平台
        PreparedStatement preparedStatement = null;
        // 4.定义结果集
        ResultSet resultSet = null;
        Operator operator1 = null;
        try {
            // 5.获取语句执行平台
            preparedStatement = connection.prepareStatement(sql);
            // 设置占位符?的具体内容
            preparedStatement.setString(1, operator.getName());
            preparedStatement.setString(2, operator.getPassword());
            // 6.获取结果集
            resultSet = preparedStatement.executeQuery();
            // 7.迭代结果集
            if (resultSet.next()) {
                // resultSet.getLong(1) 结果集获取列序号为1的Long类型的数据
                // 获取的数据类型是啥 就是getXXX 传入的就是列的序号 从1开始
                operator1 = new Operator();
                operator1.setName(resultSet.getString(1));
                operator1.setPassword(resultSet.getString(2));
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
        return (operator1 != null);
    }

    //操作员信息插入方法
    @Override
    public int insert(Operator operator) {
        //SQL语句
        String sql = "INSERT INTO `table_operator` (`name`,`sex`,`age`,`identityCard`,`workdate`,`tel`,`Password`) VALUES (?,?,?,?,?,?,?)";
        //受影响的行数
        int result = 0;
        //数据库连接对象
        Connection connection = JDBCUtils.getConnection();
        //语句执行平台
        PreparedStatement preparedStatement = null;
        try {
            //连接数据库并且装入执行语句
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,operator.getName());
            preparedStatement.setString(2,operator.getSex());
            preparedStatement.setInt(3,operator.getAge());
            preparedStatement.setString(4,operator.getIdentityCard());
            preparedStatement.setDate(5,operator.getWorkDate());
            preparedStatement.setString(6,operator.getTel());
            preparedStatement.setString(7,operator.getPassword());
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

    //操作员删除语句
    @Override
    public int delete(Integer id) {
        //SQL语句
        String sql = "DELETE FROM `table_operator` WHERE `id` = ?";
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

    //操作员信息更新语句
    @Override
    public int update(Operator operator) {
        //SQL语句
        String sql = "UPDATE `table_operator` SET `name` = ?,`sex` = ?,`age` = ?,`identityCard` = ?,`workdate` = ?,`tel` = ?,`admin` = ?,`Password` = ? WHERE `id` = ?";
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
            preparedStatement.setString(1,operator.getName());
            preparedStatement.setString(2,operator.getSex());
            preparedStatement.setInt(3,operator.getAge());
            preparedStatement.setString(4,operator.getIdentityCard());
            preparedStatement.setDate(5,operator.getWorkDate());
            preparedStatement.setString(6,operator.getTel());
            preparedStatement.setInt(7,operator.getAdmin());
            preparedStatement.setString(8,operator.getPassword());
            preparedStatement.setInt(9,operator.getId());
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

    //通过操作员ID进行查询
    @Override
    public List<Operator> queryByOperatorId(Integer id) {
        //SQL语句
        String sql = "SELECT * FROM `table_operator` WHERE `id` = ?";
        //数据库连接语句
        Connection connection = JDBCUtils.getConnection();
        //平台执行语句
        PreparedStatement preparedStatement = null;
        //查询遍历集合
        ResultSet resultSet = null;
        List<Operator> list = new ArrayList<Operator>();
        try {
            //连接数据库并且装入执行语句
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                //通过循环得到resultSet返回的每一个元素，并装入list集合中返回
                Operator operator = new Operator();
                operator.setId(resultSet.getInt(1));
                operator.setName(resultSet.getString(2));
                operator.setSex(resultSet.getString(3));
                operator.setAge(resultSet.getInt(4));
                operator.setIdentityCard(resultSet.getString(5));
                operator.setWorkDate(resultSet.getDate(6));
                operator.setTel(resultSet.getString(7));
                operator.setAdmin(resultSet.getInt(8));
                operator.setPassword(resultSet.getString(9));
                list.add(operator);
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

    //查询所有操作员信息
    @Override
    public List<Operator> queryAll() {
        //SQL语句
        String sql = "SELECT * FROM `table_operator`";
        //数据库连接语句
        Connection connection = JDBCUtils.getConnection();
        //平台执行语句
        PreparedStatement preparedStatement = null;
        //查询遍历集合
        ResultSet resultSet = null;
        List<Operator> list = new ArrayList<Operator>();
        try {
            //连接数据库并且装入执行语句
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                //通过循环得到resultSet返回的每一个元素，并装入list集合中返回
                Operator operator = new Operator();
                operator.setId(resultSet.getInt(1));
                operator.setName(resultSet.getString(2));
                operator.setSex(resultSet.getString(3));
                operator.setAge(resultSet.getInt(4));
                operator.setIdentityCard(resultSet.getString(5));
                operator.setWorkDate(resultSet.getDate(6));
                operator.setTel(resultSet.getString(7));
                operator.setAdmin(resultSet.getInt(8));
                operator.setPassword(resultSet.getString(9));
                list.add(operator);
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

    //通过操作员名字进行查询
    @Override
    public List<Operator> queryByOperatorName(String name) {
        //SQL语句
        String sql = "SELECT * FROM `table_operator` WHERE `name` LIKE ?";
        //数据库连接语句
        Connection connection = JDBCUtils.getConnection();
        //平台执行语句
        PreparedStatement preparedStatement = null;
        //查询遍历集合
        ResultSet resultSet = null;
        List<Operator> list = new ArrayList<Operator>();
        try {
            //连接数据库并且装入执行语句
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //通过循环得到resultSet返回的每一个元素，并装入list集合中返回
                Operator operator = new Operator();
                operator.setId(resultSet.getInt(1));
                operator.setName(resultSet.getString(2));
                operator.setSex(resultSet.getString(3));
                operator.setAge(resultSet.getInt(4));
                operator.setIdentityCard(resultSet.getString(5));
                operator.setWorkDate(resultSet.getDate(6));
                operator.setTel(resultSet.getString(7));
                operator.setAdmin(resultSet.getInt(8));
                operator.setPassword(resultSet.getString(9));
                list.add(operator);
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

