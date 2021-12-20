package com.dcjt.wpsweb.modules.weboffice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 描述信息
 * 文件信息
 * @author 杨祎
 * @date 2021/9/17
 */
@Data
@ApiModel
public class RespFileVO implements Serializable {

    @ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("文件名称")
    private String name;

    @ApiModelProperty("下载地址")
    private String downloadUrl;
}
