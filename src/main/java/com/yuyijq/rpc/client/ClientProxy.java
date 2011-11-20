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

    public Object invoke(Object o, Method asyncMethod, Object[] objects) throws Throwable {
        if (asyncMethod.getName().equalsIgnoreCase("toString")) {
            return null;
        }
        if (asyncMethod.getName().startsWith("begin")) {
            String method = asyncMethod.getName().substring("begin".length());
            channelFactory.getChannel().asyncInvoke(asyncMethod.getDeclaringClass().getSimpleName(), method, objects);
            return null;
        } else {
            return channelFactory.getChannel().invoke(asyncMethod, objects);
        }
    }
}
