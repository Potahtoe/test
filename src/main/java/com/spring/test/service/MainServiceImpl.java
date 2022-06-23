package com.spring.test.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.test.dao.MainDao;
import com.spring.test.dto.MemberDto;

@Service
public class MainServiceImpl implements MainService{

	@Autowired
	MainDao dao;
	
	//---------------회원가입--------------------
	//회원가입 처리
	@Override
	public void signInAction(HttpServletRequest req, Model model) {
		System.out.println("서비스 - 회원가입 처리");
		
		//dto 바구니 생성
		MemberDto dto = new MemberDto();
		
		//입력된 id와 pwd를 dto에 담아서
		dto.setMem_id(req.getParameter("mem_id"));
		dto.setMem_pwd(req.getParameter("mem_pwd"));
		
		System.out.println("파라미터 mem_id : " + req.getParameter("mem_id"));
		System.out.println("파라미터 mem_pwd : " + req.getParameter("mem_pwd"));
		//dao를 통해 db에 저장
		int insertCnt = dao.signInAction(dto);
		
		System.out.println("insertCnt : " + insertCnt);
		//jsp로 결과 보내주기(signInAction.jsp)
		model.addAttribute("insertCnt", insertCnt);
	}
	
	//---------------로그인--------------------
	//로그인 처리
	@Override
	public void loginAction(HttpServletRequest req, Model model) {
		System.out.println("서비스 - 로그인 처리");
		
		//id와 pwd 입력 받기
		String strId = req.getParameter("mem_id");
		String strPwd = req.getParameter("mem_pwd");
		
		//두 값을 넘기기 위해 hashmap에 담아주기
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("strId", strId);
		map.put("strPwd", strPwd);
		
		System.out.println("map : " + map);
		
		//dao를 통해 db에 있는 id/pwd 정보와 일치하는지 비교하기
		int selectCnt = dao.idPwdCheck(map);
		
		//일치하면 입력된 id를 세션id로 설정해주기
		if(selectCnt==1) {
			HttpSession session = req.getSession();
			session.setAttribute("sessionID", strId);
		}
		
		System.out.println("로그인 처리 selectCnt : " + selectCnt);
		//위에서 구한 값을 jsp로 넘겨주기
		model.addAttribute("selectCnt", selectCnt);
	}
	
	//---------------게시판--------------------
	//게시판 목록 조회
	@Override
	public void boardList(HttpServletRequest req, Model model) {
		System.out.println("서비스 - 게시판 목록 조회");
		
	}

	//게시판 등록 처리
	@Override
	public void boardInsertAction(HttpServletRequest req, Model model) {
		System.out.println("서비스 - 게시판 등록 처리");
		
	}

	//게시판 상세 조회
	@Override
	public void boardDetail(HttpServletRequest req, Model model) {
		System.out.println("서비스 - 게시판 상세 조회");
		
	}

	//게시판 수정 처리
	@Override
	public void boardUpdateAction(HttpServletRequest req, Model model) {
		System.out.println("서비스 - 게시판 수정 처리");
		
	}

	//게시판 삭제 처리
	@Override
	public void boardDeleteAction(HttpServletRequest req, Model model) {
		System.out.println("서비스 - 게시판 삭제 처리");
		
	}
	//게시판 검색
	@Override
	public void boardSearch(HttpServletRequest req, Model model) {
		System.out.println("서비스 - 게시판 검색");
		
	}


}
