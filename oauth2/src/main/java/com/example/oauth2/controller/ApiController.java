package com.example.oauth2.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/** @author lyn
 * TODO 开放访问api
 * @date 2020/7/29 9:45
*/
@RequestMapping("/api")
@RestController
public class ApiController {

    @RequestMapping("/anonymous")
    public String anonymous(){
        return "hello!";
    }

}
