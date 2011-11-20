package com.yuyijq.rpc.server;

import com.yuyijq.rpc.User;

public class UserHibernateService {
    public User findById(Long id) {
        User user = new User();
        user.setUserName("yuyijq");
        user.setPassword("123456");
        return user;
    }
}
