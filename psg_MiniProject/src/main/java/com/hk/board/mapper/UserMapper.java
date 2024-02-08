package com.hk.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.board.dtos.UserDto;

@Mapper
public interface UserMapper {
	//회원가입
	public boolean addUser(UserDto dto);
	
	//아이디 중복체크
	public String idChk(String id);
	
	//로그인
	public UserDto login(String id);
	
	//나의정보
	public UserDto myInfo(String id);
	
	//나의정보 수정
	public boolean updateUser(UserDto dto);
	
	//회원탈퇴
	public boolean deleteUser(String id);
	
	//회원목록 전체조회
	public List<UserDto> getAllList();
	
	//회원 상세조회
	public UserDto userDetail(String id);
	
	//회원등급변경
	public boolean updateUserRole(String id, String role,String enabled);
	
	//회원 비활성화 시키기
	public boolean mulDel(String[] chks);
}
