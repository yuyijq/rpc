package com.yuyijq.rpc.model;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest extends Request {
    private String queryString;

    public HttpRequest(String service, String method, String queryString) {
        super(service, method);
        this.queryString = queryString;
    }

    public Map<String, String> getQueryString() {
        Map<String, String> result = new HashMap<String, String>();
        String[] nameValuePair = StringUtils.split(this.queryString, "&");
        for (String nameValue : nameValuePair) {
            String[] pair = StringUtils.split(nameValue, "=");
            result.put(pair[0], pair[1]);
        }
        return result;
    }
}
