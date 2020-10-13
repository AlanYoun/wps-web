package com.dcjt.wpsweb;

import com.alibaba.fastjson.JSON;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

/**
 * 描述信息
 *
 * @author 杨祎
 * @date 2020/10/9
 */
public class MinioTest {

    @SneakyThrows
    public static void main(String[] args) {
        MinioClient minioClient = MinioClient.builder().endpoint("http://192.168.3.123:9001").credentials("AKIAIOSFODNN7EXAMPLE","wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY").build();
        File file = new File("C:\\Users\\xs\\Desktop\\test1.docx");
        FileInputStream in = new FileInputStream(file);
        PutObjectArgs putObj = PutObjectArgs.builder().bucket("wps").object(file.getName()).stream(in,in.available(), -1).build();
        ObjectWriteResponse response = minioClient.putObject(putObj);
        System.out.println(JSON.toJSONString(response));
    }
}
