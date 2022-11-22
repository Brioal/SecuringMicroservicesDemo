package com.lzu.securingmicroservicesdemo.config;


import com.lzu.securingmicroservicesdemo.bean.AdminBean;
import com.lzu.securingmicroservicesdemo.bean.temp.AdminService;
import com.lzu.securingmicroservicesdemo.injections.PermissionCheck;
import com.lzu.securingmicroservicesdemo.service.cache.RedisService;
import com.lzu.securingmicroservicesdemo.utils.TokenUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;


public class AuthenticationInterceptor implements HandlerInterceptor {

    private AdminService adminService;
    private RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断接口是否需要登录
        PermissionCheck methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(PermissionCheck.class);
        if (methodAnnotation == null) {
            return true;
        }
        String accessToken = request.getHeader("token");
        if (null == accessToken) {
            // 没有传递Token
            // todo
            return false;
        }
        // 允许Admin角色访问
        if (methodAnnotation.ADMIN_ALLOW()) {
            Claims claims = TokenUtils.parseJWT(accessToken);
            Date expireDate = claims.getExpiration();
            if (expireDate.before(new Date(System.currentTimeMillis()))) {
                // token过期
                // todo
                return false;
            }
            // 获取AdminCode
            String code = claims.getId();
            // 从Redis读取,判断token是否相等,不相等则踢下线
            String existToken = (String) redisService.get(code);
            if (accessToken.equals(existToken)) {
                // todo 踢下线
                return false;
            }
            // 检查Admin是否存在
            AdminBean adminBean = adminService.getByCode(code);
            // 管理员拥有的权限 eg:news_edit;news_add;news_delete;
            String authorities = adminBean.getAuthorities();
            // 要求的权限 eg:news_delete
            String requiredAuthorities = methodAnnotation.requireAuthorities();
            // todo 分割+检查
            return true;
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().append("权限校验失败,不允许访问");
        return false;
    }
}
