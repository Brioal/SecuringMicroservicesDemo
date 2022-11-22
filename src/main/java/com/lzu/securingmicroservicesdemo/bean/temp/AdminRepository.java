package com.lzu.securingmicroservicesdemo.bean.temp;

import com.lzu.securingmicroservicesdemo.bean.AdminBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<AdminBean, Integer>, JpaSpecificationExecutor<AdminBean> {
    /**
     * 计算总数
     *
     * @return
     */
    long countAllBy();

    /**
     * 删除全部
     */
    @Transactional
    @Modifying
    void deleteAllBy();

    /**
     * 根据账号获取实体
     *
     * @param account
     * @return
     */
    AdminBean findFirstByAccountEquals(String account);

    /**
     * 根据账号判断是否存在
     *
     * @param account
     * @return
     */
    boolean existsAllByAccountEquals(String account);


    /**
     * 根据编号获取实体
     *
     * @param code
     * @return
     */
    AdminBean findFirstByCodeEquals(String code);

    /**
     * 根据编号判断是否存在
     *
     * @param code
     * @return
     */
    boolean existsAllByCodeEquals(String code);
}
