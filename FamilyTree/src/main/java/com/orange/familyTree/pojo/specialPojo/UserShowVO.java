package com.orange.familyTree.pojo.specialPojo;

public class UserShowVO {
    // 用来渲染用户个人界面的数据

    public UserShowVO() {}

    public UserShowVO(String nickname, String introduction, String registerTime) {
        this.nickname = nickname;
        this.introduction = introduction;
        this.registerTime = registerTime;
    }

    private String nickname;

    private String introduction;

    private String registerTime;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }
}
