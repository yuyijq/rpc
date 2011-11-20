package com.yuyijq.rpc;

import java.io.Serializable;

/**
 * User: zhaohuiyu
 * Date: 11/9/11
 * Time: 11:48 PM
 */
public class User implements Serializable{
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
