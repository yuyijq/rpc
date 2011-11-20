package com.yuyijq.rpc.client;

/**
 * User: zhaohuiyu
 * Date: 11/20/11
 * Time: 8:37 PM
 */
public interface Callback<TResult> {
    void receive(TResult result);
}
