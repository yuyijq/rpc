package com.yuyijq.rpc.server.handlermapping;

import com.yuyijq.rpc.model.Request;
import com.yuyijq.rpc.server.HandlerAdapter;

/**
 * User: zhaohuiyu
 * Date: 11/15/11
 * Time: 10:33 PM
 */
public interface HandlerMapping {
    HandlerAdapter getHandler(Request request);
}
