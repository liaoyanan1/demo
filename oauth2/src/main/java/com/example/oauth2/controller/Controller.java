package com.example.oauth2.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    //退出 删除token

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("/tokenLogout")
    public String deleteToken(HttpServletResponse response){
        SecurityContext context = SecurityContextHolder.getContext();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) context.getAuthentication().getDetails();
        String tokenValue = details.getTokenValue();
        response.setHeader("Authorization"," ");
        response.setHeader("AUTHORIZATION_HEADER"," ");
      return consumerTokenServices.revokeToken(tokenValue)==true?"退出成功":"退出登录失败";
    }
}
