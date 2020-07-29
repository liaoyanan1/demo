package com.example.aservice.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    @HystrixCommand(fallbackMethod = "error")
    // @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/A")
    public String A(Principal principal){
        return A+principal;
    }

    public String error(Principal principal){
        return "1";
    }
}
