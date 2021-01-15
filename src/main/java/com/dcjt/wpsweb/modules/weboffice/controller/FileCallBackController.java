package com.dcjt.wpsweb.modules.weboffice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcjt.wpsweb.common.api.Response;
import com.dcjt.wpsweb.modules.weboffice.dto.HistoryDTO;
import com.dcjt.wpsweb.modules.weboffice.dto.WpsUserDTO;
import com.dcjt.wpsweb.modules.weboffice.factory.WpsFactory;
import com.dcjt.wpsweb.modules.weboffice.dto.WpsCopyDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述信息
 *
 * @author 杨祎
 * @date 2020/9/21
 */
@RestController
@RequestMapping("v1/3rd/file")
public class FileCallBackController {

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 查询用户信息
     * @param _w_userid 用户ID
     * @param _w_username 用户名称
     * @param _w_permission 权限 write/read
     * @param _w_avatar_url 头像路径
     * @return
     */
    @GetMapping("info")
    public ResponseEntity<Object> info(String _w_userid, String _w_username, String _w_permission, String _w_avatar_url) {
        WpsUserDTO user = new WpsUserDTO();
        user.set_w_userid(_w_userid);
        user.set_w_username(_w_username);
        user.set_w_permission(_w_permission);
        user.set_w_avatar_url(_w_avatar_url);
        return Response.success(WpsFactory.fileService.info(user));
    }

    /**
     * 回调通知
     */
    @PostMapping("onnotify")
    public ResponseEntity<Object> onNotify(@RequestBody JSONObject obj) {

        log.info("回调通知param:{}", JSON.toJSONString(obj));
        // 返回数据暂不处理
        return Response.success();
    }

    /**
     * 保存文件
     *
     * @param file      文件
     * @param _w_userid 用户ID
     * @return
     */
    @PostMapping("save")
    public ResponseEntity<Object> fileSave(@RequestBody MultipartFile file, @RequestParam String _w_userid) {
        Map<String, Object> map = WpsFactory.fileService.save(file, _w_userid);
        return Response.success(map);
    }

    /**
     * 新建文件
     * @param file 文件
     * @param _w_userid 用户ID
     * @param _w_username 用户名称
     * @param _w_permission 权限 write/read
     * @param _w_avatar_url 头像路径
     */
    @PostMapping("new")
    public ResponseEntity<Object> fileNew(@RequestBody MultipartFile file,
                                          @RequestParam String _w_userid,
                                          @RequestParam String _w_username,
                                          @RequestParam String _w_permission,
                                          @RequestParam String _w_avatar_url) {
        WpsUserDTO user = new WpsUserDTO();
        user.set_w_userid(_w_userid);
        user.set_w_username(_w_username);
        user.set_w_permission(_w_permission);
        user.set_w_avatar_url(_w_avatar_url);
        Map<String, Object> res = WpsFactory.fileService.fileNew(file, user);
        return Response.success(res);
    }


    /**
     * 复制文件
     */
    @PostMapping("copy")
    public ResponseEntity<Object> fileCopy(@RequestBody WpsCopyDTO wpsCopyDTO) {
        WpsUserDTO user = new WpsUserDTO();
        user.set_w_userid(wpsCopyDTO.get_w_userid());
        user.set_w_username(wpsCopyDTO.get_w_username());
        user.set_w_permission(wpsCopyDTO.get_w_permission());
        user.set_w_avatar_url(wpsCopyDTO.get_w_avatar_url());
        wpsCopyDTO.setPrefex(StringUtils.isEmpty(wpsCopyDTO.getPrefex())?".docx":wpsCopyDTO.getPrefex());
        Map<String, Object> res = WpsFactory.fileService.fileCopy(wpsCopyDTO.getFile_id(), user,wpsCopyDTO.getPrefex());
        return Response.success(res);
    }

    /**
     * 文件历史记录
     * @param historyDTO 请求参数对象
     * @return
     */
    @PostMapping("history")
    public ResponseEntity<Object> history(@RequestBody HistoryDTO historyDTO){
        return Response.success(new HashMap(1){
            {
                put("histories", Collections.emptyList());
            }
        });
    }
}
