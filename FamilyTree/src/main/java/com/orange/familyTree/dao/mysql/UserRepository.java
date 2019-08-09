package com.orange.familyTree.dao.mysql;

import com.orange.familyTree.entity.mysql.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    // 账户注册
    void registerUser(@Param("name") String userNickname, @Param("phoneNum") Integer userPhoneNum,
                             @Param("email") String userEmail, @Param("password") String password);

    // 通过电话号码查找用户
    User findUserByPhoneNumAndPassword(@Param("phoneNum") Integer phoneNum, @Param("password") String password);

    // 通过邮箱查找用户
    User findUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
