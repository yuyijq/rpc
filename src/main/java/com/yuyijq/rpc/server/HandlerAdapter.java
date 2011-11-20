package com.yuyijq.rpc.server;

import com.yuyijq.rpc.model.Request;
import com.yuyijq.rpc.model.Response;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * User: zhaohuiyu
 * Date: 11/19/11
 * Time: 2:45 PM
 */
public class HandlerAdapter {
    private Object service;
    private Method handle;

    public HandlerAdapter(Object service, Method handle) {
        this.service = service;
        this.handle = handle;
    }

    public void handle(Request request, Response response) {
        try {
            Object result = this.handle.invoke(service, request.getParameters());
            response.write(result);
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }
    }
}
