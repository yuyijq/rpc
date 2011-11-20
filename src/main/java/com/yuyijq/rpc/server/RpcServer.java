package com.yuyijq.rpc.server;

import com.yuyijq.rpc.server.transport.RpcTransport;

import java.io.IOException;

/**
 * User: zhaohuiyu
 * Date: 11/14/11
 * Time: 10:08 PM
 */
public class RpcServer {

    private RpcTransport transport;

    public RpcServer(RpcTransport transport) throws IOException {
        this.transport = transport;
    }

    public void start(int port) throws IOException {
        transport.start(port);
    }
}
