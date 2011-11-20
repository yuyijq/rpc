package com.yuyijq.rpc.client.netty;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import java.util.concurrent.BlockingQueue;

/**
 * User: zhaohuiyu
 * Date: 11/20/11
 * Time: 10:37 AM
 */
public class NettyClientHandlerWrapper extends SimpleChannelUpstreamHandler {

    private BlockingQueue result;

    public NettyClientHandlerWrapper(BlockingQueue result){
        this.result = result;
    }

    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        result.offer(e.getMessage());
    }
}
