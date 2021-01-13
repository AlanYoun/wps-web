package com.dcjt.wpsweb.modules.weboffice.service;

import com.dcjt.wpsweb.common.domain.MinioFile;
import com.dcjt.wpsweb.common.props.MinioProperties;
import com.dcjt.wpsweb.common.service.MinioService;
import com.dcjt.wpsweb.common.util.CommonUtil;
import com.dcjt.wpsweb.modules.weboffice.common.Token;
import com.dcjt.wpsweb.modules.weboffice.config.Context;
import com.dcjt.wpsweb.modules.weboffice.dto.FileDTO;
import com.dcjt.wpsweb.modules.weboffice.dto.UserDTO;
import com.dcjt.wpsweb.modules.weboffice.dto.WpsUserDTO;
import com.dcjt.wpsweb.modules.weboffice.entity.WFile;
import com.dcjt.wpsweb.modules.weboffice.props.WpsProperties;
import com.dcjt.wpsweb.modules.weboffice.repository.FileRepository;
import com.dcjt.wpsweb.modules.weboffice.utils.FileUtil;
import com.dcjt.wpsweb.modules.weboffice.utils.WpsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述信息
 *
 * @author 杨祎
 * @date 2020/9/28
 */
@Service
public class FileService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private WpsProperties wpsProperties;

    @Resource
    private MinioProperties minioProperties;

    @Resource
    private MinioService minioService;

    @Resource
    private WpsUtil wpsUtil;

    @Resource
    private FileRepository fileRepository;

    /**
     * 查询文件信息
     *
     * @param user 用户
     * @return
     */
    public Map<String, Object> info(WpsUserDTO user) {
        //获取文件ID
        String fileId = Context.getFileId();
        //组装
        WFile wFile = fileRepository.findById(fileId).get();
        FileDTO file = new FileDTO();
        BeanUtils.copyProperties(wFile, file);

        return new HashMap<String, Object>() {
            {
                put("file", file);
                put("user", user.toUser());
            }
        };
    }

    /**
     * 获取查看URL
     *
     * @param fileId     文件ID
     * @param user       用户对象
     * @param checkToken 是否验证token
     * @return
     */
    public Token getViewUrl(String fileId, UserDTO user, boolean checkToken) {
        WFile wFile = fileRepository.findById(fileId).get();
        if (wFile != null) {
            Token t = new Token();
            String fileName = wFile.getName();
            String fileType = FileUtil.getFileTypeByName(fileName);

            Map<String, String> values = new HashMap<String, String>(user.getWpsMap()) {
                {
                    if (wpsProperties.isSignature()) {
                        put("_w_appid", wpsProperties.getAppid());
                    }
                    if (checkToken) {
                        put("_w_tokentype", "1");
                    }
                    put("_w_filepath", fileName);
                }
            };

            String wpsUrl = wpsUtil.getWpsUrl(values, fileType, wFile.getId());
            t.setToken(CommonUtil.uuid());
            t.setExpires_in(600);
            t.setWpsUrl(wpsUrl);

            return t;
        }
        return null;
    }

    /**
     * 保存文件
     *
     * @param mFile  文件对象
     * @param userId 用户ID
     * @return
     */
    public Map<String, Object> save(MultipartFile mFile, String userId) {
        String fileId = Context.getFileId();
        WFile wFile = fileRepository.getOne(fileId);

        //上传文件
        MinioFile minioFile = minioService.upload(minioProperties.getBucketName(), mFile, wFile.getName());
        //保存至数据库
        wFile.setSize(minioFile.getSize());
        wFile.setModifier(userId);
        wFile.setModify_time(System.currentTimeMillis());
        fileRepository.saveAndFlush(wFile);
        log.info("save file, id:{}", fileId);

        FileDTO fileDTO = new FileDTO();
        BeanUtils.copyProperties(wFile, fileDTO);
        //返回文件信息
        return new HashMap<String, Object>() {
            {
                put("file", fileDTO);
            }
        };
    }

    /**
     * 新建文件
     *
     * @param mFile 文件
     * @param user  用户ID
     * @return
     */
    public Map<String, Object> fileNew(MultipartFile mFile, WpsUserDTO user) {
        MinioFile minioFile = minioService.upload(minioProperties.getBucketName(), mFile);
        return fileSave(user, minioFile);
    }

    /**
     * 新建文件
     *
     * @param fileId 文件编号
     * @param user   用户ID
     * @return
     */
    public Map<String, Object> fileCopy(String fileId, WpsUserDTO user) {
        WFile oldFile = fileRepository.getOne(fileId);
        MinioFile minioFile = minioService.coypFile(minioProperties.getBucketName(), oldFile.getName());
        minioFile.setSize(oldFile.getSize());
        return fileSave(user, minioFile);
    }

    /**
     * 保存文件
     *
     * @param user
     * @param minioFile
     * @return
     */
    private Map<String, Object> fileSave(WpsUserDTO user, MinioFile minioFile) {

        WFile wFile = new WFile();
        wFile.setName(minioFile.getName());
        wFile.setSize(minioFile.getSize());
        wFile.setCreator(user.get_w_userid());
        wFile.setModifier(user.get_w_userid());
        wFile.setCreate_time(System.currentTimeMillis());
        wFile.setModify_time(System.currentTimeMillis());
        wFile.setDownload_url(minioProperties.getNetEndpoint() + "/" + minioFile.getBucketName() + "/" + minioFile.getName());
        fileRepository.save(wFile);
        log.info("new file,id:{}", wFile.getId());

        //封装返回信息
        return new HashMap<String, Object>() {
            {
                put("download_url", wFile.getDownload_url());
                put("file_id", wFile.getId());
                put("redirect_url", getViewUrl(wFile.getId(), user.toUser(), false).getWpsUrl());
                put("user_id", user.get_w_userid());
            }
        };
    }

}
