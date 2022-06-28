package com.spring.test.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.test.service.MainService;

@Controller
public class MainController {

		private static final Logger logger = LoggerFactory.getLogger(MainController.class);
		
		@Autowired
		MainService service;

		//------------회원가입------------------
		//회원가입 화면
		@RequestMapping("signIn.do")
		public String signIn(Model model) {
			logger.info("회원가입 화면");
			return "member/signIn";
		}
		
		//회원가입 처리
		@RequestMapping("signInAction.do")
		public String signInAction(HttpServletRequest req, Model model) {
			logger.info("회원가입 처리");
			
			service.signInAction(req, model);
			return "member/signInAction";
		}
		
		//회원가입 성공 시
		@RequestMapping("signInSuccess.do")
		public String signInSuccess(HttpServletRequest req, Model model) {
			logger.info("회원가입 성공");
			
			//signInAction.jsp에서 들고 온 값 불러오기
			int cnt = Integer.parseInt(req.getParameter("insertCnt"));
			
			model.addAttribute("selectCnt", cnt);
			
			return "member/login";
		}
		
		//------------로그인------------------
		//로그인 화면
		@RequestMapping("login.do")
		public String login(Model model) {
			logger.info("로그인 화면");
			return "member/login";
		}
		
		//로그인 처리
		@RequestMapping("loginAction.do")
		public String loginAction(HttpServletRequest req, Model model) {
			logger.info("로그인 처리");
			
			service.loginAction(req, model);
			return "member/loginAction";
		}
				
		//------------게시판------------------
		//게시판 목록 조회
		@RequestMapping("boardList.do")
		public String boardList(HttpServletRequest req, Model model) {
			logger.info("게시판 목록 조회");
			
			service.boardList(req, model);
			return "board/boardList";
		}
		//게시판 등록 화면
		@RequestMapping("boardInsert.do")
		public String boardInsert(Model model) {
			logger.info("게시판 등록 화면");
			return "board/boardInsert";
		}
		//게시판 등록 처리
		@RequestMapping("boardInsertAction.do")
		public void boardInsertAction(HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {
			logger.info("게시판 등록 처리");
			
			service.boardInsertAction(req, model);
			String viewPage = req.getContextPath() +"/boardList.do";
			res.sendRedirect(viewPage);
		}
		//게시판 상세 조회
		@RequestMapping("boardDetail.do")
		public String boardDetail(HttpServletRequest req, Model model) {
			logger.info("게시판 상세 조회");
			
			service.boardDetail(req, model);
			return "board/boardDetail";
		}
		//게시판 수정 화면
		@RequestMapping("boardUpdate.do")
		public String boardUpdate(HttpServletRequest req, Model model) {
			logger.info("게시판 수정 화면");
			
			service.boardUpdate(req, model);
			return "board/boardUpdate";
		}
		//게시판 수정 처리
		@RequestMapping("boardUpdateAction.do")
		public String boardUpdateAction(HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {
			logger.info("게시판 수정 처리");
			
			int board_no = Integer.parseInt(req.getParameter("board_no"));
			String pageNum = req.getParameter("pageNum");
			
			service.boardUpdateAction(req, model);
			/*
			 * String viewPage = req.getContextPath() +"/boardList.do";
			 * res.sendRedirect(viewPage);
			 */
			req.setAttribute("board_no", board_no);
			req.setAttribute("pageNum", pageNum);
			return "forward:/boardList";
		}
		
		//게시판 삭제 처리
		@RequestMapping("boardDeleteAction.do")
		public void boardDeleteAction(HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {
			logger.info("게시판 삭제 처리");
			
			service.boardDeleteAction(req, model);
			String viewPage = req.getContextPath() +"/boardList.do";
			res.sendRedirect(viewPage);
		}
		
		//게시판 검색
		@RequestMapping("boardSearch.do")
		public String boardSearch(HttpServletRequest req, Model model) {
			logger.info("게시판 검색");
			
			service.boardSearch(req, model);
			return "board/boardSearch";
		}
}
