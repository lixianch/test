package com.lixianch.netty;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

/**
 * created by lixianch on 2023/4/10
 */
@Sharable
@Slf4j
public class NettyClientHandler extends SimpleChannelInboundHandler<HotKeyMsg> {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){

        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HotKeyMsg msg) throws Exception {
        if(MessageType.PONG == msg.getMessageType()) {
            log.info("heart beat");
            return;
        }
        if(MessageType.RESPONSE_NEW_KEY == msg.getMessageType()){
            log.info("receive new key: " + msg);
            if(CollectionUtils.isEmpty(msg.getHotKeyModels())){
                return;
            }
            HotKeyModel model = msg.getHotKeyModels().get(0);
//            EventBusCenter.getInstance().post(new ReceiveNewKeyEvent(model));
        }

    }
}
