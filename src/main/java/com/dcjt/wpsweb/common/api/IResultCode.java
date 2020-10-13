package com.dcjt.wpsweb.common.api;

import java.io.Serializable;

/**
 * 描述信息
 *
 * @author 杨祎
 * @date 2020/9/21
 */
public interface IResultCode extends Serializable {
    String getMessage();

    int getCode();
}
