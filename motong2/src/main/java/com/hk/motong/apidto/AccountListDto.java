package com.hk.motong.apidto;

import java.util.List;

public class AccountListDto {
	private String api_tran_id;
	private String api_tran_dtm;
	private String rsp_code;
	private String user_name;
	private String res_cnt;
	private List<AccountDto> res_list;
	public AccountListDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountListDto(String api_tran_id, String api_tran_dtm, String rsp_code, String user_name, String res_cnt,
			List<AccountDto> res_list) {
		super();
		this.api_tran_id = api_tran_id;
		this.api_tran_dtm = api_tran_dtm;
		this.rsp_code = rsp_code;
		this.user_name = user_name;
		this.res_cnt = res_cnt;
		this.res_list = res_list;
	}
	public String getApi_tran_id() {
		return api_tran_id;
	}
	public void setApi_tran_id(String api_tran_id) {
		this.api_tran_id = api_tran_id;
	}
	public String getApi_tran_dtm() {
		return api_tran_dtm;
	}
	public void setApi_tran_dtm(String api_tran_dtm) {
		this.api_tran_dtm = api_tran_dtm;
	}
	public String getRsp_code() {
		return rsp_code;
	}
	public void setRsp_code(String rsp_code) {
		this.rsp_code = rsp_code;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getRes_cnt() {
		return res_cnt;
	}
	public void setRes_cnt(String res_cnt) {
		this.res_cnt = res_cnt;
	}
	public List<AccountDto> getRes_list() {
		return res_list;
	}
	public void setRes_list(List<AccountDto> res_list) {
		this.res_list = res_list;
	}
	@Override
	public String toString() {
		return "AccountListDto [api_tran_id=" + api_tran_id + ", api_tran_dtm=" + api_tran_dtm + ", rsp_code="
				+ rsp_code + ", user_name=" + user_name + ", res_cnt=" + res_cnt + ", res_list=" + res_list + "]";
	}
	
	
}
