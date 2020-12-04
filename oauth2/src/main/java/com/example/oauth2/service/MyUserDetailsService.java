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
/** @author lyn
 * TODO 自定义用户信息类
 * @date 2020/12/4 11:13
*/
@Service
public class MyUserDetailsService implements UserDetailsService {

    //默认用户名密码登录
    @Autowired
    UserInfoMapper userInfoMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoMapper.queryUserByUserName(s);
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ADMIN");
        List<GrantedAuthority> grantedAuthorities  = new ArrayList<>();
        grantedAuthorities.add(grantedAuthority);
        User user = new User(userInfo.getUserLoginName(),userInfo.getPassword(),grantedAuthorities);
        return user;
    }
  //grant_type 未phone_token时
    public UserDetails loadUserByPhoneAndCode(String phone,String code) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoMapper.queryUserByPhoneAndCode(phone, code);
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ADMIN");
        List<GrantedAuthority> grantedAuthorities  = new ArrayList<>();
        grantedAuthorities.add(grantedAuthority);
        User user = new User(userInfo.getUserLoginName(),userInfo.getPassword(),grantedAuthorities);
        return user;
    }
}