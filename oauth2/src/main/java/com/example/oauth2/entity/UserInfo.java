package com.example.oauth2.entity;

import java.io.Serializable;

/** @author lyn
 * TODO 数据库用户实体类
 * @date 2020/7/29 9:46
*/
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 11111L;

    private Integer userId ;

    private  String userLoginName;

    private String userName;

    private String password;

    private String phone;

    private String code;

    public String getPhone() {
        return phone;
    }

    public UserInfo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getCode() {
        return code;
    }

    public UserInfo setCode(String code) {
        this.code = code;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userLoginName='" + userLoginName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}