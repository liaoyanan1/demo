package com.example.aservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xmlpull.v1.XmlPullParserException;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.io.IOException;

/** @author lyn
 * TODO swagger2基本信息配置
 * @date 2020/7/28 14:33
*/
@Configuration
@EnableSwagger2
public class Swagger2Configuration1 {
    private static final String SWAGGER_SCAN_BASE_PACKAGE = "com.example.aservice.controller";
    private static final String VERSION = "v.1";

    @Bean
    public Docket api() throws IOException, XmlPullParserException {
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder().title("a-service Api 文档")
                .description(" - -")
                .version(VERSION)
                .contact(new Contact("lyndon", "lyndon.fun", "liaoyn@zcstc.com"));
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfoBuilder.build());
    }
}
