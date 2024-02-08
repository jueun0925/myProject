package com.hk.motong.apidto;


public class DepositResListDto {
	private String tran_no;
    private String bank_tran_id;
    private String bank_tran_date;
    private String bank_code_tran;
    private String bank_rsp_code;
    private String bank_rsp_message;
    private String fintech_use_num;
    private String account_alias;
    private String bank_code_std;
    private String bank_code_sub;
    private String bank_name;
    private String account_num_masked;
    private String print_content;
    private String account_holder_name;
    private String tran_amt;
    private String cms_num;
    private String savings_bank_name;
    private String withdraw_bank_tran_id;
	public DepositResListDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DepositResListDto(String tran_no, String bank_tran_id, String bank_tran_date, String bank_code_tran,
			String bank_rsp_code, String bank_rsp_message, String fintech_use_num, String account_alias,
			String bank_code_std, String bank_code_sub, String bank_name, String account_num_masked,
			String print_content, String account_holder_name, String tran_amt, String cms_num, String savings_bank_name,
			String withdraw_bank_tran_id) {
		super();
		this.tran_no = tran_no;
		this.bank_tran_id = bank_tran_id;
		this.bank_tran_date = bank_tran_date;
		this.bank_code_tran = bank_code_tran;
		this.bank_rsp_code = bank_rsp_code;
		this.bank_rsp_message = bank_rsp_message;
		this.fintech_use_num = fintech_use_num;
		this.account_alias = account_alias;
		this.bank_code_std = bank_code_std;
		this.bank_code_sub = bank_code_sub;
		this.bank_name = bank_name;
		this.account_num_masked = account_num_masked;
		this.print_content = print_content;
		this.account_holder_name = account_holder_name;
		this.tran_amt = tran_amt;
		this.cms_num = cms_num;
		this.savings_bank_name = savings_bank_name;
		this.withdraw_bank_tran_id = withdraw_bank_tran_id;
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
	public String getBank_tran_date() {
		return bank_tran_date;
	}
	public void setBank_tran_date(String bank_tran_date) {
		this.bank_tran_date = bank_tran_date;
	}
	public String getBank_code_tran() {
		return bank_code_tran;
	}
	public void setBank_code_tran(String bank_code_tran) {
		this.bank_code_tran = bank_code_tran;
	}
	public String getBank_rsp_code() {
		return bank_rsp_code;
	}
	public void setBank_rsp_code(String bank_rsp_code) {
		this.bank_rsp_code = bank_rsp_code;
	}
	public String getBank_rsp_message() {
		return bank_rsp_message;
	}
	public void setBank_rsp_message(String bank_rsp_message) {
		this.bank_rsp_message = bank_rsp_message;
	}
	public String getFintech_use_num() {
		return fintech_use_num;
	}
	public void setFintech_use_num(String fintech_use_num) {
		this.fintech_use_num = fintech_use_num;
	}
	public String getAccount_alias() {
		return account_alias;
	}
	public void setAccount_alias(String account_alias) {
		this.account_alias = account_alias;
	}
	public String getBank_code_std() {
		return bank_code_std;
	}
	public void setBank_code_std(String bank_code_std) {
		this.bank_code_std = bank_code_std;
	}
	public String getBank_code_sub() {
		return bank_code_sub;
	}
	public void setBank_code_sub(String bank_code_sub) {
		this.bank_code_sub = bank_code_sub;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getAccount_num_masked() {
		return account_num_masked;
	}
	public void setAccount_num_masked(String account_num_masked) {
		this.account_num_masked = account_num_masked;
	}
	public String getPrint_content() {
		return print_content;
	}
	public void setPrint_content(String print_content) {
		this.print_content = print_content;
	}
	public String getAccount_holder_name() {
		return account_holder_name;
	}
	public void setAccount_holder_name(String account_holder_name) {
		this.account_holder_name = account_holder_name;
	}
	public String getTran_amt() {
		return tran_amt;
	}
	public void setTran_amt(String tran_amt) {
		this.tran_amt = tran_amt;
	}
	public String getCms_num() {
		return cms_num;
	}
	public void setCms_num(String cms_num) {
		this.cms_num = cms_num;
	}
	public String getSavings_bank_name() {
		return savings_bank_name;
	}
	public void setSavings_bank_name(String savings_bank_name) {
		this.savings_bank_name = savings_bank_name;
	}
	public String getWithdraw_bank_tran_id() {
		return withdraw_bank_tran_id;
	}
	public void setWithdraw_bank_tran_id(String withdraw_bank_tran_id) {
		this.withdraw_bank_tran_id = withdraw_bank_tran_id;
	}
	@Override
	public String toString() {
		return "DepositResListDto [tran_no=" + tran_no + ", bank_tran_id=" + bank_tran_id + ", bank_tran_date="
				+ bank_tran_date + ", bank_code_tran=" + bank_code_tran + ", bank_rsp_code=" + bank_rsp_code
				+ ", bank_rsp_message=" + bank_rsp_message + ", fintech_use_num=" + fintech_use_num + ", account_alias="
				+ account_alias + ", bank_code_std=" + bank_code_std + ", bank_code_sub=" + bank_code_sub
				+ ", bank_name=" + bank_name + ", account_num_masked=" + account_num_masked + ", print_content="
				+ print_content + ", account_holder_name=" + account_holder_name + ", tran_amt=" + tran_amt
				+ ", cms_num=" + cms_num + ", savings_bank_name=" + savings_bank_name + ", withdraw_bank_tran_id="
				+ withdraw_bank_tran_id + "]";
	}
    
    
}