package com.yuyijq.rpc.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * User: zhaohuiyu
 * Date: 11/10/11
 * Time: 7:52 AM
 */
public class ClientProxy implements InvocationHandler {
    private ChannelFactory channelFactory;

    public ClientProxy(ChannelFactory channelFactory) {
        this.channelFactory = channelFactory;
    }

    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if(method.getName().equalsIgnoreCase("toString")){
            return null;
        }
        return channelFactory.getChannel().invoke(method, objects);
    }
}
