package com.lixianch.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * created by lixianch on 2023/2/10
 */
@RestControllerAdvice
public class BaseController {
    @ExceptionHandler(Exception.class)
    public Map<String, Object> handleException(Exception e, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> result = new HashMap<>();
        result.put("message", e.getMessage());
        return result;
    }
}
