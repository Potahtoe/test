package com.spring.test.dao;

import java.util.List;
import java.util.Map;

import com.spring.test.dto.BoardDto;
import com.spring.test.dto.MemberDto;

public interface MainDao {
	
	//-------회원가입---------
	//회원가입 처리
	public int signInAction(MemberDto dto);
	
	//-------로그인---------
	//로그인 확인
	public int idPwdCheck(Map<String,Object> map);
	
	//-------게시판---------
	//게시글 수
	public int boardCnt();
	//게시판 목록 조회
	public List<BoardDto> boardList(Map<String,Object> map);
	//게시글 등록 처리
	public int boardInsertAction(BoardDto dto);
	//조회수 증가
	public void plusReadCnt(int board_no);
	//게시판 상세 조회
	public BoardDto boardDetail(int board_no);
	//게시판 수정 화면
	public BoardDto boardUpdate(int board_no);
	//게시판 수정 처리
	public int boardUpdateAction(BoardDto dto);
	//게시판 삭제 처리
	public int boardDeleteAction(int board_no);
	//검색글 수 
	public int searchCnt(String searchContent);
	//게시판 검색
	public List<BoardDto> boardSearch(Map<String,Object> map);
	
}
