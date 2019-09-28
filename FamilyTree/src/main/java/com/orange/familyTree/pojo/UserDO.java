package com.orange.familyTree.pojo;

import com.orange.familyTree.entity.mysql.UserMySQL;
import com.orange.familyTree.pojo.specialPojo.UserShowVO;

public class UserDO {

    public UserDO() {}

    public UserDO(Long userId, String userNickname, Integer userPhoneNum, String userEmail, String userPassword,
                  String userIntroduction, String userAvatar, String userRegisterTime, Integer userRoleNum) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.userPhoneNum = userPhoneNum;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userIntroduction = userIntroduction;
        this.userAvatar = userAvatar;
        this.userRegisterTime = userRegisterTime;
        this.userRoleNum = userRoleNum;
    }

    private Long userId;

    private String userNickname;

    private Integer userPhoneNum;

    private String userEmail;

    private String userPassword;

    private String userIntroduction;

    private String userAvatar;

    private String userRegisterTime;

    private Integer userRoleNum;

    public static UserVO changeToVo(UserDO userDO) {

        String email = userDO.userEmail;
        Integer phoneNum = userDO.userPhoneNum;
        String nickname = userDO.userNickname;
        String introduction = userDO.userIntroduction;
        String registrationTime = userDO.userRegisterTime;
        UserVO userVO = new UserVO(email, phoneNum, nickname, introduction, registrationTime);
        return userVO;

    }

    public static UserShowVO changeToShow(UserDO userDO) {
        if(userDO != null) {
            String nickname = userDO.userNickname;
            String introduction = userDO.userIntroduction;
            String avatar = userDO.userAvatar;
            String registrationTime = userDO.userRegisterTime;
            UserShowVO userShow = new UserShowVO(nickname, introduction, avatar, registrationTime);
            return userShow;
        }
        else {
            return null;
        }
    }

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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserIntroduction() {
        return userIntroduction;
    }

    public void setUserIntroduction(String userIntroduction) {
        this.userIntroduction = userIntroduction;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserRegisterTime() {
        return userRegisterTime;
    }

    public void setUserRegisterTime(String userRegisterTime) {
        this.userRegisterTime = userRegisterTime;
    }

    public Integer getUserRoleNum() {
        return userRoleNum;
    }

    public void setUserRoleNum(Integer userRoleNum) {
        this.userRoleNum = userRoleNum;
    }
}
