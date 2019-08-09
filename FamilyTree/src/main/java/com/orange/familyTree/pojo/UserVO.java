package com.orange.familyTree.pojo;

public class UserVO {
    // 前端能获取的User，数据不具有敏感性

    public UserVO() {}

    public UserVO(String email, Integer phoneNum, String nickName, String registrationTime) {
        super();
        this.email = email;
        this.phoneNum = phoneNum;
        this.nickName = nickName;
        this.registrationTime = registrationTime;
    }


    private String email;

    private Integer phoneNum;

    private String nickName;

    private String registrationTime;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Integer phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }
}
