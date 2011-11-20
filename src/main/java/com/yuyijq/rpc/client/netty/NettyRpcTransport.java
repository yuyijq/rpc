package com.yuyijq.rpc.client.netty;

import com.yuyijq.rpc.client.RpcTransport;
import com.yuyijq.rpc.model.Request;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

import java.net.SocketAddress;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * User: zhaohuiyu
 * Date: 11/20/11
 * Time: 5:00 PM
 */
public class NettyRpcTransport implements RpcTransport {

    private Channel channel;
    private BlockingQueue resultQueue = new LinkedBlockingQueue(1);

    public void connect(SocketAddress address) {
        ClientBootstrap bootstrap = new ClientBootstrap(
                new NioClientSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                DefaultChannelPipeline pipeline = new DefaultChannelPipeline();
                pipeline.addLast("encoder", new ObjectEncoder());
                pipeline.addLast("decoder", new ObjectDecoder());
                pipeline.addLast("receive", new NettyClientHandlerWrapper(resultQueue));
                return pipeline;
            }
        });
        ChannelFuture future = bootstrap.connect(address);
        channel = future.awaitUninterruptibly().getChannel();
        if (!future.isSuccess()) {
            future.getCause().printStackTrace();
            bootstrap.releaseExternalResources();
            return;
        }
    }

    public void send(Request request) {
        if (this.channel == null)
            return;
        this.channel.write(request);
    }

    public Object receive() {
        try {
            return resultQueue.take();
        } catch (InterruptedException e) {
            return null;
        }
    }
}
