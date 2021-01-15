package com.dcjt.wpsweb.modules.weboffice.controller;

import com.dcjt.wpsweb.common.api.Response;
import com.dcjt.wpsweb.modules.weboffice.common.Token;
import com.dcjt.wpsweb.modules.weboffice.dto.UserDTO;
import com.dcjt.wpsweb.modules.weboffice.factory.WpsFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述信息
 *
 * @author 杨祎
 * @date 2020/9/28
 */
@RestController
public class WpsController {

    private Logger log = LoggerFactory.getLogger(getClass());
    /**
     * 通过fileId获取wpsUrl以及token
     * @param fileId 文件id
     * @return token（包含url）
     */
    @GetMapping("getViewUrl")
    public ResponseEntity<Object> getViewUrlDbPath(String fileId, UserDTO userDTO){
        log.info("getViewUrlDbPath：fileId={},userId={}",fileId,userDTO.getId());
        Token t = WpsFactory.fileService.getViewUrl(fileId, userDTO,false);
        if (t != null){
            return Response.success(t);
        }else {
            return Response.bad("文件不存在或其它异常！");
        }
    }
}
