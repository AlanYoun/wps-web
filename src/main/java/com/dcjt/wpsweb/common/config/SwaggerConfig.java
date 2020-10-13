package com.dcjt.wpsweb.common.config;

import com.dcjt.wpsweb.common.props.MinioProperties;
import com.dcjt.wpsweb.wps.props.WpsProperties;
import io.minio.MinioClient;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.Resource;

/**
 * 描述信息
 *
 * @author 杨祎
 * @date 2020/9/21
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(new ApiInfoBuilder()
                .description("WPS服务")
                .title("wps-web")
                .version("1.0.0")
                .build()
        )
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                ;
    }

    @Bean
    @SneakyThrows
    public static MinioClient template(MinioProperties properties) {
        MinioClient minioClient = MinioClient.builder().endpoint(properties.getEndpoint()).credentials(properties.getAccessKey(),properties.getSecretKey()).build();
        return minioClient;
    }

}
