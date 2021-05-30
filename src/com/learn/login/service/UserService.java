package com.learn.login.service;

import com.learn.login.pojo.User;

public interface UserService {
    User checkUserLoginService(String uname, String pwd);
}
