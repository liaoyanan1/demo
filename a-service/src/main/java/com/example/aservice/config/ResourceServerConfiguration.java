package com.example.aservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;
/** @author lyn
 * TODO 资源服务器拦截配置
 * @date 2020/7/28 14:32
*/
@EnableResourceServer //oauth2开启资源服务
@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //开放访问
        http.authorizeRequests().mvcMatchers("/swagger-ui.html/**","/webjars/**","/swagger-resources/**","/v2/**","/api/**","/actuator/hystrix.stream").permitAll();
        //权限访问
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();

    }

}
