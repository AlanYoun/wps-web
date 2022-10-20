package com.dcjt.wpsweb.modules.weboffice.repository;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dcjt.wpsweb.modules.weboffice.entity.User;
import com.dcjt.wpsweb.modules.weboffice.entity.WFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 描述信息
 * 文件持久层接口
 *
 * @author 杨祎
 * @date 2020/10/10
 */
@DS("zb")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
