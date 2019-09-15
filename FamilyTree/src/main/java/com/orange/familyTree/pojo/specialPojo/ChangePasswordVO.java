package com.orange.familyTree.pojo.specialPojo;

public class ChangePasswordVO {

    public ChangePasswordVO() {}

    public ChangePasswordVO(Long telephoneNum, String oldPassword, String newPassword) {
        this.telephoneNum = telephoneNum;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    private Long telephoneNum;

    private String oldPassword;

    private String newPassword;

    public Long getTelephoneNum() {
        return telephoneNum;
    }

    public void setTelephoneNum(Long telephoneNum) {
        this.telephoneNum = telephoneNum;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
