package com.lixianch.aop;

import com.lixianch.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * created by lixianch on 2023/2/10
 */
public class OrderServiceTest extends BaseTest {
    @Resource
    OrderService orderService;

    @Test
    public void testQueryOrder(){
        orderService.queryOrder("PO23212121123");
    }
}
