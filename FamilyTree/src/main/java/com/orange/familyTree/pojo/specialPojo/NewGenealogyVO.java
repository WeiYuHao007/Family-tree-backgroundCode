package com.orange.familyTree.pojo.specialPojo;

public class NewGenealogyVO {
    // 创建图谱的实体

    public NewGenealogyVO() {}

    public NewGenealogyVO(String newGenealogyName, String newGenealogyDescription, String defaultCenterNodeName,
                          String nodeBirthTime, String nodeDeathTime, String nodeMajorAchievements) {
        this.newGenealogyName = newGenealogyName;
        this.newGenealogyDescription = newGenealogyDescription;
        this.defaultCenterNodeName = defaultCenterNodeName;
        this.nodeBirthTime = nodeBirthTime;
        this.nodeDeathTime = nodeDeathTime;
        this.nodeMajorAchievements = nodeMajorAchievements;
    }

    private String newGenealogyName;

    private String newGenealogyDescription;

    private String defaultCenterNodeName;

    private String nodeBirthTime;

    private String nodeDeathTime;

    private String nodeMajorAchievements;

    public String getNewGenealogyName() {
        return newGenealogyName;
    }

    public void setNewGenealogyName(String newGenealogyName) {
        this.newGenealogyName = newGenealogyName;
    }

    public String getNewGenealogyDescription() {
        return newGenealogyDescription;
    }

    public void setNewGenealogyDescription(String newGenealogyDescription) {
        this.newGenealogyDescription = newGenealogyDescription;
    }

    public String getDefaultCenterNodeName() {
        return defaultCenterNodeName;
    }

    public void setDefaultCenterNodeName(String defaultCenterNodeName) {
        this.defaultCenterNodeName = defaultCenterNodeName;
    }

    public String getNodeBirthTime() {
        return nodeBirthTime;
    }

    public void setNodeBirthTime(String nodeBirthTime) {
        this.nodeBirthTime = nodeBirthTime;
    }

    public String getNodeDeathTime() {
        return nodeDeathTime;
    }

    public void setNodeDeathTime(String nodeDeathTime) {
        this.nodeDeathTime = nodeDeathTime;
    }

    public String getNodeMajorAchievements() {
        return nodeMajorAchievements;
    }

    public void setNodeMajorAchievements(String nodeMajorAchievements) {
        this.nodeMajorAchievements = nodeMajorAchievements;
    }
}
