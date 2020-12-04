package com.example.oauth2.service;


import com.example.oauth2.entity.UserInfo;
import com.example.oauth2.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;

    public boolean creatUser(UserInfo userInfo) {
        return userInfoMapper.insertNewUser(userInfo);
    }

    public UserInfo queryUser(String name){
      return   userInfoMapper.queryUserByUserName(name);
    }
    public UserInfo queryUserByPhoneAndCode(String phone,String code){
      return   userInfoMapper.queryUserByPhoneAndCode(phone,code);
    }

}