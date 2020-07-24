package com.example.gatewayservice.service;


import com.example.gatewayservice.entity.UserInfo;
import com.example.gatewayservice.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;

    public boolean creatUser(UserInfo userInfo) {
        return userInfoMapper.insertNewUser(userInfo);
    }
}