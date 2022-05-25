package com.example.backend.utils;

import com.example.backend.entity.User;
import com.example.backend.ex.ServiceException;

/**
 * A check class for User.
 * and throw an ServiceException if attr is not valid.
 * @author xsy420
 * */
public class UserUtil {
    public static void isUsername(String username) {
        if (username == null || username.length() == 0) throw new ServiceException("username is empty");
        if (username.length() < 3 || username.length() > 10) throw new ServiceException("用户名长度在3-10个字符");
    }

    public static void isPassword(String password) {
        if (password == null || password.length() == 0) throw new ServiceException("password is empty");
        if (password.length() < 6 || password.length() > 15) throw new ServiceException("密码长度在6-15个字符");
    }

    public static void isUser(User user, boolean checkLogin) {
        isUsername(user.getUsername());
        if (checkLogin) isPassword(user.getPassword());
    }

    public static void isUser(User user) {
        isUser(user, false);
    }

}
