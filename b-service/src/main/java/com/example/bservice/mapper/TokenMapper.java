package com.example.bservice.mapper;


import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenMapper {

    @Select("select token from oauth_access_token where user_name = #{name}")
    byte[] queryToken(String name);
}
