package com.yuyijq.rpc.server.netty;

import com.yuyijq.rpc.model.Response;
import org.jboss.netty.channel.Channel;

/**
 * User: zhaohuiyu
 * Date: 11/20/11
 * Time: 3:40 PM
 */
public class NettyResponse extends Response {
    private Channel channel;

    public NettyResponse(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void write(Object message) {
        this.channel.write(message);
    }
}
