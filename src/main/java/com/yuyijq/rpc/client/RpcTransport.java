package com.yuyijq.rpc.client;

import com.yuyijq.rpc.model.Request;

import java.net.SocketAddress;

/**
 * User: zhaohuiyu
 * Date: 11/20/11
 * Time: 4:59 PM
 */
public interface RpcTransport {
    void connect(SocketAddress address);

    void send(Request request);

    Object receive();

    void asyncSend(Request request, Callback callback);
}
