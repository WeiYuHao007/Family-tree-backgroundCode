package com.orange.familyTree.pojo;

import java.sql.Timestamp;

public class GenealogyUpdateRecordVO {

    public GenealogyUpdateRecordVO() {}

    public GenealogyUpdateRecordVO(String updateTime, String updateCommit, String updateRemark) {
        this.updateCommit = updateCommit;
        this.updateTime = updateTime;
        this.updateRemark = updateRemark;
    }

    private String updateCommit;

    private String updateTime;

    private String updateRemark;

    public String getUpdateCommit() {
        return updateCommit;
    }

    public void setUpdateCommit(String updateCommit) {
        this.updateCommit = updateCommit;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateRemark() {
        return updateRemark;
    }

    public void setUpdateRemark(String updateRemark) {
        this.updateRemark = updateRemark;
    }
}
