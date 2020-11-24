package com.dcjt.wpsweb.modules.weboffice.factory;

import com.dcjt.wpsweb.modules.weboffice.service.FileService;
import com.dcjt.wpsweb.modules.weboffice.service.UserService;
import org.springframework.stereotype.Component;

/**
 * 描述信息
 *
 * @author 杨祎
 * @date 2020/9/28
 */
@Component
public class WpsFactory {

    public static FileService fileService;
    public static UserService userService;

    public WpsFactory(FileService fileService, UserService userService) {
        WpsFactory.fileService = fileService;
        WpsFactory.userService = userService;
    }
}
