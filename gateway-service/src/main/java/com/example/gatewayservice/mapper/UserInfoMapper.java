package com.example.gatewayservice.mapper;



import com.example.gatewayservice.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapper {
    @Select("SELECT * FROM user WHERE userLoginName = #{userName}")
    UserInfo queryUserByUserName(String userName);

    @Insert("insert into user(userId,userLoginName,userName,password) value(#{userId},#{userLoginName},#{userName},#{password})")
    boolean insertNewUser(UserInfo user);
}