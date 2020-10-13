package com.dcjt.wpsweb.wps.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 描述信息
 *
 * @author 杨祎
 * @date 2020/10/10
 */
@Data
@Entity
@Table(name = "w_file")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class WFile {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 版本
     */
    private Integer version = 1;

    /**
     * 长度
     */
    private Long size;

    /**
     * 创建用户
     */
    private String creator;

    /**
     * 修改用户
     */
    private String modifier;

    /**
     * 创建时间
     */
    private Long create_time;

    /**
     * 修改时间
     */
    private Long modify_time;

    /**
     * 下载地址
     */
    private String download_url;

}
