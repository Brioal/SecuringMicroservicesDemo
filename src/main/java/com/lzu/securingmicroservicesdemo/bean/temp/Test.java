package com.lzu.securingmicroservicesdemo.bean.temp;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getHeader("token");
        if ("I am Admin".equals(accessToken)) {
            return true;
        }
        response.getWriter().append("权限校验失败,不允许访问");
        return false;
    }
}
