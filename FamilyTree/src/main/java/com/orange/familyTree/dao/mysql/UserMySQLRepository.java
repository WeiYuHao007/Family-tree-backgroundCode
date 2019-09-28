package com.orange.familyTree.dao.mysql;

import com.orange.familyTree.entity.mysql.UserMySQL;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserMySQLRepository {

    // 账户注册（MySQL）
    void registerUser(@Param("name") String userNickname, @Param("phoneNum") Integer userPhoneNum,
                      @Param("email") String userEmail, @Param("password") String password);

    // 通过用户ID查找用户（MySQL）
    UserMySQL findUserById(@Param("id") Long userId);

    // 通过电话号码查找用户（MySQL）
    UserMySQL findUserByPhoneNumAndPassword(@Param("phoneNum") Integer phoneNum, @Param("password") String password);

    // 通过邮箱查找用户（MySQL）
    UserMySQL findUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    // 通过用户昵称查找用户（MySQL）
    UserMySQL findUserByNickName(@Param("nickname") String nickname);

    // 通过用户id查找用户昵称（MySQL）
    String findUserNicknameById(@Param("id") Long userId);

    // 通过多位用户id查找昵称（MySQL）
    List<String> findUsersNicknameByIds(@Param("ids") List<Long> userIds);

    // 查找用户id通过用户昵称（MySQL）
    Long findUserIdByNickname(@Param("nickname") String userNickname);

    // 查询用户头像文件名称（MySQL）
    String findUserAvatar(@Param("nickname") String userNickname);

    // 销毁用户（MySQL）
    void destroyUserById(@Param("id") Long userId);

    // 修改密码（MySQL）
    void changePassword(@Param("id") Long userId, @Param("oldPassword") String oldPassword,
                        @Param("newPassword") String newPassword);

    // 修改用户头像文件名称（MySQL）
    void changeUserAvatar(@Param("nickname") String userNickname, @Param("avatar") String newUserAvatar);

    // 修改用户昵称和个人简介（MySQL）
    void changeUserNicknameAndIntroduction(@Param("oldNickname") String oldNickname, @Param("newNickname") String newNickname,
                                           @Param("introduction") String newIntroduction);
}