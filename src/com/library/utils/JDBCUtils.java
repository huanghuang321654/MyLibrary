package com.library.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    // 数据库驱动,不需要改动
    private static String DRIVER = "com.mysql.jdbc.Driver";
    // 数据库连接URL
    private static String URL = "jdbc:mysql://127.0.0.1:3306/librarymanagesystem?useSSL=false&useUnicode=true&characterEncoding=utf8";
    // 数据库连接用户名
    private static String USERNAME = "root";
    // 数据库连接密码
    private static String PASSWORD = "root";

    static {
        try {
            // 加载JDBC驱动
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(DRIVER + "未找到");
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }

    private JDBCUtils() {
        super();
    }
}
