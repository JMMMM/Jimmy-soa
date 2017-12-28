package com.jimmy.containers.netty;

import com.jimmy.containers.Container;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;


public class NettyServerContainer implements Container {

    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();

    private ServerBootstrap bootstrap;

    @Override
    public void start() {
        new Thread(() -> {
            bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            IdleStateHandler handler = new IdleStateHandler(15, 0, 0);
                            ch.pipeline().addLast(handler);
                        }
                    });
        }).start();
    }

    @Override
    public void stop() {

    }
}