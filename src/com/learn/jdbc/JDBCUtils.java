/**
 * 把常用代码写成工具类，减少代码冗余
 */

package com.learn.jdbc;

import java.sql.*;
import java.util.ArrayList;

public class JDBCUtils {

    private static String connectionURL = "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf-8";
    private static String user = "root";
    private static String password = "xycxyc123";

    private static ArrayList<Connection> conList = new ArrayList<Connection>();  //用集合来存储连接，构造连接池

    static {
        for (int i=0;i<5;i++){
            Connection con = createConnection();
            conList.add(con);
        }
    }

    private static Connection createConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");//注册驱动

            /*建立与数据库的连接*/
            Connection con = DriverManager.getConnection(connectionURL,user,password);
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Connection getConnection(){
        if (conList.isEmpty()==false){
            Connection con = conList.get(0);
            conList.remove(con);
            return con;
        }else {
            return createConnection();
        }

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
//        if (con!=null) {
//            try {
//                con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
        conList.add(con);
    }

}
