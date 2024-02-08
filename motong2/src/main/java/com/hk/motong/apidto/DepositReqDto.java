
package com.hk.motong.apidto;

import java.util.List;


public class DepositReqDto {
	private String cntr_account_type;
	private String cntr_account_num;
	private String wd_pass_phrase;
    private String wd_print_content;
	private String name_check_option;
	private String tran_dtime;
	private String req_cnt;
	private List<DepositReqListDto> req_list;
	public DepositReqDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DepositReqDto(String cntr_account_type, String cntr_account_num, String wd_pass_phrase,
			String wd_print_content, String name_check_option, String tran_dtime, String req_cnt,
			List<DepositReqListDto> req_list) {
		super();
		this.cntr_account_type = cntr_account_type;
		this.cntr_account_num = cntr_account_num;
		this.wd_pass_phrase = wd_pass_phrase;
		this.wd_print_content = wd_print_content;
		this.name_check_option = name_check_option;
		this.tran_dtime = tran_dtime;
		this.req_cnt = req_cnt;
		this.req_list = req_list;
	}
	public String getCntr_account_type() {
		return cntr_account_type;
	}
	public void setCntr_account_type(String cntr_account_type) {
		this.cntr_account_type = cntr_account_type;
	}
	public String getCntr_account_num() {
		return cntr_account_num;
	}
	public void setCntr_account_num(String cntr_account_num) {
		this.cntr_account_num = cntr_account_num;
	}
	public String getWd_pass_phrase() {
		return wd_pass_phrase;
	}
	public void setWd_pass_phrase(String wd_pass_phrase) {
		this.wd_pass_phrase = wd_pass_phrase;
	}
	public String getWd_print_content() {
		return wd_print_content;
	}
	public void setWd_print_content(String wd_print_content) {
		this.wd_print_content = wd_print_content;
	}
	public String getName_check_option() {
		return name_check_option;
	}
	public void setName_check_option(String name_check_option) {
		this.name_check_option = name_check_option;
	}
	public String getTran_dtime() {
		return tran_dtime;
	}
	public void setTran_dtime(String tran_dtime) {
		this.tran_dtime = tran_dtime;
	}
	public String getReq_cnt() {
		return req_cnt;
	}
	public void setReq_cnt(String req_cnt) {
		this.req_cnt = req_cnt;
	}
	public List<DepositReqListDto> getReq_list() {
		return req_list;
	}
	public void setReq_list(List<DepositReqListDto> req_list) {
		this.req_list = req_list;
	}
	@Override
	public String toString() {
		return "DepositReqDto [cntr_account_type=" + cntr_account_type + ", cntr_account_num=" + cntr_account_num
				+ ", wd_pass_phrase=" + wd_pass_phrase + ", wd_print_content=" + wd_print_content
				+ ", name_check_option=" + name_check_option + ", tran_dtime=" + tran_dtime + ", req_cnt=" + req_cnt
				+ ", req_list=" + req_list + "]";
	}
	
	
}