package com.orange.familyTree.entity.mysql;

import com.orange.familyTree.pojo.GenealogyUpdateRecordVO;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Alias(value = "GenealogyUpdateRecord")
public class GenealogyUpdateRecord {

    public GenealogyUpdateRecord() {}

    public GenealogyUpdateRecord(Long updateRecordId, Long genealogyId, Timestamp updateTime, String updateCommit,
                                 String updateRemark) {
        this.updateRecordId = updateRecordId;
        this.genealogyId = genealogyId;
        this.updateCommit = updateCommit;
        this.updateTime = updateTime;
        this.updateRemark = updateRemark;
    }

    private Long updateRecordId;

    private Long genealogyId;

    private String updateCommit;

    private Timestamp updateTime;

    private String updateRemark;

    public static GenealogyUpdateRecordVO changeToVO(GenealogyUpdateRecord updateRecord) {
        String updateCommit = updateRecord.updateCommit;
        String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(updateRecord.updateTime);
        String updateRemark = updateRecord.updateRemark;
        return new GenealogyUpdateRecordVO(updateTime, updateCommit, updateRemark);
    }

    public Long getUpdateRecordId() {
        return updateRecordId;
    }

    public void setUpdateRecordId(Long updateRecordId) {
        this.updateRecordId = updateRecordId;
    }

    public Long getGenealogyId() {
        return genealogyId;
    }

    public void setGenealogyId(Long genealogyId) {
        this.genealogyId = genealogyId;
    }

    public String getUpdateCommit() {
        return updateCommit;
    }

    public void setUpdateCommit(String updateCommit) {
        this.updateCommit = updateCommit;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateRemark() {
        return updateRemark;
    }

    public void setUpdateRemark(String updateRemark) {
        this.updateRemark = updateRemark;
    }
}
