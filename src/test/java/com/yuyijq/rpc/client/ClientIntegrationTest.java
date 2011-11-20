package com.yuyijq.rpc.client;

import com.yuyijq.rpc.User;
import com.yuyijq.rpc.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * User: zhaohuiyu
 * Date: 11/20/11
 * Time: 10:27 PM
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    public void should_call_remote_server() {
        User user = userService.findById(1L);

        assertThat(user.getUserName(), is("yuyijq"));
    }
}
