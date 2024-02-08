package com.hk.motong.feignMapper;


import org.json.simple.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.motong.apidto.AccountBalanceDto;
import com.hk.motong.apidto.AccountListDto;
import com.hk.motong.apidto.AccountTransactionListDto;
import com.hk.motong.apidto.DepositReqDto;
import com.hk.motong.apidto.DepositResDto;
import com.hk.motong.apidto.TokenResponseDto;
import com.hk.motong.apidto.UserMeDto;
import com.hk.motong.apidto.UserOobDto;
import com.hk.motong.apidto.WithdrawDto;
import com.hk.motong.apidto.WithdrawReqDto;

//restAPI 서버(openbanking)에 요청하고, 결과값을 받아 주는 기능 
@FeignClient(name = "feign", url = "https://testapi.openbanking.or.kr")
public interface OpenBankingFeign {

	//나의 정보조회
	@GetMapping(path = "/v2.0/user/me")
	public UserMeDto requestUserMe(
			@RequestHeader("Authorization") String access_token,
			@RequestParam("user_seq_no") String user_seq_no
			);
	
	//토큰발급
	@PostMapping(params = "/oauth/v2.0/token",
			     consumes = "application/x-www-form-urlencoded",
			     produces = "application/json")
	public TokenResponseDto requestToken(
//				@RequestParam("code") String code,
//				@RequestParam("client_id") String client_id,
//				@RequestParam("client_secret") String client_secret,
//				@RequestParam("redirect_uri") String redirect_uri,
//				@RequestParam("grant_type") String grant_type 
			);

	@GetMapping(path = "/v2.0/account/list")
	  public AccountListDto requestAccountList(
	          @RequestHeader("Authorization") String access_token,
			  @RequestParam("user_seq_no") String user_seq_no, 
			  @RequestParam("include_cancel_yn") String include_cancel_yn, 
			  @RequestParam("sort_order") String sort_order);
	  
	  @GetMapping(path = "/v2.0/account/balance/fin_num")
	  public AccountBalanceDto requestAccountBalance(
	      @RequestHeader("Authorization") String access_token,
			  @RequestParam("bank_tran_id") String bank_tran_id, 
			  @RequestParam("fintech_use_num") String fintech_use_num, 
			  @RequestParam("tran_dtime") String tran_dtime);

	  @GetMapping(path = "/v2.0/account/transaction_list/fin_num")
	  public AccountTransactionListDto requestAccountTransactionList(
			  @RequestParam("bank_tran_id") String bank_tran_id, 
			  @RequestParam("fintech_use_num") String fintech_use_num, 
			  @RequestParam("inquiry_type") String inquiry_type, 
			  @RequestParam("inquiry_base") String inquiry_base, 
			  @RequestParam("from_date") String from_date, 
			  @RequestParam("to_date") String to_date, 
			  @RequestParam("sort_order") String sort_order, 
			  @RequestParam("tran_dtime") String tran_dtime);

	  //출금
	  @PostMapping(path = "/v2.0/transfer/withdraw/fin_num",produces = "application/json")
	  public WithdrawDto requestWithdraw(
			  @RequestHeader("Authorization") String access_token,
			  @RequestBody WithdrawReqDto reqDto);
	  
	  // 입금
	  @PostMapping(path="/v2.0/transfer/deposit/fin_num",produces = "application/json")
	  public DepositResDto requestDeposit(
			  @RequestHeader("Authorization") String access_token,
			  @RequestBody DepositReqDto drdto);
		  
		  // oob 토큰
	  @PostMapping(path="/oauth/2.0/token")
	  public UserOobDto requestOobToken(
			  @RequestParam("client_id") String client_id,
			  @RequestParam("client_secret")String client_secret,
			  @RequestParam("scope") String scope,
			  @RequestParam("grant_type") String grant_type);

}