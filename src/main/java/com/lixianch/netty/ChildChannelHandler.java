package com.lixianch.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

/**
 * created by lixianch on 2023/4/10
 */
public class ChildChannelHandler extends ChannelInitializer<Channel> {
    private IClientChangeListener clientChangeListener;
    @Override
    protected void initChannel(Channel channel) throws Exception {
        NodesServerHandler serverHandler = new NodesServerHandler();
        serverHandler.setClientChangeListener(clientChangeListener);
        ByteBuf delimiter = Unpooled.copiedBuffer(Constant.DELIMITER.getBytes());
        channel.pipeline().addLast(new DelimiterBasedFrameDecoder(Constant.MAX_LENGTH, delimiter))
            .addLast(new MsgEncoder())
        .addLast(new MsgDecoder())
        .addLast(new NodesServerHandler());
    }
}
