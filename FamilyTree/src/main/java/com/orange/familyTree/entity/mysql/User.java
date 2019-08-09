package com.orange.familyTree.entity.mysql;

import org.apache.ibatis.type.Alias;

@Alias(value = "user")
public class User {

    public User() {}

    public User(Long userId, String userNickname, Integer userPhoneNum, String userEmail, String userPassword,
                String userRegisterTime, Integer userRole) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.userPhoneNum = userPhoneNum;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userRegisterTime = userRegisterTime;
        this.userRole = userRole;
    }

    private Long userId;

    private String userNickname;

    private Integer userPhoneNum;

    private String userEmail;

    private String userPassword;

    private String userRegisterTime;

    private Integer userRole;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public Integer getUserPhoneNum() {
        return userPhoneNum;
    }

    public void setUserPhoneNum(Integer userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserRegisterTime() {
        return userRegisterTime;
    }

    public void setUserRegisterTime(String userRegisterTime) {
        this.userRegisterTime = userRegisterTime;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
