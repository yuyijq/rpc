package com.yuyijq.rpc.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * User: zhaohuiyu
 * Date: 11/9/11
 * Time: 11:32 PM
 */
public class ClientFactory {

    private InvocationHandler clientProxy;

    public ClientFactory(InvocationHandler clientProxy) {
        this.clientProxy = clientProxy;
    }

    public <TService> TService getService(Class<TService> clazz) {
        if (!clazz.isInterface())
            throw new RuntimeException("The contract type must be a interface");
        TService serviceProxy = (TService) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, clientProxy);
        return serviceProxy;
    }
}
