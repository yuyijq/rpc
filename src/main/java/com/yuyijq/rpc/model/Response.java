package com.yuyijq.rpc.model;

import java.nio.channels.SelectableChannel;

/**
 * User: zhaohuiyu
 * Date: 11/15/11
 * Time: 10:23 PM
 */
public class Response {
    private SelectableChannel channel;

    public Response(SelectableChannel channel) {
        this.channel = channel;
    }

    public void write(Object result) {
    }

    public void flush() {

    }
}
