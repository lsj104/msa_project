package com.sparta.msa_exam.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ProductServerPortInterceptor implements HandlerInterceptor {

    @Value("${server.port}")
    private String serverPort;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        response.addHeader("Server-Port", serverPort);
        System.out.println("Server-Port 헤더가 추가되었습니다: " + serverPort);
    }

}