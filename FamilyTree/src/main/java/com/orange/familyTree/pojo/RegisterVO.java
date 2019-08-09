package com.orange.familyTree.pojo;

public class RegisterVO {

    public RegisterVO() {}

    public RegisterVO(String nickname, Integer phoneNum, String email, String password) {
        this.nickname = nickname;
        this.phoneNum = phoneNum;
        this.email = email;
        this.password = password;
    }

    private String nickname;

    private Integer phoneNum;

    private String email;

    private String password;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Integer phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
