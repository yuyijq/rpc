package com.yuyijq.rpc.client;

import com.yuyijq.rpc.handler.Handler;
import com.yuyijq.rpc.model.Request;

import java.lang.reflect.Method;
import java.util.List;

/**
 * User: zhaohuiyu
 * Date: 11/10/11
 * Time: 9:56 PM
 */
public class Channel {
    private List<Handler> handlers;

    public Channel(List<Handler> handlers) {
        this.handlers = handlers;
    }

    public Object invoke(Method method, Object[] parameters) {
        return handlers.get(0).handle(new Request(method, parameters));
    }
}
