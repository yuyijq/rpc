package com.yuyijq.rpc.client.netty;

import com.yuyijq.rpc.client.Callback;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 * User: zhaohuiyu
 * Date: 11/20/11
 * Time: 8:53 PM
 */
public class NettyAsyncClientHandlerWrapper extends SimpleChannelUpstreamHandler {
    private Callback callback;

    public NettyAsyncClientHandlerWrapper(Callback callback) {
        this.callback = callback;
    }

    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        this.callback.receive(e.getMessage());
    }

}
