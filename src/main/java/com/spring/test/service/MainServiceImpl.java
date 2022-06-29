package com.spring.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.test.dao.MainDao;
import com.spring.test.dto.BoardDto;
import com.spring.test.dto.MemberDto;
import com.spring.test.page.Paging;

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
		
		//jsp에서 넘긴 id와 pwd 값을 dto에 담는다
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
		System.out.println("로그인 처리 selectCnt : " + selectCnt);
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
		
		//
		String pageNum = req.getParameter("pageNum");
		Paging paging = new Paging(pageNum);
		int total = dao.boardCnt();
		System.out.println("total : " + total);
		
		paging.setTotalCount(total);
		
		int start = paging.getStartRow()-1;
		int end = paging.getEndRow();
		
		List<BoardDto> list = null;
		if(total>0) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("start", start);
			map.put("end", end);
			list=dao.boardList(map);
		}
		System.out.println("start : " + start);
		System.out.println("end : " + end);
		System.out.println("list : " + list);
				
		//list를 jsp로 전달
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("total", total);
	}

	//게시판 등록 처리
	@Override
	public void boardInsertAction(HttpServletRequest req, Model model) {
		System.out.println("서비스 - 게시판 등록 처리");
		
		//dto 바구니 생성
		BoardDto dto = new BoardDto();
		
		//화면에서 입력받은 값을 dto에 담기
		dto.setBoard_title(req.getParameter("board_title"));
		dto.setBoard_writer(req.getParameter("board_writer"));
		dto.setBoard_contents(req.getParameter("board_contents"));
		
		System.out.println("파라미터값 title : " + req.getParameter("board_title"));
		System.out.println("파라미터값 writer : " + req.getParameter("board_writer"));
		System.out.println("파라미터값 contents : " + req.getParameter("board_contents"));
		
		//dao를 통해 db에 저장
		int insertCnt = dao.boardInsertAction(dto);
		System.out.println("insertCnt : " + insertCnt);
		
		//jsp로 처리 결과 전달
		model.addAttribute("insertCnt", insertCnt);
	}

	//게시판 상세 조회
	@Override
	public void boardDetail(HttpServletRequest req, Model model) {
		System.out.println("서비스 - 게시판 상세 조회");
		
		//화면에서 게시글 번호 받아오기
		String pageNum = req.getParameter("crtPage");
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		System.out.println("게시글 번호 : " + board_no);
		System.out.println("pageNum : " + pageNum);
		
		//상세 클릭할 때마다 조회수 증가
		dao.plusReadCnt(board_no);
		
		//받아온 게시글 번호를 통해 db에 저장된 값을 불러온다
		BoardDto dto = dao.boardDetail(board_no);
		System.out.println("상세 dto : " + dto);
		
		//불러온 값을 jsp에 전달한다
		model.addAttribute("dto", dto);
		model.addAttribute("crtPage", pageNum);
	}

	//게시판 수정 화면
	@Override
	public void boardUpdate(HttpServletRequest req, Model model) {
		System.out.println("서비스 - 게시판 수정 화면");
		
		//화면에서 게시글 번호 받아오기
		String pageNum = req.getParameter("crtPage");
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		System.out.println("게시글 번호 : " + board_no);
		System.out.println("pageNum : " + pageNum);
		
		//받아온 게시글 번호를 통해 db에 저장된 값을 불러온다
		BoardDto dto = dao.boardUpdate(board_no);
		System.out.println("수정 dto : " + dto);
		
		//불러온 값을 jsp에 전달한다
		model.addAttribute("dto", dto);
		model.addAttribute("crtPage", pageNum);
	}
	
	//게시판 수정 처리
	@Override
	public void boardUpdateAction(HttpServletRequest req, Model model) {
		System.out.println("서비스 - 게시판 수정 처리");
		
		//dto 주머니 생성
		BoardDto dto = new BoardDto();
		
		//화면에 입력된 값 불러와서 dto에 담기
		String pageNum = req.getParameter("crtPage");
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		dto.setBoard_title(req.getParameter("board_title"));
		dto.setBoard_contents(req.getParameter("board_contents"));
		
		System.out.println("게시글번호 파라미터 : " + Integer.parseInt(req.getParameter("board_no")));
		System.out.println("제목 파라미터 : " + req.getParameter("board_title"));
		System.out.println("내용 파라미터 : " + req.getParameter("board_contents"));
		
		//dao를 통해 db에 저장
		int updateCnt = dao.boardUpdateAction(dto);
		System.out.println("updateCnt : " + updateCnt);
		System.out.println("pageNum : " + pageNum);
		System.out.println("board_no : " + board_no);
		
		//jsp로 결과 전달
		model.addAttribute("updateCnt", updateCnt);
		model.addAttribute("crtPage", pageNum);
		model.addAttribute("board_no", board_no);
	}

	//게시판 삭제 처리
	@Override
	public void boardDeleteAction(HttpServletRequest req, Model model) {
		System.out.println("서비스 - 게시판 삭제 처리");
		
		//화면에서 게시글 번호 받아오기
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		System.out.println("게시글 번호 : " + board_no);
		
		//받아온 게시글 번호를 통해 db에 보낸다
		int deleteCnt = dao.boardDeleteAction(board_no);
		System.out.println("삭제 deleteCnt : " + deleteCnt);
		
		//불러온 값을 jsp에 전달한다
		model.addAttribute("deleteCnt", deleteCnt);
	}
	//게시판 검색
	@Override
	public void boardSearch(HttpServletRequest req, Model model) {
		System.out.println("서비스 - 게시판 검색");
		
		//화면에서 검색어 입력 받아오기
		String searchContent = req.getParameter("searchContent");
		System.out.println("searchContent : " + searchContent);
		
		//dao를 통해 검색값을 list에 담기
		List<BoardDto> list = dao.boardSearch(searchContent);
		System.out.println("list : " + list);
		
		//결과값 jsp에 전달
		model.addAttribute("list", list);
	}


}
