package com.dcjt.wpsweb.wps.common;

import lombok.Data;

/**
 * 描述信息
 *
 * @author 杨祎
 * @date 2020/9/28
 */
@Data
public class Token {

    /**
     * 过期时间
     */
    private int expires_in;
    /**
     * token
     */
    private String token;

    /**
     * wps预览url
     */
    private String wpsUrl;
}
