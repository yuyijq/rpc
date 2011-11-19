package com.yuyijq.rpc.server;

import com.yuyijq.rpc.UserService;
import com.yuyijq.rpc.client.User;

public class UserHibernateService implements UserService {
    public User findById(Long id) {
        return new User();
    }
}
