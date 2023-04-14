package com.lixianch.netty;

import java.util.List;
import lombok.Data;

/**
 * created by lixianch on 2023/4/10
 */
@Data
public class HotKeyMsg {
    private String appName;
    private String messageType;
    private List<HotKeyModel> hotKeyModels;

}
