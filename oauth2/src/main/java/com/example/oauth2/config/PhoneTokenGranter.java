package com.example.oauth2.config;


import com.example.oauth2.service.MyUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;
/** @author lyn
 * TODO 自定义登录类型
 * client_id=login
 * &client_secret=123456
 * &grant_type=phone_token
 * &phone=155****3929
 * &code=666666
 * @date 2020/12/4 11:08
*/

public class PhoneTokenGranter extends AbstractTokenGranter  {

    private static final String GRANT_TYPE = "phone_token";

    private MyUserDetailsService myUserDetailsService;

    protected PhoneTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
    }

    protected PhoneTokenGranter(MyUserDetailsService myUserDetailsService,AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> requestParameters = tokenRequest.getRequestParameters();
        UserDetails userDetails = myUserDetailsService.loadUserByPhoneAndCode(requestParameters.get("phone"), requestParameters.get("code"));
        OAuth2Request storedRequest = getRequestFactory().createOAuth2Request(client,tokenRequest);
        Authentication userAuthentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
        return new OAuth2Authentication(storedRequest,userAuthentication);
    }

}
