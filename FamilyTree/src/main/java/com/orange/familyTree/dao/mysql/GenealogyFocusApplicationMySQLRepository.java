package com.orange.familyTree.dao.mysql;

import com.orange.familyTree.entity.mysql.GenealogyFocusApplication;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface GenealogyFocusApplicationMySQLRepository {

    // 返回族谱的所有关注请求，通过族谱ID
    ArrayList<GenealogyFocusApplication> findApplicationByGenealogyId(@Param("id") Long genealogyId);

    // 返回所有已经发出的关注请求，通过用户ID
    ArrayList<GenealogyFocusApplication> findApplicationByUserId(@Param("id") Long userId);

    // 创建申请请求
    void createApplication(@Param("genealogyId") Long genealogyId, @Param("userId") Long userId,
                           @Param("comment") String applicationComment);

    // 删除申请，通过图谱id与用户id
    void deleteApplication(@Param("genealogyId") Long genealogyId, @Param("userId") Long userId);
}
