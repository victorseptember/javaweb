package com.learn.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCP_demo {
    private static String connectionURL = "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf-8";
    private static String user = "root";
    private static String password = "xycxyc123";
    private static BasicDataSource ds;

    static {
        //初始化数据源
        ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl(connectionURL);
        ds.setUsername(user);
        ds.setPassword(password);

        ds.setInitialSize(5);  //初始五个连接
        ds.setMaxTotal(20);
        ds.setMinIdle(3);   //至少保持三个空闲连接
    }

    public static Connection getConnection(){
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(ResultSet set, Statement state, Connection con){
        closeResultSet(set);
        closeStatement(state);
        closeConnection(con);
    }

    public static void close(Statement state1, Statement state2,Connection con) {
        closeStatement(state2);
        closeStatement(state1);
        closeConnection(con);
    }

    private static void closeResultSet(ResultSet set){
        if (set!=null) {
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void closeStatement(Statement state){
        if (state!=null) {
            try {
                state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void closeConnection(Connection con) {  //归还的时候再将连接添加回连接池
        if (con!=null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
