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

	// 수정폼에 뿌려주기 위한 데이터
	public PersonVo getData(int person_id) {
		PersonVo vo = sqlSession.selectOne("phonebook.getData", person_id);
		return vo;
	}

	// 수정폼에 뿌려주기 위한 데이터
	//데이터를 맵으로 받아서 화면단에 뿌려보자
	public Map<String, Object> getData2(int person_id) {
		Map<String, Object> mapData = sqlSession.selectOne("phonebook.getData2", person_id);
		
		System.out.println(mapData.get("PERSON_ID"));
		System.out.println(mapData.get("NAME"));
		System.out.println(mapData.get("HP"));
		System.out.println(mapData.get("COMPANY"));
		return mapData;
	}

	// 사람 추가
	public int personInsert(PersonVo personVo) {
		int count;
		count = sqlSession.insert("phonebook.insert", personVo);
		System.out.println(count);
		return count;
	}

	// 사람 추가2
	// 프레임워크 중 맵을 사용해보자.
	//우리가 맵의 키값과 데이터값을 넣어줄때는 우리가 지정한 키값을 이용해 데이터를 꺼내면 된다.
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
		int count = sqlSession.delete("phonebook.delete", person_id);
		return count;
	}

}
