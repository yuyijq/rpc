package com.yuyijq.rpc.client;

/**
 * User: zhaohuiyu
 * Date: 11/10/11
 * Time: 8:13 AM
 */
public class ChannelFactory {

    private RpcTransport transport;

    public ChannelFactory(RpcTransport transport) {
        this.transport = transport;
    }

    public Channel getChannel() {
        Channel channel = new Channel(this.transport);
        return channel;
    }
}
