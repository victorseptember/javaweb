package com.learn.login.dao;

import com.learn.login.pojo.User;

public interface UserDao {
    /**
     * 从数据库中查询用户数据信息
     * @param uname
     * @param pwd
     * @return
     */
    User checkUserLoginDao(String uname, String pwd);
}
