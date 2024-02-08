package com.hk.motong.dtos;

public class MoimDto { 
   private int moim_seq;
   private int leader;
   private String name;//팀장 이름
   private String email;//팀장 이메일
   private String phone;//팀장 핸드폰번호
   private int account_seq;
   private String mname;
   private String account_num_masked;
   private String bank_name;
   private String delflag; //계좌 사용중인지 여부
   private int pin;
   public MoimDto() {
      super();
      // TODO Auto-generated constructor stub
   }

	public MoimDto(int moim_seq, int leader, String name, String email, String phone, int account_seq, String mname, String account_num_masked,
			String bank_name, String delflag, int pin) {
		super();
		this.moim_seq = moim_seq;
		this.leader = leader;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.account_seq = account_seq;
		this.mname = mname;
		this.account_num_masked = account_num_masked;
		this.bank_name = bank_name;
		this.delflag = delflag;
		this.pin = pin;
	}
	public int getMoim_seq() {
		return moim_seq;
	}
	public void setMoim_seq(int moim_seq) {
		this.moim_seq = moim_seq;
	}
	public int getLeader() {
		return leader;
	}
	public void setLeader(int leader) {
		this.leader = leader;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAccount_seq() {
		return account_seq;
	}
	public void setAccount_seq(int account_seq) {
		this.account_seq = account_seq;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getAccount_num_masked() {
		return account_num_masked;
	}
	public void setAccount_num_masked(String account_num_masked) {
		this.account_num_masked = account_num_masked;
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
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "MoimDto [moim_seq=" + moim_seq + ", leader=" + leader + ", name=" + name + ", email=" + email
				+ ", phone=" + phone + ", account_seq=" + account_seq + ", mname=" + mname + ", account_num_masked="
				+ account_num_masked + ", bank_name=" + bank_name + ", delflag=" + delflag + ", pin=" + pin + "]";
	}
	
	

}