package com.yuyijq.rpc.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * User: zhaohuiyu
 * Date: 11/10/11
 * Time: 7:52 AM
 */
public class ClientProxy implements InvocationHandler {
    public static final String ASYNC_SUFFIX = "Async";

    private ChannelFactory channelFactory;

    public ClientProxy(ChannelFactory channelFactory) {
        this.channelFactory = channelFactory;
    }

    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        String methodName = method.getName();
        if (isSpecialMethod(methodName)) {
            return null;
        }
        if (methodName.endsWith(ASYNC_SUFFIX)) {
            String modifiedMethodName = methodName.substring(0, methodName.lastIndexOf(ASYNC_SUFFIX));
            channelFactory.getChannel().invokeAsync(method.getDeclaringClass().getSimpleName(), modifiedMethodName, objects);
            return null;
        } else {
            return channelFactory.getChannel().invoke(method, objects);
        }
    }

    private boolean isSpecialMethod(String methodName) {
        return methodName.equalsIgnoreCase("toString");
    }
}
