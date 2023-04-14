package com.lixianch.netty;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * created by lixianch on 2023/4/10
 */
@Data
@Slf4j
public class NodesServerHandler extends SimpleChannelInboundHandler<HotKeyMsg> {

    private List<INettyMsgFilter> messageFilters = new ArrayList<>();
    private IClientChangeListener clientChangeListener;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HotKeyMsg msg) throws Exception {
        log.info("channelRead0 ..." + JSONObject.toJSONString(msg));
        if(msg == null){
            return;
        }

        for (INettyMsgFilter messageFilter : messageFilters) {
            boolean doNext = messageFilter.chain(msg, ctx);
            if(!doNext){
                return;
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        log.error("something is error, " + cause.getMessage());
        super.exceptionCaught(ctx, cause);
    }
}
