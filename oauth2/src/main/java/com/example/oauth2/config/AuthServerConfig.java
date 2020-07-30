package com.example.oauth2.config;

import com.example.oauth2.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(
            AuthorizationServerSecurityConfigurer oauthServer){
        oauthServer.allowFormAuthenticationForClients()
                .tokenKeyAccess("isAuthenticated()");
    }
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //访问数据库获取客户端id和密码权限等
        clients.jdbc(dataSource);
      /*  clients.inMemory()
                .withClient("lyn")
                .secret(passwordEncoder().encode("123456"))
                .scopes("read","write")
                .authorities("ROLE_CLIENT")
                .authorizedGrantTypes("authorization_code","refresh_token","implicit","password")
                .redirectUris("http://localhost:8080/A/login","http://localhost:9090/B/login")
                .autoApprove(true);*/
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .userDetailsService(myUserDetailsService)
                 .authenticationManager(authenticationManager);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public JdbcTokenStore tokenStore(){
        return  new JdbcTokenStore(dataSource);
    }

}