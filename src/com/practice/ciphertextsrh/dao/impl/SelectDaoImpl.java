package com.practice.ciphertextsrh.dao.impl;

import com.practice.ciphertextsrh.dao.SelectDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectDaoImpl implements SelectDao {

    @Override
    public List<String> selectBreachDao(String string) {
        //声明jdbc对象
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String connectionUrl = "jdbc:mysql://localhost:3306/user?useSSL=false&characterEncoding=utf-8&serverTimezone=GMT";
        String dbUsername = "root";
        String passwd = "xycxyc123";
        List<String> list = new ArrayList<>();

        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            con = DriverManager.getConnection(connectionUrl,dbUsername,passwd);
            String sql = "select ciphertxt from user_c where hashPre = " + string;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String ciphertxt = rs.getString("ciphertxt");
                list.add(ciphertxt);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
