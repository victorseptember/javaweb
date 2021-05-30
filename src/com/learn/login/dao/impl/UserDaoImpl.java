package com.learn.login.dao.impl;

import com.learn.login.dao.UserDao;
import com.learn.login.pojo.User;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    @Override
    public User checkUserLoginDao(String uname, String pwd) {
        //声明jdbc对象
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String connectionUrl = "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf-8&serverTimezone=GMT";
        String dbUsername = "root";
        String passwd = "xycxyc123";
        //声明变量
        User user = null;

        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            con = DriverManager.getConnection(connectionUrl,dbUsername,passwd);
            //创建sql语句
            String sql = "select * from t_user where uname = ? and pwd = ?";
            //创建sql语句对象
            ps = con.prepareStatement(sql);
            //给占位符赋值
            ps.setString(1,uname);
            ps.setString(2,pwd);
            //执行sql语句
            rs = ps.executeQuery();
            //遍历结果
            while (rs.next()){  //  返回的结果集不为空
                user = new User();
                user.setUid(rs.getInt("id"));
                user.setAge(rs.getInt("age"));
                user.setUname(rs.getString("uname"));
                user.setPwd(rs.getString("pwd"));
                user.setSex(rs.getString("sex"));
                user.setBirth(rs.getString("birth"));
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
        return user;
    }
}
