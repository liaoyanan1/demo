package com.example.oauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
/** @author lyn
 * TODO 权限访问api
 * @date 2020/7/29 9:45
*/
@RestController
public class Controller {

    //资源服务器认证用
    @GetMapping("/user/me")
    @ResponseBody
    public Principal user(Principal principal) {
        return principal;
    }

}
