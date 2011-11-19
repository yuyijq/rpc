package com.yuyijq.rpc.server;

import org.junit.Test;

import java.io.IOException;

public class RpcServerTest {
    @Test
    public void should_start_rpc_server() throws IOException {
        Router router = new Router();
        router.register("user",new UserHibernateService());
        RpcDispatcher dispatcher = new RpcDispatcher(router);
        RpcServer server = new RpcServer(dispatcher);
        server.start("127.0.0.1",9006);
    }
}
