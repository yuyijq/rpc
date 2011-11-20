package com.yuyijq.rpc.client;

import com.yuyijq.rpc.User;
import com.yuyijq.rpc.UserService;
import com.yuyijq.rpc.client.netty.NettyRpcTransport;

import java.net.InetSocketAddress;

/**
 * User: zhaohuiyu
 * Date: 11/20/11
 * Time: 10:46 AM
 */
public class ClientTest {
    public static void main(String[] args) {
        RpcTransport transport = new NettyRpcTransport();
        transport.connect(new InetSocketAddress("127.0.0.1",9006));
        ChannelFactory channelFactory = new ChannelFactory(transport);
        ClientFactory clientFactory = new ClientFactory(new ClientProxy(channelFactory));
        UserService service = clientFactory.getService(UserService.class);

        User user = service.findById(1L);

        System.out.println(user.getUserName());
    }
}
