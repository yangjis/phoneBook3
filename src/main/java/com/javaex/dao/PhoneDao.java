package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {

	@Autowired
	private SqlSession sqlSession;

	// 사람 리스트(검색안할때)
	public List<PersonVo> getPersonList() {
		return getPersonList("");
	}

	// 사람 리스트(검색할때)
	public List<PersonVo> getPersonList(String keword) {

		List<PersonVo> pList = sqlSession.selectList("phonebook.selectList");
		return pList;

	}
	
	//수정폼에 뿌려주기 위한 데이터
	public PersonVo getData(int person_id) {
		PersonVo vo = sqlSession.selectOne("phonebook.getData", person_id);
		return vo;
	}

	// 사람 추가
	public int personInsert(PersonVo personVo) {
		int count;
		count = sqlSession.insert("phonebook.insert", personVo);
		System.out.println(count);
		return count;
	}

	// 사람 추가2
	//프레임워크 중 맵을 사용해보자.
	public int personInsert2(String name, String hp, String company) {
		int count;

		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("name", name);
		pMap.put("hp", hp);
		pMap.put("company", company);

		count = sqlSession.insert("phonebook.insert2", pMap);
		return count;
	}

	// 사람 수정
	public int personUpdate(PersonVo personVo) {
		int count = 0;
		count = sqlSession.update("phonebook.update", personVo);
		return count;
	}

	// 사람 삭제
	public int personDelet(int person_id) { 
		  int count = sqlSession.delete("phonebook.delete",person_id);
		  return count; 
	 }

}
