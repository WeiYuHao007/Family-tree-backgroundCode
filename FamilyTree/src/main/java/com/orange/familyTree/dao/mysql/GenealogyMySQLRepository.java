package com.orange.familyTree.dao.mysql;

import com.orange.familyTree.entity.mysql.GenealogyMySQL;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface GenealogyMySQLRepository {

    // 通过图谱Id查询指定图谱（MySQL）
    GenealogyMySQL findGenealogyById(@Param("id") Long genealogyId);

    // 通过图谱Id查询指定图谱名称（MySQL）
    String findGenealogyNameById(@Param("id") Long genealogyId);

    // 通过图谱name查询指定图谱（MySQL）
    GenealogyMySQL findGenealogyByName(@Param("name") String genealogyName);

    // 通过图谱名查询指定图谱（MySQL）
    Long findGenealogyIdByName(@Param("name") String genealogyName);

    // 查询图谱管理员
    String findGenealogyAdminByName(@Param("name") String genealogyName);

    // 查询图谱的默认中心节点
    String getGenealogyDefaultCenterPerson(@Param("name") String genealogyName);

    // 关键词查询图谱
    ArrayList<GenealogyMySQL> keywordSearchGenealogy(@Param("keyword") String keyword, @Param("start") int start);

    // 创建图谱（MySQL）
    void createGenealogy(@Param("name") String genealogyName, @Param("admin") String genealogyAdmin,
                         @Param("centerPersonName") String centerPersonName, @Param("description") String genealogyDescription);
    // 删除图谱（MySQL）
    void deleteGenealogy(@Param("id") Long genealogyId);

    // 修改图谱名称（MySQL）
    void changeGenealogyNameById(@Param("id") Long genealogyId, @Param("name") String genealogyName);

    // 修改图谱名称（MySQL）
    void changeGenealogyNameByName(@Param("oldName") String oldName, @Param("newName") String newName);

    // 修改图谱描述（MySQL）
    void changeGenealogyDescription(@Param("id") Long genealogyId, @Param("description") String genealogyDescription);

    // 修改中心节点（MySQL）
    void changeDefaultCenterPerson(@Param("id") Long genealogyId, @Param("centerPerson") String defaultCenterPerson);

    // 修改图谱管理员（MySQL）
    void changeGenealogyAdmin(@Param("id") Long genealogyId, @Param("admins") String admins);
}
