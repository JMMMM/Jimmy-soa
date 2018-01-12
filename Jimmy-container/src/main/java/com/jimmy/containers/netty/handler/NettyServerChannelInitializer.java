package com.jimmy.containers.netty.handler;

import com.google.protobuf.Message;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;


public class NettyServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        IdleStateHandler idleStateHandler = new IdleStateHandler(15, 0, 0);
        ProtobufEncoderHandler protobufEncoderHandler = new ProtobufEncoderHandler();
        channelPipeline.addLast(idleStateHandler)
                .addLast(new ProtobufVarint32FrameDecoder())
                .addLast(protobufEncoderHandler)
                .addLast(new ProtobufEncoder());
    }

}
