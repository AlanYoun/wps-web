package com.dcjt.wpsweb.modules.weboffice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcjt.wpsweb.common.api.R;
import com.dcjt.wpsweb.common.api.Response;
import com.dcjt.wpsweb.modules.weboffice.entity.User;
import com.dcjt.wpsweb.modules.weboffice.factory.WpsFactory;
import com.dcjt.wpsweb.modules.weboffice.service.UserService;
import com.dcjt.wpsweb.modules.weboffice.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author yy
 * 用户相关回调接口
 */
@RestController
@RequestMapping("v1/3rd/user")
public class UserCallBackController {

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 获取用户信息
     */
    @PostMapping("info")
    public ResponseEntity<Object> userInfo(@RequestBody JSONObject reqObj) {
        log.info("获取用户信息param:{}", JSON.toJSON(reqObj));
        JSONArray ids = reqObj.getJSONArray("ids");
        List<UserVO> userList = new LinkedList();

        ids.stream().forEach(id->{
            User user = WpsFactory.userService.detail(Long.valueOf(String.valueOf(id)));
            if(Objects.nonNull(user)) {
                UserVO uv = new UserVO();
                uv.setId(String.valueOf(user.getId()));
                uv.setName(user.getName());
                uv.setAvatar_url(user.getAvatar());
                userList.add(uv);
            }

        });
        return Response.success(new HashMap<String, Object>(1){{put("users", userList);}});
    }

}
