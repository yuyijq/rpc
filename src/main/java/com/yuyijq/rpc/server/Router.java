package com.yuyijq.rpc.server;

import com.yuyijq.rpc.model.Request;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * User: zhaohuiyu
 * Date: 11/15/11
 * Time: 10:33 PM
 */
public class Router {

    private Map<String, Object> serviceMap = new HashMap<String, Object>();

    public ServiceAdapter route(Request request) {
        Object service = serviceMap.get(request.getService());
        Method[] handles = service.getClass().getDeclaredMethods();
        for (Method handle : handles) {
            if (handle.getName().equals(request.getMethod())) {
                return new ServiceAdapter(service, handle);
            }
        }
        return null;
    }

    public void register(String service, Object serviceInstance) {
        serviceMap.put(service, serviceInstance);
    }
}
