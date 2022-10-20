package com.dcjt.wpsweb.modules.weboffice.service;

import com.dcjt.wpsweb.modules.weboffice.dto.UserDTO;
import com.dcjt.wpsweb.modules.weboffice.entity.User;
import com.dcjt.wpsweb.modules.weboffice.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    /**
     * 查询用户详情
     * @return
     */
    public User detail(Long id){
        return userRepository.getOne(id);
    }
}
