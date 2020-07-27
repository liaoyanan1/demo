package com.example.oauth2.config;

import com.example.oauth2.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {




    @Autowired
    private DataSource dataSource;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public RedisTokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
      //  return new JdbcTokenStore(dataSource);
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(myUserDetailsService)
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter())
                .reuseRefreshTokens(false);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    /*    security.allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");*/
        security.allowFormAuthenticationForClients()
                .tokenKeyAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        KeyStoreKeyFactory keyStoreKeyFactory =  new KeyStoreKeyFactory(new ClassPathResource("volunteer.jks"), "volunteer123".toCharArray());
        jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("volunteer"));
        return  jwtAccessTokenConverter;
    }
}
