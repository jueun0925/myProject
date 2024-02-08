package com.hk.motong.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.hk.motong.dtos.AccountDto;
import com.hk.motong.dtos.UserDto;


@Mapper
public interface UserMapper {
	public int addUser(UserDto dto);
	
	public String idChk(String email);
	
	public UserDto loginUser(UserDto dto);
	
	public UserDto myInfo(String email);
	
	public boolean updateMyInfo(UserDto dto);
	
	public boolean delUser(String email);
	
	//토큰 발급받아서 DB에 저장
	public boolean registToken(UserDto dto);
	//계좌테이블에 계좌 정보 저장
	public boolean registAccount(AccountDto dto);
	
	//계좌 리스트 불러오기
	public List<AccountDto> getAccountList(int user_seq);
	
	//잔액 업데이트
	public boolean updateBalanceAmt(Map<String, Integer> map);
	
	//계좌 삭제
	public boolean deleteAccount(int account_seq);
}