package com.learn.login.service.impl;

import com.learn.login.dao.UserDao;
import com.learn.login.dao.impl.UserDaoImpl;
import com.learn.login.pojo.User;
import com.learn.login.service.UserService;

public class UserServiceImpl implements UserService {

    UserDao user = new UserDaoImpl();
    @Override
    public User checkUserLoginService(String uname, String pwd) {

        return user.checkUserLoginDao(uname, pwd);
    }
}
