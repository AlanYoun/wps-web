package com.dcjt.wpsweb.modules.weboffice.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息返回对象
 *
 * @author yy
 */
@Data
public class UserVO implements Serializable {

    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 头像
     */
    private String avatar_url;
}
