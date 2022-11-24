package com.lzu.securingmicroservicesdemo.bean.temp;

import com.lzu.securingmicroservicesdemo.bean.AdminBean;
import com.lzu.securingmicroservicesdemo.injections.PermissionCheck;
import com.lzu.securingmicroservicesdemo.service.cache.RedisService;
import com.lzu.securingmicroservicesdemo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@RestController
public class AdminController {
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


    /**
     * 用户登录
     *
     * @return
     */
    @PostMapping("/login")
    public String get(@RequestParam String account, @RequestParam String passWord) {
        // 校验账号和密码
        AdminBean exitBean = adminService.getByAccount(account);
        if (!exitBean.getPassWord().equals(passWord)) {
            return "密码错误";
        }
        // 根据编号生成Token
        String token = TokenUtils.createJwtToken(exitBean.getCode());
        // 存储token到Redis
        redisService.set(exitBean.getCode(), token, 60 * 60 * 2);
        return TokenUtils.createJwtToken(exitBean.getCode());
    }

    /**
     * 检查是否有权限
     *
     * @return
     */
    @GetMapping("/test")
    @PermissionCheck(ADMIN_ALLOW = true,USER_ALLOW = false)
    public String test() {
        return "管理员允许访问";
    }


}
