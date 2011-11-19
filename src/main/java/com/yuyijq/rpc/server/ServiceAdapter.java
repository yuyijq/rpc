package com.yuyijq.rpc.server;

import com.yuyijq.rpc.model.HttpRequest;
import com.yuyijq.rpc.model.Request;
import com.yuyijq.rpc.model.Response;
import com.yuyijq.rpc.model.TransportData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * User: zhaohuiyu
 * Date: 11/15/11
 * Time: 10:32 PM
 */
public class ServiceAdapter {
    private Object service;
    private Method handle;

    public ServiceAdapter(Object service, Method handle) {
        this.service = service;
        this.handle = handle;
    }

    public TransportData handle(Request request, Response response) {
        try {
            HttpRequest httpRequest = (HttpRequest)request;
            Class<?>[] parameterTypes = this.handle.getParameterTypes();
            for (int i = 0;i < parameterTypes.length;++i) {
                Class<?> parameterType = parameterTypes[i];
               if(parameterType.isPrimitive()){
                   if(parameterType == String.class){
                   }
               }
            }
            Object result = this.handle.invoke(this.service, request.getParameters());
            response.write(result);
            response.flush();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
