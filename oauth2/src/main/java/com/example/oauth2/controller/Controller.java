package com.example.oauth2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
/** @author lyn
 * TODO 权限访问api
 * @date 2020/7/29 9:45
*/
@org.springframework.stereotype.Controller
public class Controller {

    //资源服务器认证用
    @GetMapping("/user/me")
    @ResponseBody
    public Principal user(Principal principal) {
        return principal;
    }

    //退出 删除token

    @Autowired
    private ConsumerTokenServices consumerTokenServices;
    @Autowired
    RedisTemplate redisTemplate;
    @GetMapping("/tokenLogout")
    public String deleteToken(HttpServletResponse response){
        SecurityContext context = SecurityContextHolder.getContext();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) context.getAuthentication().getDetails();
        String tokenValue = details.getTokenValue();
        User principal = (User) context.getAuthentication().getPrincipal();
        return consumerTokenServices.revokeToken(tokenValue)?"redirect:/logout":"退出登录失败";
}
}
