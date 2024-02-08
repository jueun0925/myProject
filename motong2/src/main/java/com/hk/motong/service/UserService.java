package com.hk.motong.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.hk.motong.dtos.AccountDto;
import com.hk.motong.dtos.UserDto;
import com.hk.motong.mapper.UserMapper;


@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	
	public boolean addUser(UserDto dto) {
		int count=userMapper.addUser(dto);
		return count>0?true:false;
	}
	
	public String idChk(String email) {
		return userMapper.idChk(email);
	}
	
	public UserDto loginUser(UserDto dto) {
		return userMapper.loginUser(dto);
	}
	
	public UserDto myInfo(String email) {
		return userMapper.myInfo(email);
	}
	
	public boolean updateMyInfo(UserDto dto) {
		return userMapper.updateMyInfo(dto);
	}
	
	public boolean delUser(String email) {
		return userMapper.delUser(email);
	}
	
	@Transactional(propagation =  Propagation.REQUIRED) //선언적
	public boolean registTokenAccount(UserDto dto, AccountDto adto) {
		int count =0 ;
		
		userMapper.registToken(dto);
		userMapper.registAccount(adto);
		
		return count>0?true:false;
	}

	
	public boolean registAccount(AccountDto adto) {
		return userMapper.registAccount(adto);
	}
	
	//계좌 리스트 불러오기
	public List<AccountDto> getAccountList(int user_seq){
		
		return userMapper.getAccountList(user_seq);
	}
	
	//잔액 업데이트
	public boolean updateBalanceAmt(Map<String, Integer> map) {
		return userMapper.updateBalanceAmt(map);
	}
	
	//계좌 삭제
	public boolean deleteAccount(int account_seq) {
		return userMapper.deleteAccount(account_seq);
	}
}