package com.dcjt.wpsweb.wps.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 描述信息
 *
 * @author 杨祎
 * @date 2020/9/21
 */
@Data
@Component
@ConfigurationProperties(prefix = "wps")
public class WpsProperties {

    private String domain;

    private String appid;

    private String appsecret;

    private String downloadHost;

    private String localDir;

}
