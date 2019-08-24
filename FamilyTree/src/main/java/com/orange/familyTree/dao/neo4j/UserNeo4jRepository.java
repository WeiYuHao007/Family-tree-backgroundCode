package com.orange.familyTree.dao.neo4j;

import com.orange.familyTree.entity.neo4j.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserNeo4jRepository extends Neo4jRepository<User, Long> {

    // 创建User节点（Neo4j）
    @Query("CREATE (u:User) \n" +
            "SET u.userId = {userId}")
    void createUser(@Param("userId") Long userId);

    // 销毁User节点（Neo4j）
    @Query("MATCH (u:User) \n" +
            "WHERE u.userId = {userId} \n" +
            "DETACH DELETE u")
    void destroyUser(@Param("userId") Long userId);

    // 同意关注指定名称的图谱的请求（Neo4j）
    @Query("MATCH (u:User)-[r:FOCUS_ON]->(g:Genealogy) \n" +
            "WHERE u.userId = {userId} AND g.genealogyId = {genealogyId} " +
            "AND r.show = false AND r.admin = false \n" +
            "SET r.show = true")
    void focusOnGenealogy(@Param("userId") Long userId, @Param("genealogyId") Long genealogyId);

    // 取消关注指定图谱（Neo4j）
    @Query("MATCH (u:User)-[r:FOCUS_ON]->(g:Genealogy) \n" +
            "WHERE u.userId = {userId} AND g.genealogyId = {genealogyId} " +
            "AND r.show = true AND r.admin = false \n" +
            "REMOVE r")
    void cancelFocusOnGenealogy();

    // 根据图谱名称搜索图谱（Neo4j）

    // 申请关注指定图谱（Neo4j）
    @Query("MATCH (u:User),(g:Genealogy) \n" +
            "WHERE u.userId = {userId} AND g.genealogyId = {genealogyId} \n" +
            "CREATE (u)-[r:FOCUS_ON{show: false, admin: false}]->(g)")
    void applyForFocusOnGenealogy();
}
