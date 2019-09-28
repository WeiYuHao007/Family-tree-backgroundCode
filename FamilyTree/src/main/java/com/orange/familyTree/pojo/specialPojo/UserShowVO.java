package com.orange.familyTree.pojo.specialPojo;

public class UserShowVO {
    // 用来渲染用户个人界面的数据

    public UserShowVO() {}

    public UserShowVO(String nickname, String introduction, String avatar, String registerTime) {
        this.nickname = nickname;
        this.introduction = introduction;
        this.avatar = avatar;
        this.registerTime = registerTime;
    }

    private String nickname;

    private String introduction;

    private String avatar;

    private String registerTime;

    public String getNickname() {
        return nickname;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getRegisterTime() {
        return registerTime;
    }
}
