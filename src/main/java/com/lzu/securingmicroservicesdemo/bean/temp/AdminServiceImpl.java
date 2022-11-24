package com.lzu.securingmicroservicesdemo.bean.temp;

import com.lzu.securingmicroservicesdemo.bean.AdminBean;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
