package com.orange.familyTree.pojo;

public class GenealogyFocusApplicationVO {

    public GenealogyFocusApplicationVO() {}

    public GenealogyFocusApplicationVO(String genealogyName, String userName, String applicationComment) {
        this.genealogyName = genealogyName;
        this.userName = userName;
        this.applicationComment = applicationComment;
    }

    private String genealogyName;

    private String userName;

    private String applicationComment;

    public String getGenealogyName() {
        return genealogyName;
    }

    public void setGenealogyName(String genealogyName) {
        this.genealogyName = genealogyName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getApplicationComment() {
        return applicationComment;
    }

    public void setApplicationComment(String applicationComment) {
        this.applicationComment = applicationComment;
    }
}
