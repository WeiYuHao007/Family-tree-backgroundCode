package com.orange.familyTree.dao.mysql;

import com.orange.familyTree.entity.mysql.Genealogy;
import org.apache.ibatis.annotations.Param;

public interface GenealogyMySQLRepository {

    // 通过图谱Id查询指定图谱（MySQL）
    Genealogy findGenealogyById(@Param("id") Long genealogyId);
    
    // 通过图谱名查询指定图谱（MySQL）
    Long findGenealogyIdByName(@Param("name") String genealogyName);

    // 创建图谱（MySQL）
    void createGenealogy(@Param("name") String genealogyName, @Param("admin") String genealogyAdmin,
                         @Param("description") String genealogyDescription);
    // 删除图谱（MySQL）
    void deleteGenealogy(@Param("id") Long genealogyId);

    // 修改图谱名称（注意在其他数据库中也要更新数据）（MySQL）
    void changeGenealogyName(@Param("id") Long genealogyId, @Param("name") String genealogyName);

    // 修改图谱描述（MySQL）
    void changeGenealogyDescription(@Param("id") Long genealogyId, @Param("description") String genealogyDescription);

    // 修改图谱管理员（MySQL）
    void changeGenealogyAdmin(@Param("id") Long id, @Param("admins") String admins);
}
