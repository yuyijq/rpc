package com.yuyijq.rpc.server;

import com.yuyijq.rpc.model.Request;
import com.yuyijq.rpc.model.Response;
import com.yuyijq.rpc.model.TransportData;

/**
 * User: zhaohuiyu
 * Date: 11/14/11
 * Time: 11:56 PM
 */
public class RpcDispatcher {
    private Router router;

    public RpcDispatcher(Router router) {
        this.router = router;
    }

    public TransportData service(Request request, Response response) {
        ServiceAdapter service = router.route(request);
        return service.handle(request, response);
    }

}
