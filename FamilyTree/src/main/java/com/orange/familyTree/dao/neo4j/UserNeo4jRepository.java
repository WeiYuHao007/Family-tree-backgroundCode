package com.orange.familyTree.dao.neo4j;

import com.orange.familyTree.entity.neo4j.UserNeo4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.ArrayList;

public interface UserNeo4jRepository extends Neo4jRepository<UserNeo4j, Long> {

    // 创建User节点（Neo4j）
    @Query("CREATE (u:UserMySQL) \n" +
            "SET u.userId = {userId}")
    void createUser(@Param("userId") Long userId);

    // 销毁User节点（Neo4j）
    @Query("MATCH (u:UserMySQL) \n" +
            "WHERE u.userId = {userId} \n" +
            "DETACH DELETE u")
    void destroyUser(@Param("userId") Long userId);

    // 同意关注指定名称的图谱的请求（Neo4j）
    @Query("MATCH (u:UserMySQL)-[r:FOCUS_ON]->(g:GenealogyMySQL) \n" +
            "WHERE u.userId = {userId} AND g.genealogyId = {genealogyId} " +
            "AND r.show = false AND r.admin = false \n" +
            "SET r.show = true")
    void focusOnGenealogy(@Param("userId") Long userId, @Param("genealogyId") Long genealogyId);

    // 取消关注指定图谱（Neo4j）
    @Query("MATCH (u:UserMySQL)-[r:FOCUS_ON]->(g:GenealogyMySQL) \n" +
            "WHERE u.userId = {userId} AND g.genealogyId = {genealogyId} " +
            "AND r.show = true AND r.admin = false \n" +
            "REMOVE r")
    void cancelFocusOnGenealogy(@Param("genealogyId") Long genealogyId, @Param("userId") Long userId);

    // 申请关注指定图谱（Neo4j）
    @Query("MATCH (u:UserMySQL),(g:GenealogyMySQL) \n" +
            "WHERE u.userId = {userId} AND g.genealogyId = {genealogyId} \n" +
            "CREATE (u)-[r:FOCUS_ON{show: false, admin: false}]->(g)")
    void applyForFocusOnGenealogy(@Param("genealogyId") Long genealogyId, @Param("userId") Long userId);

    // 查询用户关注的所有图谱ID
    @Query("MATCH (u:UserMySQL)-[r:FOCUS_ON{ show:true }]->(g:GenealogyMySQL) \n" +
            "WHERE u.userId = {userId} \n" +
            "RETURN g.genealogyId")
    ArrayList<Long> findAllFocusGenealogy(@Param("userId") Long userId);
}
