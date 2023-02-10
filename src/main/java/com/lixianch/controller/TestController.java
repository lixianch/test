package com.lixianch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * created by lixianch on 2023/2/10
 */
@RestController
public class TestController {
    @GetMapping("/list")
    public Map<String, Object> list(){
        throw new RuntimeException("test exception catch");
    }
}
