package com.yuyijq.rpc.server.transport;

import com.yuyijq.rpc.model.HttpRequest;
import com.yuyijq.rpc.model.Request;
import com.yuyijq.rpc.server.RpcDispatcher;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;

import static java.nio.channels.SelectionKey.OP_ACCEPT;
import static java.nio.channels.SelectionKey.OP_READ;

/**
 * User: zhaohuiyu
 * Date: 11/19/11
 * Time: 2:55 PM
 */
public class SimpleRpcTransport implements RpcTransport {
    private Selector selector;
    private RpcDispatcher dispatcher;

    public SimpleRpcTransport(RpcDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void start(int port) {
        try {
            selector = Selector.open();
            doStart(port);
        } catch (IOException e) {

        }

    }

    private void doStart(int port) throws IOException {
        listen(port);
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

    private void listen(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        SocketAddress address = new InetSocketAddress(port);
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
            ByteBuffer buffer = ByteBuffer.allocateDirect(10);
            socketChannel.read(buffer);
            //Request request = getRequest(socketChannel);
            //socketChannel.register(selector, OP_WRITE);
            //key.attach(request);
        } else if (key.isWritable()) {
            Request request = (Request) key.attachment();
        }
    }

    private Request getRequest(SocketChannel socketChannel) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        new BufferedWriter(new StringWriter());
        try {
            int read;
            while((read = socketChannel.read(buffer)) != -1){
                buffer.flip();
                byte[] readBytes = new byte[read];
                buffer.get(readBytes, 0,read);
            }
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
