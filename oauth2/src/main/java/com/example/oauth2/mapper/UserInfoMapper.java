package com.example.oauth2.mapper;



import com.example.oauth2.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


public interface UserInfoMapper {
    @Select("SELECT * FROM user WHERE userLoginName = #{userName}")
    UserInfo queryUserByUserName(String userName);

    @Insert("insert into user(userId,userLoginName,userName,password) value(#{userId},#{userLoginName},#{userName},#{password})")
    boolean insertNewUser(UserInfo user);
}