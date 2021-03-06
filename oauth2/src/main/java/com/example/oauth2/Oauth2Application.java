package com.example.oauth2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/** @author lyn 
 * TODO oauth2单点登录权限认证
 * @date 2020/7/29 9:46 
*/
@EnableResourceServer
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.example.oauth2.mapper")
public class Oauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2Application.class, args);
    }

}
