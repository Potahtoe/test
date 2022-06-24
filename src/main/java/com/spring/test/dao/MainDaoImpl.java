package com.spring.test.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.test.dto.BoardDto;
import com.spring.test.dto.MemberDto;

@Repository
public class MainDaoImpl implements MainDao{

	@Autowired
	SqlSession sqlSession;
	
	//-----------회원가입-------------
	//회원가입 처리
	@Override
	public int signInAction(MemberDto dto) {
		System.out.println("dao - 회원가입 처리");
		
		//MainDao dao = sqlSession.getMapper(MainDao.class);
		int insertCnt = sqlSession.insert("com.spring.test.dao.MainDao.signInAction", dto);
		System.out.println("insertCnt : " + insertCnt);
		return insertCnt;
	}

	//-----------로그인-------------
	//로그인 확인
	@Override
	public int idPwdCheck(Map<String, Object> map) {
		System.out.println("dao - 로그인 확인");
		
		int selectCnt = sqlSession.selectOne("com.spring.test.dao.MainDao.idPwdCheck", map);
		System.out.println("dao selectCnt : " + selectCnt);
		//MainDao dao = sqlSession.getMapper(MainDao.class);
		return selectCnt;
	}
	
	//-----------게시글-------------
	//게시글 수
	@Override
	public int boardCnt() {
		System.out.println("dao - 게시글 수");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.boardCnt();
	}

	/*
	//게시판 목록 조회
	@Override
	public List<BoardDto> boardList(Map<String, Object> map) {
		System.out.println("dao - 게시판 목록 조회");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		List<BoardDto> list = dao.boardList(map);
		return list;
	}
	*/
	//게시판 목록 조회
	@Override
	public List<BoardDto> boardList() {
		System.out.println("dao - 게시판 목록 조회");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		List<BoardDto> list = dao.boardList();
		return list;
	}

	//게시판 등록 처리
	@Override
	public int boardInsertAction(BoardDto dto) {
		System.out.println("dao - 게시판 등록 처리");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.boardInsertAction(dto);
	}

	//조회수 증가
	@Override
	public void plusReadCnt(int board_no) {
		System.out.println("dao - 조회수 증가");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		dao.plusReadCnt(board_no);
	}

	//게시판 상세 조회
	@Override
	public BoardDto boardDetail(int board_no) {
		System.out.println("dao - 게시판 상세 조회");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.boardDetail(board_no);
	}

	//게시판 수정 화면
	@Override
	public BoardDto boardUpdate(int board_no) {
		System.out.println("dao - 게시판 수정 화면");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		System.out.println("dao :" + dao);
		return dao.boardUpdate(board_no);
	}

	@Override
	public int boardUpdateAction(BoardDto dto) {
		System.out.println("dao - 게시판 수정 처리");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.boardUpdateAction(dto);
	}

	//게시판 삭제 처리
	@Override
	public int boardDeleteAction(int board_no) {
		System.out.println("dao - 게시판 삭제 처리");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.boardDeleteAction(board_no);
	}

	@Override
	public List<BoardDto> boardSearch(String searchContent) {
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.boardSearch(searchContent);
	}

}
