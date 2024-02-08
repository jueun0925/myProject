package com.hk.motong.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.hk.motong.dtos.AccountDto;


@Mapper
public interface BankMapper {
   
	public AccountDto getmoimAccount(int account_seq);

}