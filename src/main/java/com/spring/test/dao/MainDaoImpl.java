package com.spring.test.dao;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.test.dto.MemberDto;

@Repository
public class MainDaoImpl implements MainDao{

	@Autowired
	SqlSession sqlSession;
	
	DataSource dataSource;
	
	//회원가입 처리
	@Override
	public int signInAction(MemberDto dto) {
		System.out.println("dao - 회원가입 처리");
		
		//MainDao dao = sqlSession.getMapper(MainDao.class);
		int insertCnt = sqlSession.insert("com.spring.test.dao.MainDao.signInAction", dto);
		System.out.println("insertCnt : " + insertCnt);
		return insertCnt;
	}

	//로그인 확인
	@Override
	public int idPwdCheck(Map<String, Object> map) {
		System.out.println("dao - 로그인 확인");
		
		int selectCnt = sqlSession.selectOne("com.spring.test.dao.MainDao.idPwdCheck", map);
		System.out.println("dao selectCnt : " + selectCnt);
		//MainDao dao = sqlSession.getMapper(MainDao.class);
		return selectCnt;
	}

}
