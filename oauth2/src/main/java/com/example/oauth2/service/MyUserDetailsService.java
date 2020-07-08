package com.example.oauth2.service;


import com.example.oauth2.entity.UserInfo;
import com.example.oauth2.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserInfoMapper userInfoMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoMapper.queryUserByUserName(s);
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ADMIN");
        List<GrantedAuthority> grantedAuthorities  = new ArrayList<>();
        grantedAuthorities.add(grantedAuthority);
        System.out.println(s);
        System.out.println(userInfo);
        User user = new User(userInfo.getUserLoginName(),userInfo.getPassword(),grantedAuthorities);
        return user;
    }
}