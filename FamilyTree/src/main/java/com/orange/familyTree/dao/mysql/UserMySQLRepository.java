package com.orange.familyTree.dao.mysql;

import com.orange.familyTree.entity.mysql.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMySQLRepository {

    // 账户注册（MySQL）
    void registerUser(@Param("name") String userNickname, @Param("phoneNum") Integer userPhoneNum,
                             @Param("email") String userEmail, @Param("password") String password);

    // 通过用户ID查找用户（MySQL）
    User findUserById(@Param("id") Long userId);

    // 通过电话号码查找用户（MySQL）
    User findUserByPhoneNumAndPassword(@Param("phoneNum") Integer phoneNum, @Param("password") String password);

    // 通过邮箱查找用户（MySQL）
    User findUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    // 通过用户昵称查找用户（MySQL）
    User findUserByNickName(@Param("nickname") String nickname);

    // 销毁用户（MySQL）
    void destroyUserById(@Param("id") Long userId);
}
