package com.lixianch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * created by lixianch on 2023/1/31
 */
@Service("bService")
public class BServiceImpl implements IBService {
    @Resource
    private IAService aService;
    @Override
    public void sayHelloB() {
        System.out.println("Hello B");
    }
}
