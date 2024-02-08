package com.hk.motong.apidto;

import java.util.List;

public class DepositResDto {
	private String api_tran_id;
    private String rsp_code;
    private String rsp_message;
    private String api_tran_dtm;
    private String wd_bank_code_std;
    private String wd_bank_code_sub;
    private String wd_bank_name;
    private String wd_account_num_masked;
    private String wd_print_content;
    private String wd_account_holder_name;
    private String res_cnt;
    private List<DepositResListDto> res_list;
	public DepositResDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DepositResDto(String api_tran_id, String rsp_code, String rsp_message, String api_tran_dtm,
			String wd_bank_code_std, String wd_bank_code_sub, String wd_bank_name, String wd_account_num_masked,
			String wd_print_content, String wd_account_holder_name, String res_cnt, List<DepositResListDto> res_list) {
		super();
		this.api_tran_id = api_tran_id;
		this.rsp_code = rsp_code;
		this.rsp_message = rsp_message;
		this.api_tran_dtm = api_tran_dtm;
		this.wd_bank_code_std = wd_bank_code_std;
		this.wd_bank_code_sub = wd_bank_code_sub;
		this.wd_bank_name = wd_bank_name;
		this.wd_account_num_masked = wd_account_num_masked;
		this.wd_print_content = wd_print_content;
		this.wd_account_holder_name = wd_account_holder_name;
		this.res_cnt = res_cnt;
		this.res_list = res_list;
	}
	public String getApi_tran_id() {
		return api_tran_id;
	}
	public void setApi_tran_id(String api_tran_id) {
		this.api_tran_id = api_tran_id;
	}
	public String getRsp_code() {
		return rsp_code;
	}
	public void setRsp_code(String rsp_code) {
		this.rsp_code = rsp_code;
	}
	public String getRsp_message() {
		return rsp_message;
	}
	public void setRsp_message(String rsp_message) {
		this.rsp_message = rsp_message;
	}
	public String getApi_tran_dtm() {
		return api_tran_dtm;
	}
	public void setApi_tran_dtm(String api_tran_dtm) {
		this.api_tran_dtm = api_tran_dtm;
	}
	public String getWd_bank_code_std() {
		return wd_bank_code_std;
	}
	public void setWd_bank_code_std(String wd_bank_code_std) {
		this.wd_bank_code_std = wd_bank_code_std;
	}
	public String getWd_bank_code_sub() {
		return wd_bank_code_sub;
	}
	public void setWd_bank_code_sub(String wd_bank_code_sub) {
		this.wd_bank_code_sub = wd_bank_code_sub;
	}
	public String getWd_bank_name() {
		return wd_bank_name;
	}
	public void setWd_bank_name(String wd_bank_name) {
		this.wd_bank_name = wd_bank_name;
	}
	public String getWd_account_num_masked() {
		return wd_account_num_masked;
	}
	public void setWd_account_num_masked(String wd_account_num_masked) {
		this.wd_account_num_masked = wd_account_num_masked;
	}
	public String getWd_print_content() {
		return wd_print_content;
	}
	public void setWd_print_content(String wd_print_content) {
		this.wd_print_content = wd_print_content;
	}
	public String getWd_account_holder_name() {
		return wd_account_holder_name;
	}
	public void setWd_account_holder_name(String wd_account_holder_name) {
		this.wd_account_holder_name = wd_account_holder_name;
	}
	public String getRes_cnt() {
		return res_cnt;
	}
	public void setRes_cnt(String res_cnt) {
		this.res_cnt = res_cnt;
	}
	public List<DepositResListDto> getRes_list() {
		return res_list;
	}
	public void setRes_list(List<DepositResListDto> res_list) {
		this.res_list = res_list;
	}
	@Override
	public String toString() {
		return "DepositResDto [api_tran_id=" + api_tran_id + ", rsp_code=" + rsp_code + ", rsp_message=" + rsp_message
				+ ", api_tran_dtm=" + api_tran_dtm + ", wd_bank_code_std=" + wd_bank_code_std + ", wd_bank_code_sub="
				+ wd_bank_code_sub + ", wd_bank_name=" + wd_bank_name + ", wd_account_num_masked="
				+ wd_account_num_masked + ", wd_print_content=" + wd_print_content + ", wd_account_holder_name="
				+ wd_account_holder_name + ", res_cnt=" + res_cnt + ", res_list=" + res_list + "]";
	}
    
    
}