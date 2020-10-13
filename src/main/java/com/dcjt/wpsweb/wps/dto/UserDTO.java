package com.dcjt.wpsweb.wps.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息
 *
 * @author 杨祎
 * @date 2020/9/28
 */
@Data
public class UserDTO {

    /**
     * 用户id，长度小于32
     */
    private String id;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户操作权限，write：可编辑，read：预览
     */
    private String permission;
    /**
     * 用户头像地址
     */
    private String avatar_url;

    /**
     * 获取wps参数map集合
     * @return
     */
    public Map<String,String> getWpsMap(){
        return new HashMap<String, String>(){
            {
                put("_w_userid", id);
                put("_w_username",name);
                put("_w_permission",permission);
                put("_w_avatar_url",avatar_url);
            }
        };
    }
}
