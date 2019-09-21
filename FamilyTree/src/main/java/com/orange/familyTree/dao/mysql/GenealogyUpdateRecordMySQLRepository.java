package com.orange.familyTree.dao.mysql;

import com.orange.familyTree.entity.mysql.GenealogyUpdateRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;

@Repository
public interface GenealogyUpdateRecordMySQLRepository {
    // 查找指定图谱id更新动态
    ArrayList<GenealogyUpdateRecord> findUpdateRecordByGenealogiesId(@Param("ids") ArrayList<Long> genealogiesId);

    // 插入动态
    void createUpdateRecord(@Param("id") Long genealogyId, @Param("commit") String updateCommit,
                            @Param("time") Timestamp updateTime, @Param("remark") String updateRemark);
}
