package com.yuyijq.rpc.client;

import com.yuyijq.rpc.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * User: zhaohuiyu
 * Date: 11/10/11
 * Time: 12:16 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ClientFactoryIntegrationTest {
    @Autowired
    private UserService userService;

    @Test
//    @Ignore("Use case for integration with spring")
    public void should_integration_with_spring() {
        User user = userService.findById(1L);

        assertNotNull(user);
    }
}
