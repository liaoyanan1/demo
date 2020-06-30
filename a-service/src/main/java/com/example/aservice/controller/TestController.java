package com.example.aservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/getA")
public class TestController {

    @GetMapping("/A")
    public String A(){
        return "b";
    }
}
