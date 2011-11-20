package com.yuyijq.rpc.server.netty;

import com.yuyijq.rpc.server.RpcDispatcher;
import com.yuyijq.rpc.server.transport.RpcTransport;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.DefaultChannelPipeline;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * User: zhaohuiyu
 * Date: 11/19/11
 * Time: 11:16 PM
 */
public class NettyRpcTransport implements RpcTransport {
    private RpcDispatcher dispatcher;

    public NettyRpcTransport(RpcDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void start(int port) {
        ServerBootstrap server = new ServerBootstrap(new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool()));

        server.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                DefaultChannelPipeline pipeline = new DefaultChannelPipeline();
                pipeline.addLast("encoder",new ObjectEncoder());
                pipeline.addLast("decoder",new ObjectDecoder());
                pipeline.addLast("handler", new NettyHandlerWrapper(dispatcher));
                return pipeline;
            }
        });

        server.bind(new InetSocketAddress(port));
    }
}
