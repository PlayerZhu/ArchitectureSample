package com.zhusm.oracle.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @description: 数据库连接类
 * @author: maker
 * @create: 2018/11/17
 */
public class DatabaseConnection {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private DatabaseConnection() {
    }

    private static Connection rebuildConnection() {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("dbd");
            String driver = rb.getString("driver");
            String url = rb.getString("url");
            String user = rb.getString("user");
            String password = rb.getString("password");
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Connection getConnection() {
        Connection connection = threadLocal.get();
        if (connection == null) {
            connection = rebuildConnection();
            threadLocal.set(connection);
        }
        return connection;
    }

    public static void close() {
        Connection connection = threadLocal.get();
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            threadLocal.remove();
        }
    }
}
