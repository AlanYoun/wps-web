package com.dcjt.wpsweb.common.util;

import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * 描述信息
 *
 * @author 杨祎
 * @date 2020/10/10
 */
public class CommonUtil {

    /**
     * 获取文件后缀
     * @param fileName 文件名称
     * @return
     */
    public static String fileSuffix(String fileName) {
        if(StringUtils.isEmpty(fileName)) {
            return fileName;
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 获取UUID
     * @return
     */
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
