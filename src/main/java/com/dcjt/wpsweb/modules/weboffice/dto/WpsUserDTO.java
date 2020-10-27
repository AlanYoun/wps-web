package com.dcjt.wpsweb.modules.weboffice.dto;

import lombok.Data;

/**
 * 用户信息
 *
 * @author 杨祎
 * @date 2020/9/28
 */
@Data
public class WpsUserDTO {

    /**
     * 用户id，长度小于32
     */
    private String _w_userid;
    /**
     * 用户名称
     */
    private String _w_username;
    /**
     * 用户操作权限，write：可编辑，read：预览
     */
    private String _w_permission;
    /**
     * 用户头像地址
     */
    private String _w_avatar_url;

    /**
     * 转换为响应用户对象
     * @return
     */
    public UserDTO toUser(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(_w_userid);
        userDTO.setName(_w_username);
        userDTO.setPermission(_w_permission);
        userDTO.setAvatar_url(_w_avatar_url);
        return userDTO;
    }
}
