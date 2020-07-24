package com.example.aservice.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {



    @Value("${A}")
    String A;

   // @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/A")
    public String A(){
        return A;
    }
}
