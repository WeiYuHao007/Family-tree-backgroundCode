package com.orange.familyTree.pojo.specialPojo;

public class NewUserNicknameAndIntroduction {
    public NewUserNicknameAndIntroduction() {
    }

    public NewUserNicknameAndIntroduction(String newNickname, String newIntroduction) {
        this.newNickname = newNickname;
        this.newIntroduction = newIntroduction;
    }

    private String newNickname;

    private String newIntroduction;

    public String getNewNickname() {
        return newNickname;
    }

    public void setNewNickname(String newNickname) {
        this.newNickname = newNickname;
    }

    public String getNewIntroduction() {
        return newIntroduction;
    }

    public void setNewIntroduction(String newIntroduction) {
        this.newIntroduction = newIntroduction;
    }
}
