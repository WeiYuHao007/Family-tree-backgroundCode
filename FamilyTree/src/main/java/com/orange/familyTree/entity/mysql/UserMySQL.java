package com.orange.familyTree.entity.mysql;

import com.orange.familyTree.pojo.UserDO;
import org.apache.ibatis.type.Alias;

@Alias(value = "userMySQL")
public class UserMySQL {

    public UserMySQL() {}

    public UserMySQL(Long userId, String userNickname, Integer userPhoneNum, String userEmail, String userPassword,
                     String userIntroduction, String userAvatar, String userRegisterTime, Integer userRole) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.userPhoneNum = userPhoneNum;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userIntroduction = userIntroduction;
        this.userAvatar = userAvatar;
        this.userRegisterTime = userRegisterTime;
        this.userRole = userRole;
    }

    private Long userId;

    private String userNickname;

    private Integer userPhoneNum;

    private String userEmail;

    private String userPassword;

    private String userIntroduction;

    private String userAvatar;

    private String userRegisterTime;

    private Integer userRole;

    public static UserDO changeToDO(UserMySQL userMySQL) {
        if (userMySQL != null) {
            Long userId = userMySQL.getUserId();
            String userNickname = userMySQL.getUserNickname();
            Integer userPhoneNum = userMySQL.getUserPhoneNum();
            String userEmail = userMySQL.getUserEmail();
            String userPassword = userMySQL.getUserPassword();
            String userIntroduction = userMySQL.getUserIntroduction();
            String userAvatar = userMySQL.getUserAvatar();
            String userRegisterTime = userMySQL.getUserRegisterTime();
            Integer userRoleNum = userMySQL.getUserRole();
            UserDO userDO = new UserDO(userId, userNickname, userPhoneNum, userEmail, userPassword, userIntroduction,
                    userAvatar, userRegisterTime, userRoleNum);
            return userDO;
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

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }
}
