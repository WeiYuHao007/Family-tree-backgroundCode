package com.orange.familyTree.entity.mysql;

import org.apache.ibatis.type.Alias;

@Alias(value = "genealogy")
public class Genealogy {

    public Genealogy() {}

    public Genealogy(Long genealogyId, String genealogyName, String genealogyAdmin, String genealogyDescription) {
        this.genealogyId = genealogyId;
        this.genealogyName = genealogyName;
        this.genealogyAdmin = genealogyAdmin;
        this.genealogyDescription = genealogyDescription;
    }

    private Long genealogyId;

    private String genealogyName;

    private String genealogyAdmin;

    private String genealogyDescription;

    public Long getGenealogyId() {
        return genealogyId;
    }

    public void setGenealogyId(Long genealogyId) {
        this.genealogyId = genealogyId;
    }

    public String getGenealogyName() {
        return genealogyName;
    }

    public void setGenealogyName(String genealogyName) {
        this.genealogyName = genealogyName;
    }

    public String getGenealogyAdmin() {
        return genealogyAdmin;
    }

    public void setGenealogyAdmin(String genealogyAdmin) {
        this.genealogyAdmin = genealogyAdmin;
    }

    public String getGenealogyDescription() {
        return genealogyDescription;
    }

    public void setGenealogyDescription(String genealogyDescription) {
        this.genealogyDescription = genealogyDescription;
    }
}
