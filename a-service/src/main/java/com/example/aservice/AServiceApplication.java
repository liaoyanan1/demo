package com.example.aservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
/** @author lyn 
 * TODO 资源服务器
 * @date 2020/7/30 15:36 
*/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AServiceApplication.class, args);
    }

}
