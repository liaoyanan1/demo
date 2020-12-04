package com.example.oauth2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

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
