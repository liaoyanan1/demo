package com.example.aservice.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/getA")
public class TestController {


    @Value("${A}")
    String A;

    @GetMapping("/A")
    public String A(){
        return A;
    }
}
