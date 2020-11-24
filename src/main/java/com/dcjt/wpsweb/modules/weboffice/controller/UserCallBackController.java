package com.dcjt.wpsweb.modules.weboffice.controller;

import com.dcjt.wpsweb.modules.weboffice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yy
 * 用户相关回调接口
 */
@RestController
@RequestMapping("v1/3rd/user")
public class UserCallBackController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private UserService userService;

    /**
     * 获取用户信息
     */
//    @PostMapping("info")
//    public R<Object> userInfo(@RequestBody JSONObject reqObj) {
//        log.info("获取用户信息param:{}", JSON.toJSON(reqObj));
//        Map<String, Object> map = userService.userInfo(reqObj);
//        return R.data(map);
//    }

}
