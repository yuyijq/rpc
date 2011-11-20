package com.yuyijq.rpc.model;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * User: zhaohuiyu
 * Date: 11/10/11
 * Time: 10:18 PM
 */
public class Request implements Serializable {
    private String method;
    private Object[] parameters;
    private String service;

    public Request() {
    }

    public Request(String service, String method) {
        this.service = service;
        this.method = method;
    }

    public Request(Method method, Object[] parameters) {
        this.service = method.getDeclaringClass().getSimpleName();
        this.method = method.getName();
        this.parameters = parameters;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
