package com.orange.familyTree.dao.neo4j;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.orange.familyTree.entity.neo4j.Person;

@Repository
public interface PersonNeo4jRepository extends Neo4jRepository<Person, Long>{

	// 查询指定节点的儿子（Neo4j）
	@Query("MATCH (g:Genealogy)-[:OWNS]->(p1:Person)<-[:IS_SON]-(p2:Person) \r\n" +
			"WHERE g.name = {genealogyName} AND p1.name = {fartherName} \r\n" + 
			"RETURN p2.name")
	List<String> findSons(@Param("genealogyName") String genealogyName,
			@Param("fartherName") String fartherName);
	
	// 查询指定节点的妻子与女儿（Neo4j）
	@Query("MATCH (g)-[:OWNS]->(p1:Person)\r \n" + 
			"WHERE g.name = {genealogyName} AND p1.name = {sourcePersonName} \r \n" + 
			"MATCH (p1)<-[r]-(p2:Person)\r \n" + 
			"WHERE type(r) = 'IS_WIFE' OR type(r) = 'IS_DAUGHTER'\r \n" + 
			"RETURN p2.name")
	List<String> findWifeAndDaughter(@Param("genealogyName") String genealogyName,
			@Param("sourcePersonName") String sourcePersonName);
	
	// 查询两个相邻节点间的关系（Neo4j）
	@Query("MATCH (p1:Person)<-[:OWNS]-(g:Genealogy)-[:OWNS]->(p2:Person), (p1)-[r]->(p2) \r\n" + 
			"WHERE p1.name = {startPerson} AND p2.name = {endPerson} AND g.name = {genealogyName} \r\n" + 
			"RETURN r.relationshipName")
	String findRelationship(@Param("genealogyName") String genealogyName, 
			@Param("startPerson") String startPersonName, @Param("endPerson") String endPersonName);
	
	// 查询两节点之间的最短路径（路径最长为九）（Neo4j）
	@Query("MATCH(p2)<-[:OWNS]-(g)-[:OWNS]->(p1)\r \n" + 
			"WHERE g.name = {genealogyName} AND p1.name = {startPerson} AND p2.name = {endPerson}\r \n" + 
			"WITH p1,p2 MATCH p = shortestpath((p1)-[*..9]-(p2))\r \n" + 
			"WHERE none(r in relationships(p) WHERE type(r) = 'OWNS')\r \n" + 
			"UNWIND [p in nodes(p) | p.name] AS names\r \n" + 
			"RETURN names")
	List<String> findShortPathNodes(@Param("genealogyName") String genealogyName, 
			@Param("startPerson") String startPersonName, @Param("endPerson") String endPersonName);

	// 创建Person节点（Neo4j）
	@Query("MATCH (g:Genealogy) \n" +
			"WHERE g.name = {genealogyName} \n" +
			"CREATE (p:Person) \n" +
			"SET p.name = {personName} \n" +
			"SET p.deathTime = {deathTime} \n" +
			"SET p.birthTime = {birthTime} \n" +
			"SET p.majorAchievements = {majorAchievements} \n" +
			"CREATE (g)-[:OWNS]->(p:Person)")
	void createPerson(@Param("genealogyName") String genealogyName, @Param("personName") String name,
					  @Param("deathTime") String deathTime, @Param("birthTime") String birthTime,
					  @Param("majorAchievements") String majorAchievements);

	// 删除Person节点（Neo4j）
	@Query("MATCH (g:Genealogy)-[:OWNS]->(p:Person)\n" +
			"WHERE g.name = {genealogyName} AND p.name = {personName}\n" +
			"DETACH DELETE p")
	void deletePerson(@Param("genealogyName") String genealogyName, @Param("personName") String personName);

	// 创建关系（父母 <-儿子）（Neo4j）
	@Query("MATCH (p1:Person)<-[:OWNS]-(g:Genealogy)-[:OWNS]->(p2:Person) \n" +
			"WHERE g.name = {genealogyName} AND p1.name = {parent} AND p2.name = {son} \n" +
			"MERGE (p1)<-[:IS_SON{relationshipName: '儿子'}]-(p2)")
	void createSonRelationship(@Param("genealogyName") String genealogyName, @Param("parent") String parent,
										@Param("son") String son);

	// 创建关系（父母<-女儿）（Neo4j）
	@Query("MATCH (p1:Person)<-[:OWNS]-(g:Genealogy)-[:OWNS]->(p2:Person) \n" +
			"WHERE g.name = {genealogyName} AND p1.name = {parent} AND p2.name = {daughter} \n" +
			"MERGE (p1)<-[:IS_DAUGHTER{relationshipName: '女儿'}]-(p2) \n")
	void createDaughterRelationship(@Param("genealogyName") String genealogyName, @Param("parent") String parent,
											 @Param("daughter") String daughter);

	// 创建关系（父亲 ->儿女）（Neo4j）
	@Query("MATCH (p1:Person)<-[:OWNS]-(g:Genealogy)-[:OWNS]->(p2:Person) \n" +
			"WHERE g.name = {genealogyName} AND p1.name = {farther} AND p2.name = {children} \n" +
			"MERGE (p1)-[:IS_FARTHER{relationshipName: '父亲'}]->(p2)")
	void createFartherRelationship(@Param("genealogyName") String genealogyName, @Param("farther") String farther,
			@Param("children") String children);

	// 创建关系（母亲 ->儿女）（Neo4j）
	@Query("MATCH (p1:Person)<-[:OWNS]-(g:Genealogy)-[:OWNS]->(p2:Person) \n" +
			"WHERE g.name = {genealogyName} AND p1.name = {mother} AND p2.name = {children} \n" +
			"MERGE (p1)-[:IS_MOTHER{relationshipName: '母亲'}]->(p2)")
	void createMotherRelationship(@Param("genealogyName") String genealogyName, @Param("mother") String mother,
			@Param("children") String children);

	// 创建关系（丈夫<->妻子）（Neo4j）
	@Query("MATCH (p1:Person)<-[:OWNS]-(g:Genealogy)-[:OWNS]->(p2:Person) \n" +
			"WHERE g.name = {genealogyName} AND p1.name = {husband} AND p2.name = {wife} \n" +
			"MERGE (p1)-[:IS_HUSBAND{relationshipName: '丈夫'}]->(p2), \n" +
			"(p1)<-[:IS_WIFE{relationshipName: '妻子'}]-(p2)")
	void createManToWifeRelationship(@Param("genealogyName") String genealogyName, @Param("husband") String husband,
									 @Param("wife") String wife);

	// 创建关系（兄弟 <-> 兄弟）（Neo4j）
	@Query("MATCH (p1:Person)<-[:OWNS]-(g:Genealogy)-[:OWNS]->(p2:Person)\n" +
			"WHERE g.name = {genealogyName} AND p1.name = {firstPerson} AND p2.name = {endPerson}\n" +
			"MERGE (p1)-[:IS_BROTHER{relationshipName: '兄弟'}]->(p2)," +
			"(p1)<-[:IS_BROTHER{relationshipName: '兄弟'}]-(p2)")
	void createBrotherRelationship(@Param("genealogyName") String genealogyName, @Param("firstPerson") String firstPerson,
								   @Param("endPerson") String endPerson);

	// 删除关系（Neo4j）
	@Query("MATCH (p1:Person)<-[:OWNS]-(g:Genealogy)-[:OWNS]->(p2:Person), \r\n" + 
			"(p1)-[r1]->(p2),(p1)<-[r2]-(p2) \r\n" + 
			"WHERE g.name = {genealogyName} AND p1.name = {firstPerson} AND p2.name = {endPerson} \r\n" + 
			"DELETE r1,r2")
	void deleteRelationship(@Param("genealogyName") String genealogyName, @Param("firstPerson") String firstPerson,
			   @Param("endPerson") String endPerson);
}
