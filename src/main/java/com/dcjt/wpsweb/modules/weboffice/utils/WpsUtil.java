package com.dcjt.wpsweb.modules.weboffice.utils;

import com.dcjt.wpsweb.modules.weboffice.props.WpsProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class WpsUtil {

    @Resource
    private WpsProperties wpsProperties;


    /**
     * 获取Wps访问URL
     *
     * @param values
     * @param fileType
     * @param fileId
     * @return
     */
    public String getWpsUrl(Map<String, String> values, String fileType, String fileId) {
        String keyValueStr = SignatureUtil.getKeyValueStr(values);

        String fileTypeCode = FileUtil.getFileTypeCode(fileType);

        StringBuilder url = new StringBuilder(wpsProperties.getDomain());
        url.append(fileTypeCode).append("/").append(fileId).append("?").append(keyValueStr);

        //需要签名才添加签名参数
        if (wpsProperties.isSignature()) {
            url.append("_w_signature=").append(SignatureUtil.getSignature(values, wpsProperties.getAppsecret()));
        }
        return url.toString();
    }

}
