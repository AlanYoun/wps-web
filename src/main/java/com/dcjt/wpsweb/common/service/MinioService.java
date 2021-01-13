package com.dcjt.wpsweb.common.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.alibaba.fastjson.JSON;
import com.dcjt.wpsweb.common.domain.MinioFile;
import com.dcjt.wpsweb.common.util.CommonUtil;
import io.minio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 描述信息
 *
 * @author 杨祎
 * @date 2020/10/9
 */
@Service
public class MinioService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private MinioClient minioClient;


    /**
     * 上传文件（minio文件名称随机生成）
     * @param bucketName 存储桶名称
     * @param file 文件
     * @return
     */
    public MinioFile upload(String bucketName, MultipartFile file){
        String fileName = CommonUtil.uuid() + "." + CommonUtil.fileSuffix(CommonUtil.fileSuffix(file.getOriginalFilename()));
        return upload(bucketName,file,fileName);
    }


    /**
     * 上传问价
     * @param bucketName 存储桶名称
     * @param file 文件
     * @param fileName minio文件名称
     * @return
     */
    public MinioFile upload(String bucketName, MultipartFile file, String fileName) {
        log.info("original file name: "+file.getOriginalFilename() + " file name: "+file.getName() + " file size:" + file.getSize() +"  fileName：" +fileName);
        MinioFile minioFile = new MinioFile();
        try{
            if(!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
                log.error("bucket name {} does not exist.",bucketName);
                return minioFile;
            }
            PutObjectArgs putObj = PutObjectArgs.builder().bucket(bucketName).object(fileName).stream(file.getInputStream(),file.getSize(), -1).build();
            minioClient.putObject(putObj);

            //封装文件对象
            minioFile.setBucketName(bucketName);
            minioFile.setName(fileName);
            minioFile.setSize(file.getSize());
            minioFile.setOrgName(file.getName());
        }catch (Exception e){
            log.error("file upload error:{}",e.getMessage(),e);
        }
        return minioFile;
    }


    /**
     * 上传文件（minio文件名称随机生成）
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @return
     */
    public MinioFile coypFile(String bucketName, String objectName,String newName,String prefex){
        newName = CommonUtil.uuid() + prefex;
        return coypWithUrl(bucketName,objectName,newName);
    }
    /**
     * 上传问价
     * @param bucketName 存储桶名称
     * @param objectName minio文件名称
     * @return
     */
    public MinioFile coypWithUrl(String bucketName, String objectName,String newName) {
        MinioFile minioFile = new MinioFile();
        try{
            if(!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
                log.error("bucket name {} does not exist.",bucketName);
                return minioFile;
            }
           InputStream inputStream= minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
            CopySource source=CopySource.builder().bucket(bucketName).object(objectName).build();
            CopyObjectArgs copyObjectArgs= CopyObjectArgs.builder().bucket(bucketName).object(newName).source(source).build();
            ObjectWriteResponse objectWriteResponse= minioClient.copyObject(copyObjectArgs);

            //封装文件对象
            minioFile.setBucketName(bucketName);
            minioFile.setName(newName);
            minioFile.setOrgName(objectName);
        }catch (Exception e){
            log.error("file upload error:{}",e.getMessage(),e);
        }
        return minioFile;
    }


    public static void main(String[] args) {
        System.out.println(CommonUtil.fileSuffix("11.exe"));
    }
}
