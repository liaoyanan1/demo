package com.example.oauth2.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class ApiController {

    @RequestMapping("/anonymous")
    public String anonymous(){
        return "hello!";
    }

}
