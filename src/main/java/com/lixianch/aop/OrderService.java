package com.lixianch.aop;

import org.springframework.stereotype.Service;

/**
 * created by lixianch on 2023/2/10
 */
@Service
public class OrderService {
    public void queryOrder(String poNO){
        System.out.println("poNO=" + poNO);
    }
}
