package com.yuyijq.rpc.client;

import com.yuyijq.rpc.handler.Handler;

import java.util.ArrayList;
import java.util.List;

/**
 * User: zhaohuiyu
 * Date: 11/10/11
 * Time: 8:13 AM
 */
public class ChannelFactory {

    private List<Handler> handlers;

    public ChannelFactory(Handler... handlers) {
        this.handlers = new ArrayList<Handler>();
        Handler preHandler = null;
        Handler currentHandler;
        for (Handler handler : handlers) {
            currentHandler = handler;
            if (preHandler != null) {
                preHandler.setNextHandler(currentHandler);
            }
            this.handlers.add(handler);
            preHandler = currentHandler;
        }
    }

    public Channel getChannel() {
        Channel channel = new Channel(this.handlers);
        return channel;
    }
}
