package com.orange.familyTree.pojo;

public class GenealogyFocusApplicationVO {

    public GenealogyFocusApplicationVO() {}

    public GenealogyFocusApplicationVO(String genealogyName, String userNickname, String applicationComment) {
        this.genealogyName = genealogyName;
        this.userNickname = userNickname;
        this.applicationComment = applicationComment;
    }

    private String genealogyName;

    private String userNickname;

    private String applicationComment;

    public String getGenealogyName() {
        return genealogyName;
    }

    public void setGenealogyName(String genealogyName) {
        this.genealogyName = genealogyName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getApplicationComment() {
        return applicationComment;
    }

    public void setApplicationComment(String applicationComment) {
        this.applicationComment = applicationComment;
    }
}
