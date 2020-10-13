package com.dcjt.wpsweb.common.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述信息
 * minio 文件实体类
 *
 * @author 杨祎
 * @date 2020/10/10
 */
@Data
public class MinioFile implements Serializable {

    /**
     * 文件名称
     */
    private String name;

    /**
     * 原始名称
     */
    private String orgName;

    /**
     * 存储桶名称
     */
    private String bucketName;

    /**
     * 文件大小
     */
    private Long size;
}
