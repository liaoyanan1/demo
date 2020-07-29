package com.example.oauth2.mapper;



import com.example.oauth2.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
/** @author lyn
 * TODO 查询数据库用户接口
 * @date 2020/7/29 9:46
*/
@Repository
public interface UserInfoMapper {
    @Select("SELECT * FROM user WHERE userLoginName = #{userName}")
    UserInfo queryUserByUserName(String userName);

    @Insert("insert into user(userId,userLoginName,userName,password) value(#{userId},#{userLoginName},#{userName},#{password})")
    boolean insertNewUser(UserInfo user);
}