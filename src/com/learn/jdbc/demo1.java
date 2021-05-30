package com.learn.jdbc;

import java.sql.*;

public class demo1 {

    public static void main(String[] args) {
//        selectAll();
//        System.out.println(selectUser("xu","1239"));  //sql注入攻击
        insertUser("王","111");
//        transferAccount("小明","李华",2000);
    }

    public static void selectAll(){
        Connection con = null;
        Statement state = null;
        ResultSet set = null;

        try {
            con = JDBCUtils.getConnection();
            /*发起查询请求*/
            state = con.createStatement();
            set = state.executeQuery("select * from user2");

            while (set.next()){
                System.out.println(set.getString(1)+","+set.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(set, state, con);
        }
    }


    public static boolean selectUser(String usename, String password){

        Connection con = null;
//        Statement state = null;
        ResultSet set = null;
        try {
            con = JDBCUtils.getConnection();

            /*存在漏洞，可以进行sql注入攻击
            state = con.createStatement();
            String sql = "select * from user where id = "+id+" and name='"+name+"'";
            set = state.executeQuery(sql);*/
//            state = con.createStatement();
            String sql = "select * from user2 where usename = ? and password = ? ";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,usename);
            preparedStatement.setString(2,password);

            set = preparedStatement.executeQuery();   //进行查询

            if (set.next()){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void insertUser(String usename, String password){
        Connection con = null;
        PreparedStatement state = null;
        ResultSet set = null;

        try {
            con = JDBCUtils.getConnection();
            /*发起查询请求*/
            String sql = "insert into user2 values (?,?)";
            state = con.prepareStatement(sql);
            state.setString(1,usename);
            state.setString(2,password);

            int result = state.executeUpdate();
            System.out.println(result);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(set, state, con);
        }
    }

    /*user1转账给user2*/
    public static void transferAccount(String username1, String username2, int money){
        Connection con = null;
        PreparedStatement state1 = null;
        PreparedStatement state2 = null;

        try {
            con = DBCP_demo.getConnection();

            con.setAutoCommit(false); //开启事务

            String sql1 = "update bank set money=money-? where usename = ?";
            state1 = con.prepareStatement(sql1);
            state1.setInt(1,money);
            state1.setString(2,username1);
            state1.executeUpdate();

//            String str = null;
//            str.charAt(2);

            String sql2 = "update bank set money=money+? where usename = ?";
            state2 = con.prepareStatement(sql2);
            state2.setInt(1,money);
            state2.setString(2,username2);
            state2.executeUpdate();


            con.commit();//关闭事务

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBCP_demo.close(state1, state2, con);
        }
    }


}
