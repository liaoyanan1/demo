package com.example.oauth2.endpoint;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/** @author lyn
 * TODO 退出登录 测试过
 * @date 2020/7/29 9:46
*/
@FrameworkEndpoint
public class RevokeTokenEndpoint {

    @Autowired
    @Qualifier("consumerTokenServices")
    ConsumerTokenServices consumerTokenServices;

    @RequestMapping(method = RequestMethod.DELETE, value = "/oauth/revokeToken")
    @ResponseBody
    public String revokeToken(String access_token) {
        if (consumerTokenServices.revokeToken(access_token)){
            return "注销成功";
        }else{
            return "注销失败";
        }
    }
}
