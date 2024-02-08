package com.hk.motong.dtos;

public class AccountDto {
	private int account_seq;
	private int user_seq;
	private String fintech_use_num;
	private String account_num_masked;
	private int balance_amt;
	private String bank_name;
	private String delflag;
	public AccountDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountDto(int account_seq, int user_seq, String fintech_use_num, String account_num_masked, int balance_amt,
			String bank_name, String delflag) {
		super();
		this.account_seq = account_seq;
		this.user_seq = user_seq;
		this.fintech_use_num = fintech_use_num;
		this.account_num_masked = account_num_masked;
		this.balance_amt = balance_amt;
		this.bank_name = bank_name;
		this.delflag = delflag;
	}
	public int getAccount_seq() {
		return account_seq;
	}
	public void setAccount_seq(int account_seq) {
		this.account_seq = account_seq;
	}
	public int getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}
	public String getFintech_use_num() {
		return fintech_use_num;
	}
	public void setFintech_use_num(String fintech_use_num) {
		this.fintech_use_num = fintech_use_num;
	}
	public String getAccount_num_masked() {
		return account_num_masked;
	}
	public void setAccount_num_masked(String account_num_masked) {
		this.account_num_masked = account_num_masked;
	}
	public int getBalance_amt() {
		return balance_amt;
	}
	public void setBalance_amt(int balance_amt) {
		this.balance_amt = balance_amt;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	@Override
	public String toString() {
		return "AccountDto [account_seq=" + account_seq + ", user_seq=" + user_seq + ", fintech_use_num="
				+ fintech_use_num + ", account_num_masked=" + account_num_masked + ", balance_amt=" + balance_amt
				+ ", bank_name=" + bank_name + ", delflag=" + delflag + "]";
	}
	
	
}