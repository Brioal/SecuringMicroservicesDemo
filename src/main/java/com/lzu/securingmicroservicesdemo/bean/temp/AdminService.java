package com.lzu.securingmicroservicesdemo.bean.temp;

import com.lzu.securingmicroservicesdemo.bean.AdminBean;

import javax.transaction.Transactional;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

public interface AdminService {
    /**
     * 根据账号获取实体
     *
     * @param account
     * @return
     */
    AdminBean getByAccount(String account);

    /**
     * 根据账号判断是否存在
     *
     * @param account
     * @return
     */
    boolean checkAccount(String account);


    /**
     * 根据编号获取实体
     *
     * @param code
     * @return
     */
    AdminBean getByCode(String code);

    /**
     * 根据编号判断是否存在
     *
     * @param code
     * @return
     */
    boolean checkCode(String code);
}
