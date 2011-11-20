package com.yuyijq.rpc.server;

import com.yuyijq.rpc.server.handlermapping.StaticHandlerMapping;
import com.yuyijq.rpc.server.netty.NettyRpcTransport;
import com.yuyijq.rpc.server.transport.RpcTransport;

import java.io.IOException;

public class RpcServerTest {

    public static void main(String[] args) throws IOException {
        StaticHandlerMapping handlerMapping = new StaticHandlerMapping();
        handlerMapping.register("UserService", new UserHibernateService());
        RpcDispatcher dispatcher = new RpcDispatcher(handlerMapping);
        RpcTransport transport = new NettyRpcTransport(dispatcher);
        RpcServer server = new RpcServer(transport);
        server.start(9006);
    }
}
