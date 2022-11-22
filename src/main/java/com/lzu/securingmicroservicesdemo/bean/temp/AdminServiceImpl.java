package com.lzu.securingmicroservicesdemo.bean.temp;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.lzu.securingmicroservicesdemo.bean.AdminBean;
import com.lzu.securingmicroservicesdemo.bean.temp.AdminRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;

    @Autowired
    public void setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    /**
     * 根据账号获取实体
     *
     * @param account
     * @return
     */
    @Override
    public AdminBean getByAccount(String account) {
        return adminRepository.findFirstByAccountEquals(account);
    }

    /**
     * 根据账号检查
     *
     * @param account
     * @return
     */
    @Override
    public boolean checkAccount(String account) {
        return adminRepository.existsAllByAccountEquals(account);
    }

    /**
     * 根据编号获取实体
     *
     * @param code
     * @return
     */
    @Override
    public AdminBean getByCode(String code) {
        return adminRepository.findFirstByCodeEquals(code);
    }

    /**
     * 根据编号检查
     *
     * @param code
     * @return
     */
    @Override
    public boolean checkCode(String code) {
        return adminRepository.existsAllByCodeEquals(code);
    }
}
