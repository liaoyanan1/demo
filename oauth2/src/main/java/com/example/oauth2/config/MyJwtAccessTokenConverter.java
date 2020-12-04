package com.example.oauth2.config;

import com.example.oauth2.mapper.UserInfoMapper;
import com.example.oauth2.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
/** @author lyn
 * TODO jwt加密方式在token 中添加附加信息
 * @date 2020/12/3 16:01
*/
public class MyJwtAccessTokenConverter extends JwtAccessTokenConverter {

    @Autowired
    UserInfoService userInfoService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInformation = new HashMap<>();
        additionalInformation.put("jwt加密附加信息","QAQ");
        additionalInformation.put("user",userInfoService.queryUser(authentication.getName()));
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
        return super.enhance(accessToken,authentication);
    }
}
