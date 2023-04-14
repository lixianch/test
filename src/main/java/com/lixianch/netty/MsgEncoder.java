package com.lixianch.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * created by lixianch on 2023/4/10
 */
@Slf4j
public class MsgEncoder extends MessageToByteEncoder {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object obj, ByteBuf byteBuf) throws Exception {
        log.info("encode ...");
        if(obj instanceof HotKeyMsg) {
            byte[] data = ProtostuffUtil.serialize(obj);
            byte[] delimiter = Constant.DELIMITER.getBytes();
            byte[] total = new byte[data.length + delimiter.length];
            System.arraycopy(data, 0, total, 0, data.length);
            System.arraycopy(delimiter, 0, total, data.length, delimiter.length);
            byteBuf.writeBytes(total);
        }

    }
}
