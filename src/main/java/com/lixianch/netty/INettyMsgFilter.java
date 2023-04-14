package com.lixianch.netty;

import io.netty.channel.ChannelHandlerContext;

/**
 * created by lixianch on 2023/4/10
 */
public interface INettyMsgFilter {

    boolean chain(HotKeyMsg msg, ChannelHandlerContext ctx);
}
