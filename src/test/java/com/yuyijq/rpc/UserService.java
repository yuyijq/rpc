package com.yuyijq.rpc;

import com.yuyijq.rpc.client.User;

/**
 * User: zhaohuiyu
 * Date: 11/9/11
 * Time: 11:46 PM
 */
public interface UserService {
    User findById(Long id);
}
