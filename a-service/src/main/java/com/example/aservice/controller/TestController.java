package com.example.aservice.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/** @author lyn
 * TODO 需认证接口
 * @date 2020/7/28 14:33
*/


@RestController
@RefreshScope
public class TestController {



    @Value("${A}")
    String A;
    @HystrixCommand(fallbackMethod = "error",
            commandProperties = { //参数信息看配置图
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "100"),
                    @HystrixProperty(name = "maxQueueSize", value = "10"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2")
            },
            groupKey = "group1",
            threadPoolKey = "threadPool1"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/A")
    public String A(Principal principal){
        return A+principal;
    }

    public String error(Principal principal){
        return "1";
    }
}
