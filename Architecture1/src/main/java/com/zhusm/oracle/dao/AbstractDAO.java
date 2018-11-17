package com.zhusm.oracle.dao;

import com.zhusm.oracle.dbc.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @description: DAO抽象类
 * @author: maker
 * @create: 2018/11/17
 */
public abstract class AbstractDAO {

    protected Connection connection = null;
    protected PreparedStatement statement = null;

    public AbstractDAO() {
        this.connection = DatabaseConnection.getConnection();
    }
}
