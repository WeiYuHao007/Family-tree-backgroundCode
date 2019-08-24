package com.orange.familyTree.pojo;

public class UserVO {

    // 前端个人卡片中的数据

    public UserVO() {}

    public UserVO(String email, Integer phoneNum, String nickName, String introduction,String registrationTime) {
        super();
        this.email = email;
        this.phoneNum = phoneNum;
        this.nickName = nickName;
        this.introduction = introduction;
        this.registrationTime = registrationTime;
    }


    private String email;

    private Integer phoneNum;

    private String nickName;

    private String registrationTime;

    private String introduction;


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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
