package com.yuyijq.rpc.client;

import com.yuyijq.rpc.UserService;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * User: zhaohuiyu
 * Date: 11/9/11
 * Time: 11:36 PM
 */
public class ClientFactoryTest {
    @Test
    @Ignore("Just for use case, I want to use rpc as this way")
    public void should_create_proxy() {
        ClientFactory clientFactory = new ClientFactory(new ClientProxy(new ChannelFactory()));
        UserService service = clientFactory.getService(UserService.class);

        User user = service.findById(1L);

        assertNotNull(user);
    }
}
