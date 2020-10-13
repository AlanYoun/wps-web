package com.dcjt.wpsweb.wps.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 文件详情对象
 *
 * @author 杨祎
 * @date 2020/9/28
 */

@Data
public class FileDTO implements Serializable {

    /**
     * 文件id,字符串长度小于40
     */
    private String id;
    /**
     * 文件名
     */
    private String name;
    /**
     * 当前版本号，位数小于11
     */
    private Integer version;
    /**
     * 文件大小，单位为kb
     */
    private Long size;
    /**
     * 创建者id，字符串长度小于40
     */
    private String creator;
    /**
     * 修改者id，字符串长度小于40
     */
    private String modifier;
    /**
     * 创建时间，时间戳，单位为秒
     */
    private Long create_time;
    /**
     * 修改时间，时间戳，单位为秒
     */
    private Long modify_time;
    /**
     * 文档下载地址
     */
    private String download_url;

}


