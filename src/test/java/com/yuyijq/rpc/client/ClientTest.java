package com.yuyijq.rpc.client;

import com.yuyijq.rpc.User;
import com.yuyijq.rpc.UserService;
import com.yuyijq.rpc.client.netty.NettyRpcTransport;
import org.junit.Test;

import java.net.InetSocketAddress;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * User: zhaohuiyu
 * Date: 11/20/11
 * Time: 10:46 AM
 */
public class ClientTest {
    @Test
    public void should_call_remote_rpc_server() {
        RpcTransport transport = new NettyRpcTransport();
        transport.connect(new InetSocketAddress("127.0.0.1", 9006));
        ChannelFactory channelFactory = new ChannelFactory(transport);
        ClientFactory clientFactory = new ClientFactory(new ClientProxy(channelFactory));
        UserService service = clientFactory.getService(UserService.class);

        User user = service.findById(1L);

        assertThat(user.getUserName(), is("yuyijq"));

        service.findByIdAsync(1L, new Callback<User>() {
            public void receive(User user1) {
                assertThat(user1.getUserName(), is("yuyijq"));
            }
        });
    }
}
