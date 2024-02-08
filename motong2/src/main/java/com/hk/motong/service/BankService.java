package com.hk.motong.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.motong.dtos.AccountDto;

import com.hk.motong.mapper.BankMapper;

@Service
public class BankService {
	
	@Autowired
	BankMapper bankMapper;
	
	public AccountDto getmoimAccount(int account_seq) {
		return bankMapper.getmoimAccount(account_seq);

	}
   
}