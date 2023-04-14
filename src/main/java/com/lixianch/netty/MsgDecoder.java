package com.lixianch.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * created by lixianch on 2023/4/10
 */
@Slf4j
public class MsgDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list)
        throws Exception {
        log.info("decode ...");
        try {
            byte[] body = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(body);
            list.add(ProtostuffUtil.deserialize(body, HotKeyMsg.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
