package com.orange.familyTree.pojo.specialPojo;

public class ApplicationVO {

    public ApplicationVO() {}

    public ApplicationVO(String comment) {
        this.comment = comment;
    }

    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
