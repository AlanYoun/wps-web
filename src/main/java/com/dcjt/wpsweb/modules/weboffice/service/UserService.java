package com.dcjt.wpsweb.modules.weboffice.service;

import com.dcjt.wpsweb.modules.weboffice.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    /**
     * 测试用户
     * @return
     */
    public UserDTO testUser(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId("1");
        userDTO.setName("yy");
        userDTO.setPermission("write");
        userDTO.setAvatar_url("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1601288792253&di=551709de99a7ef867d112388eaad5e12&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201109%2F24%2F142635nexfilroenujn7er.jpg");
        return userDTO;
    }
}
