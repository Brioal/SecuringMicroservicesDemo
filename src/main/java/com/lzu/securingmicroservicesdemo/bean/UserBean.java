package com.lzu.securingmicroservicesdemo.bean;


import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 用户对象
 */
@Entity
@Data
@ToString(callSuper = true)
public class UserBean {

    // 主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    // 编号
    private String code;

    // 账号
    private String account;

    // 密码
    private String passWord;

    // 拥有的权限
    private String authorities;

}
