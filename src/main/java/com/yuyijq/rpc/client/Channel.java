package com.yuyijq.rpc.client;

import com.yuyijq.rpc.model.Request;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * User: zhaohuiyu
 * Date: 11/10/11
 * Time: 9:56 PM
 */
public class Channel {
    private RpcTransport transport;

    public Channel(RpcTransport transport) {
        this.transport = transport;
    }

    public Object invoke(final Method method, final Object[] parameters) throws IOException {
        transport.send(new Request(method, parameters));
        return transport.receive();

    }

    public void invokeAsync(String service, String method, Object[] parameters) {
        Object[] newParameters = new Object[parameters.length - 1];
        for (int i = 0; i < parameters.length - 1; ++i) {
            newParameters[i] = parameters[i];
        }
        transport.asyncSend(new Request(service, method, newParameters), (Callback) parameters[parameters.length - 1]);
    }
}
