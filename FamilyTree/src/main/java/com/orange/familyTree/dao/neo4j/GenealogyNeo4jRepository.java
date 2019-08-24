package com.orange.familyTree.dao.neo4j;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.orange.familyTree.entity.neo4j.Genealogy;

@Repository
public interface GenealogyNeo4jRepository extends Neo4jRepository<Genealogy, Long>{
	
	// 查询指定用户关注的所有族谱（Neo4j）
	@Query("MATCH (u:User)-[r:FOCUS_ON]-(g:Genealogy) \n" +
			"WHERE u.userId = {userId} AND r.show = true \n" +
			"RETURN g.name")
	List<String> findAllGenealogy(@Param("userId") Long userId);

	// 查询关注指定图谱的所有用户的ID（Neo4j）
	@Query("MATCH (u:User)-[r:FOCUS_ON]-(g:Genealogy) \n" +
			"WHERE g.name = {name} AND r.show = true \n" +
			"RETURN u.userId")
	List<Long> findGenealogyFollowersByName(@Param("name") String genealogyName);

	@Query("MATCH (u:User)-[r:FOCUS_ON]-(g:Genealogy) \n" +
			"WHERE g.name = {name} AND r.show = true  AND r.admin = true \n" +
			"RETURN u.userId")
	List<Long> findGenealogyAdminsByName(@Param("name") String genealogyName);
	
	// 创建族谱,创建者自动关注族谱并且拥有管理权（Neo4j）
	@Query("MATCH (u:User) \n" +
			"WHERE u.id = {adminId} \n" +
			"CREATE (g:Genealogy{name: {genealogyName},genealogyId: {genealogyId}}) \n" +
			"CREATE (u)-[:FOCUS_ON{admin: true,show: true}]->(g)")
	void createGenealogy(@Param("genealogyName") String genealogyName, @Param("genealogyId") Long genealogyId,
						 @Param("adminId") Long adminId);

	// 增设图谱管理员（Neo4j）
	@Query("MATCH (g:Genealogy)<-[r:FOCUS_ON{show: true, admin: false}]-(u:User) \n" +
			"WHERE g.genealogyId = {genealogyId} AND u.userId = {newAdminId} \n" +
			"SET r.admin = true")
	void setNewAdmin(@Param("genealogyId") Long genealogyId, @Param("newAdminId") Long newAdminId);

	// 转让图谱管理员（Neo4j）
	@Query("MATCH (u1:User)-[r1:FOCUS_ON]->(g:Genealogy)<-[r2:FOCUS_ON]-(u2:User) \n" +
			"WHERE u1.userId = {oldAdminId} AND u2.userId = {newAdminId} AND g.genealogyId = {genealogyId} " +
			"AND r1.show = r2.show = true AND r1.admin = true AND r2.admin = false \n" +
			"SET r1.admin = false \n" +
			"SET r2.admin = true")
	void transferAdmin(@Param("genealogyId") Long genealogyId, @Param("oldAdminId") Long oldAdminId,
					   @Param("newAdminId") Long newAdminId);
}