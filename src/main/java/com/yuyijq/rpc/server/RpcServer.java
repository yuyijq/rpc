package com.yuyijq.rpc.server;

import com.yuyijq.rpc.model.HttpRequest;
import com.yuyijq.rpc.model.Request;
import com.yuyijq.rpc.model.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;

import static java.nio.channels.SelectionKey.*;

/**
 * User: zhaohuiyu
 * Date: 11/14/11
 * Time: 10:08 PM
 */
public class RpcServer {
    private Selector selector;
    private RpcDispatcher dispatcher;

    public RpcServer(RpcDispatcher dispatcher) throws IOException {
        this.dispatcher = dispatcher;
        selector = Selector.open();
    }

    public void start(String host, int port) throws IOException {
        listen(host, port);
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                handle(selector, key);
            }
        }
    }

    private void listen(String host, int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        SocketAddress address = new InetSocketAddress(host, port);
        serverSocketChannel.socket().bind(address, 15);
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, OP_ACCEPT);
    }

    private void handle(Selector selector, SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel connection = serverSocketChannel.accept();
            connection.configureBlocking(false);
            connection.register(selector, OP_READ);
        } else if (key.isReadable()) {
            SelectableChannel channel = key.channel();
            SocketChannel socketChannel = (SocketChannel) channel;
            Request request = getRequest(socketChannel);
            socketChannel.register(selector, OP_WRITE);
            key.attach(request);
        } else if (key.isWritable()) {
            Request request = (Request) key.attachment();
            Response response = new Response(key.channel());
            dispatcher.service(request, response);
        }
    }

    private Request getRequest(SocketChannel socketChannel) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            socketChannel.read(buffer);
            String result = decode((ByteBuffer) buffer.flip());
            BufferedReader reader = new BufferedReader(new StringReader(result));
            String firstLine = reader.readLine();
            String path = firstLine.substring(firstLine.indexOf(' '), firstLine.lastIndexOf(' '));
            String service = path.substring(path.indexOf('/') + 1, path.lastIndexOf('/'));
            String method = "";
            String queryString = "";
            if (path.indexOf('?') != -1) {
                method = path.substring(path.lastIndexOf('/') + 1, path.lastIndexOf('?'));
                queryString = path.substring(path.lastIndexOf('?') + 1);
            } else {
                method = path.substring(path.lastIndexOf('/') + 1);
            }
            return new HttpRequest(service, method, queryString);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public String decode(ByteBuffer buffer) {
        try {
            Charset charset = Charset.defaultCharset();
            CharsetDecoder decoder = charset.newDecoder();
            CharBuffer charBuffer = decoder.decode(buffer);
            return charBuffer.toString();
        } catch (Exception ex) {
            return "";
        }
    }
}
