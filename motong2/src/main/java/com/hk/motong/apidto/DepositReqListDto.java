package com.hk.motong.apidto;


public class DepositReqListDto {
	private String tran_no;
	private String bank_tran_id;
	private String fintech_use_num;
	private String print_content;
	private String tran_amt;
	private String req_client_fintech_use_num;
	private String req_client_name;
	private String req_client_num;
	private String transfer_purpose;
	public DepositReqListDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DepositReqListDto(String tran_no, String bank_tran_id, String fintech_use_num, String print_content,
			String tran_amt, String req_client_fintech_use_num, String req_client_name, String req_client_num,
			String transfer_purpose) {
		super();
		this.tran_no = tran_no;
		this.bank_tran_id = bank_tran_id;
		this.fintech_use_num = fintech_use_num;
		this.print_content = print_content;
		this.tran_amt = tran_amt;
		this.req_client_fintech_use_num = req_client_fintech_use_num;
		this.req_client_name = req_client_name;
		this.req_client_num = req_client_num;
		this.transfer_purpose = transfer_purpose;
	}
	public String getTran_no() {
		return tran_no;
	}
	public void setTran_no(String tran_no) {
		this.tran_no = tran_no;
	}
	public String getBank_tran_id() {
		return bank_tran_id;
	}
	public void setBank_tran_id(String bank_tran_id) {
		this.bank_tran_id = bank_tran_id;
	}
	public String getFintech_use_num() {
		return fintech_use_num;
	}
	public void setFintech_use_num(String fintech_use_num) {
		this.fintech_use_num = fintech_use_num;
	}
	public String getPrint_content() {
		return print_content;
	}
	public void setPrint_content(String print_content) {
		this.print_content = print_content;
	}
	public String getTran_amt() {
		return tran_amt;
	}
	public void setTran_amt(String tran_amt) {
		this.tran_amt = tran_amt;
	}
	public String getReq_client_fintech_use_num() {
		return req_client_fintech_use_num;
	}
	public void setReq_client_fintech_use_num(String req_client_fintech_use_num) {
		this.req_client_fintech_use_num = req_client_fintech_use_num;
	}
	public String getReq_client_name() {
		return req_client_name;
	}
	public void setReq_client_name(String req_client_name) {
		this.req_client_name = req_client_name;
	}
	public String getReq_client_num() {
		return req_client_num;
	}
	public void setReq_client_num(String req_client_num) {
		this.req_client_num = req_client_num;
	}
	public String getTransfer_purpose() {
		return transfer_purpose;
	}
	public void setTransfer_purpose(String transfer_purpose) {
		this.transfer_purpose = transfer_purpose;
	}
	@Override
	public String toString() {
		return "DepositReqListDto [tran_no=" + tran_no + ", bank_tran_id=" + bank_tran_id + ", fintech_use_num="
				+ fintech_use_num + ", print_content=" + print_content + ", tran_amt=" + tran_amt
				+ ", req_client_fintech_use_num=" + req_client_fintech_use_num + ", req_client_name=" + req_client_name
				+ ", req_client_num=" + req_client_num + ", transfer_purpose=" + transfer_purpose + "]";
	}
	
	
}