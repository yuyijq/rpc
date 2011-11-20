package com.yuyijq.rpc.server.netty;

import com.yuyijq.rpc.model.Request;
import com.yuyijq.rpc.model.Response;
import com.yuyijq.rpc.server.RpcDispatcher;
import org.jboss.netty.channel.*;

/**
 * User: zhaohuiyu
 * Date: 11/20/11
 * Time: 12:23 AM
 */
public class NettyHandlerWrapper extends SimpleChannelUpstreamHandler {
    private RpcDispatcher dispatcher;

    public NettyHandlerWrapper(RpcDispatcher dispatcher){
        this.dispatcher = dispatcher;
    }
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        Request request = (Request) e.getMessage();
        Response response = new NettyResponse(e.getChannel());
        dispatcher.service(request, response);
    }

    @Override
    public void exceptionCaught(
            ChannelHandlerContext ctx, ExceptionEvent e) {
        e.getChannel().close();
    }
}
