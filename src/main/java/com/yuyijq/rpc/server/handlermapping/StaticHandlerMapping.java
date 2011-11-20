package com.yuyijq.rpc.server.handlermapping;

import com.yuyijq.rpc.model.Request;
import com.yuyijq.rpc.server.HandlerAdapter;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * User: zhaohuiyu
 * Date: 11/19/11
 * Time: 2:44 PM
 */
public class StaticHandlerMapping implements HandlerMapping {
    private Map<String, Object> serviceMap = new HashMap<String, Object>();

    public void register(String service, Object serviceInstance) {
        if (StringUtils.isEmpty(service)) {
            serviceMap.put(serviceInstance.getClass().getSimpleName(), serviceInstance);
        } else {
            serviceMap.put(service, serviceInstance);
        }
    }

    public HandlerAdapter getHandler(Request request) {
        Object service = serviceMap.get(request.getService());
        Method[] handles = service.getClass().getDeclaredMethods();
        for (Method handle : handles) {
            if (handle.getName().equals(request.getMethod())) {
                return new HandlerAdapter(service, handle);
            }
        }
        return null;
    }
}
