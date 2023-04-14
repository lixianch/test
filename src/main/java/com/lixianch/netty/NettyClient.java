package com.lixianch.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.assertj.core.util.Lists;

/**
 * created by lixianch on 2023/4/10
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = initBootstrap();
        ChannelFuture channelFuture = bootstrap.connect("localhost", 8050).sync();
        Channel channel = channelFuture.channel();
        while (true) {
            TimeUnit.MILLISECONDS.sleep(5);
            HotKeyMsg msg = new HotKeyMsg();
            msg.setAppName("coo-rdp");
            msg.setMessageType(MessageType.PONG);
            List<HotKeyModel> models = Lists.newArrayList();
            HotKeyModel model = new HotKeyModel();
            model.setAppName("coo-rdp");
            model.setCount(100);
            model.setKey("key-1");
            models.add(model);
            msg.setHotKeyModels(models);
            channel.writeAndFlush(msg).sync();
        }
    }

    private static Bootstrap initBootstrap() {
        EventLoopGroup group = new NioEventLoopGroup(2);
        Bootstrap bootstrap = new Bootstrap();
        NettyClientHandler nettyClientHandler = new NettyClientHandler();
        bootstrap.group(group).channel(NioSocketChannel.class)
            .option(ChannelOption.SO_KEEPALIVE, true)
            .option(ChannelOption.TCP_NODELAY, true)
            .handler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    ByteBuf delimiter = Unpooled.copiedBuffer(Constant.DELIMITER.getBytes());
                    channel.pipeline().addLast(new DelimiterBasedFrameDecoder(Constant.MAX_LENGTH, delimiter))
                    .addLast(new MsgDecoder())
                    .addLast(new MsgEncoder())
                    .addLast(new IdleStateHandler(0, 0, 30))
                    .addLast(nettyClientHandler);
                }
            });
        return bootstrap;
    }

}
