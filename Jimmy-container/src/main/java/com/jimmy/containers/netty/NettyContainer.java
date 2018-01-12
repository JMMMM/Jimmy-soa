package com.jimmy.containers.netty;

import com.jimmy.containers.Container;
import com.jimmy.containers.netty.handler.NettyServerChannelInitializer;
import com.jimmy.containers.netty.handler.NettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NettyContainer implements Container {
    private Logger logger = LoggerFactory.getLogger(NettyContainer.class);
    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private ServerBootstrap bootstrap;

    private int port = 6666;

    @Override
    public void start() {
        logger.info("start NettyContainer port:{}...", port);
        new Thread(() -> {
            try {
                bootstrap = new ServerBootstrap();

                bootstrap.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new NettyServerChannelInitializer())
                        .option(ChannelOption.SO_BACKLOG, 1024)
                        .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                        .childOption(ChannelOption.SO_KEEPALIVE, true)
                        .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

                ChannelFuture channelFuture = bootstrap.bind(port).sync();
                channelFuture.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void stop() {
        System.out.println("stop NettyContainer ...");
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
