package com.spring.test.dao;

import java.util.List;
import java.util.Map;

import com.spring.test.dto.MemberDto;

public interface MainDao {
	
	//회원가입 처리
	public int signInAction(MemberDto dto);
	
	//로그인 확인
	public int idPwdCheck(Map<String,Object> map);
}
