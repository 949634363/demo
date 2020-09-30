package com.hx.demo.repo;

import com.hx.demo.bean.model.AppUserMobile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * @author user
 */
public interface AppUserMobileRepository extends JpaRepository<AppUserMobile, Long>, JpaSpecificationExecutor<AppUserMobile> {

    /**
     * 根据修改时间分页查询
     * @param modifyTime
     * @param pageable
     * @return
     */
    @Query(value = "SELECT user.mobile, user.huanxinid from AppUserMobile user WHERE user.modifytime >= :code AND user.flag = 0 AND user.pushSwitch = 'on'")
    Page<AppUserMobile> findUsersByModifyTime(@Param("code")Date modifyTime, @Param("pageable")Pageable pageable);
}