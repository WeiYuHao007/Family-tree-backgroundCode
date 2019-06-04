package com.orange.familyTree.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.familyTree.dao.PersonCrudRepository;
import com.orange.familyTree.entity.Person;


@Service
public class PersonServiceImple implements PersonService{
	
	@Autowired
	private PersonCrudRepository personCrudRepository;

	
	//Get
	@Override
	public Person getPerson(String name) {
		/*通过名字获取节点：*/
		Person myPerson = personCrudRepository.findByName(name);
		return myPerson;
	}
	
	@Override
	public List<Person> getPersonRelationshipPath(String startName, String endName) {
		/*获得两个人之间指定关系的路径：
		 * 1.从数据库获得两人的信息。
		 * 2.创建关系。
		 * */
		Person myStartPerson = personCrudRepository.findByName(startName);
		Person myEndPerson = personCrudRepository.findByName(endName);
		List<Person> relationshipPath = personCrudRepository.findPersonRelationshipPath(
				myStartPerson.getName(), myEndPerson.getName()
				);
		/*（猜测和路径上的个数有关，待解决）
		List<Person> myRelationshipPath = new ArrayList<Person>(relationshipPath.size());
		//对列表数据进行整理
		int size = relationshipPath.size();
		int temIndex = relationshipPath.size()/2;
		Person temPerson;
		if(size % 2 != 0 ) {
			//大小是奇数的情况
			for(int i = 0; i < size; i++) {
				//偶数是减
				if(i % 2 != 0) {
					//i是奇数
					temIndex += i;
					temPerson = relationshipPath.get(temIndex);
				}
				else {
					//i是偶数
					temIndex -= i;
					temPerson = relationshipPath.get(temIndex);
				}
				myRelationshipPath.add(temPerson);
			}
		}
		else {
			//大小是偶数的情况
			temIndex -= 1;
			for(int i = 0; i < size; i++) {
				//偶数是减
				if(i % 2 != 0) {
					//i是奇数
					temIndex += i;
					temPerson = relationshipPath.get(temIndex);
				}
				else {
					//i是偶数
					temIndex -= i;
					temPerson = relationshipPath.get(temIndex);
				}
				myRelationshipPath.add(temPerson);
			}
			
		}*/
		return relationshipPath;
	}


	//Delete
	@Override
	public void deletePerson(Person person) {
		/*删除节点：
		 * 1.找人。
		 * 2.没有的话报错。
		 * 3.有的话删除*/
		Person MyPerson = personCrudRepository.findByName(person.getName());
		personCrudRepository.deleteByName(MyPerson.getName());
	}

	@Override
	public void deletePersonRelationship(Person startPerson, String relationship, Person endPerson) {
		/*删除关系：
		 * 1.找起始人。
		 * 2.找断终人。
		 * 3.找关系。
		 * 4.删除关系*/
		Person MyStartPerson = personCrudRepository.findByName(startPerson.getName());
		
	}
	
	
	//Post
	@Override
	public void postPerson(Person person) {
		/* 创建节点：
		 * */
	}
	
	@Override
	public void postPersonRelationship(Person startPerson, String relationship, Person endPerson) {
		/*创建关系:
		 * 1.找起始人。
		 * 2.找断终人。
		 * 3.创建关系。*/
		
	}


	//Put
	@Override
	public Person putPerson(Person modifiedPerson) {
		/*修改节点属性：
		 * 1.找到被修改的人。
		 * 2.进行修改
		 * 4.返回被修改后的人
		 * */
		return null;
	}

}
