package com.orange.familyTree.pojo.specialPojo;

public class DescriptionAndNameVO {

    public DescriptionAndNameVO() {}

    public DescriptionAndNameVO(String newGenealogyName, String newDescription) {
        this.newGenealogyName = newGenealogyName;
        this.newDescription = newDescription;
    }

    String newGenealogyName;

    String newDescription;

    public String getNewGenealogyName() {
        return newGenealogyName;
    }

    public void setNewGenealogyName(String newGenealogyName) {
        this.newGenealogyName = newGenealogyName;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }
}
