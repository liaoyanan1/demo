package com.example.gatewayservice.config;


import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/** @author lyn
 * TODO 开启oauth2客户端配置
 * @date 2020/7/29 9:26
*/
@Configuration
@EnableResourceServer
@EnableOAuth2Sso
@Order(-10)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers( "/login","/oauth2/**")
                .permitAll()
                .anyRequest()
                .authenticated();
        http.csrf().disable();
    }

}
