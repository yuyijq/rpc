package com.yuyijq.rpc.server;

import com.yuyijq.rpc.User;
import com.yuyijq.rpc.UserService;

public class UserHibernateService implements UserService {
    public User findById(Long id) {
        User user = new User();
        user.setUserName("yuyijq");
        user.setPassword("123456");
        return user;
    }
}
