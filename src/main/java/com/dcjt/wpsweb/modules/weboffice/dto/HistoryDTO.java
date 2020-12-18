package com.dcjt.wpsweb.modules.weboffice.dto;


import lombok.Data;

/**
 * @author ioi
 */
@Data
public class HistoryDTO{

    /**
     * 文件id
     */
    private String id;

    /**
     * 记录偏移量
     */
    private Integer offset;

    /**
     * 记录偏移量
     */
    private Integer count;
}
