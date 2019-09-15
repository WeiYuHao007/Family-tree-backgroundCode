package com.orange.familyTree.entity.mysql;

import org.apache.ibatis.type.Alias;

@Alias(value = "GenealogyFocusApplication")
public class GenealogyFocusApplication {

    public GenealogyFocusApplication() {}

    public GenealogyFocusApplication(Long focusApplicationId, Long userId, Long genealogyId, String applicationComment) {
        this.focusApplicationId = focusApplicationId;
        this.userId = userId;
        this.genealogyId = genealogyId;
        this.applicationComment = applicationComment;
    }

    private Long focusApplicationId;

    private Long userId;

    private Long genealogyId;

    private String applicationComment;

    public Long getFocusApplicationId() {
        return focusApplicationId;
    }

    public void setFocusApplicationId(Long focusApplicationId) {
        this.focusApplicationId = focusApplicationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGenealogyId() {
        return genealogyId;
    }

    public void setGenealogyId(Long genealogyId) {
        this.genealogyId = genealogyId;
    }

    public String getApplicationComment() {
        return applicationComment;
    }

    public void setApplicationComment(String applicationComment) {
        this.applicationComment = applicationComment;
    }
}
