package com.lixianch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * created by lixianch on 2023/1/31
 */
@Service("aService")
public class AServiceImpl implements IAService {
    @Resource
    private IBService bService;

    @Override
    public void sayHelloA() {
        System.out.println("Hello A");
    }
}
