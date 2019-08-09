package com.orange.familyTree.pojo;

import com.orange.familyTree.entity.mysql.User;

public class UserDO {

    public UserDO() {}

    public UserDO(Long userId, String userNickname, Integer userPhoneNum, String userEmail, String userPassword,
                String userRegisterTime, Integer userRoleNum) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.userPhoneNum = userPhoneNum;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userRegisterTime = userRegisterTime;
        this.userRoleNum = userRoleNum;
    }

    private Long userId;

    private String userNickname;

    private Integer userPhoneNum;

    private String userEmail;

    private String userPassword;

    private String userRegisterTime;

    private Integer userRoleNum;

    public static UserDO changeEToDO(User user) {

        Long userId = user.getUserId();
        String userNickname = user.getUserNickname();
        Integer userPhoneNum = user.getUserPhoneNum();
        String userEmail = user.getUserEmail();
        String userPassword = user.getUserPassword();
        String userRegisterTime = user.getUserRegisterTime();
        Integer userRoleNum = user.getUserRole();
        UserDO userDO = new UserDO(userId, userNickname, userPhoneNum, userEmail, userPassword, userRegisterTime,
                userRoleNum);
        return userDO;

    }

    public static UserVO changeToVo(UserDO userDO) {

        String email = userDO.userEmail;
        Integer phoneNum = userDO.userPhoneNum;
        String nickname = userDO.userNickname;
        String registrationTime = userDO.userRegisterTime;
        UserVO userVO = new UserVO(email, phoneNum, nickname, registrationTime);
        return userVO;

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

    public void setUserNickname(String userNickName) {
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

    public Integer getUserRoleNum() {
        return userRoleNum;
    }

    public void setUserRoleNum(Integer userRoleNum) {
        this.userRoleNum = userRoleNum;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
