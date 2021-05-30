package com.learn.Servlet.session.login;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static List<User> list = new ArrayList<>();

    static {
        list.add(new User("aaa","111"));
        list.add(new User("bbb","222"));
        list.add(new User("ccc","333"));
    }

    public static User find(String username, String password) {
        for (User user:list) {
            if (user.getUsername().equals(username)&&user.getPassword().equals(password))
                return user;
        }
        return null;
    }


}
