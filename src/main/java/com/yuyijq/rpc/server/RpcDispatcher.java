package com.yuyijq.rpc.server;

import com.yuyijq.rpc.model.Request;
import com.yuyijq.rpc.model.Response;
import com.yuyijq.rpc.server.handlermapping.HandlerMapping;

/**
 * User: zhaohuiyu
 * Date: 11/14/11
 * Time: 11:56 PM
 */
public class RpcDispatcher {
    private HandlerMapping handlerMapping;

    public RpcDispatcher(HandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    public void service(Request request, Response response) {
        HandlerAdapter handler = handlerMapping.getHandler(request);
        handler.handle(request, response);
    }

}
