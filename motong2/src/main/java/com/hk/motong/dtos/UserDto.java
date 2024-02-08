package com.hk.motong.dtos;

public class UserDto {
	private int user_seq;
	private String email;
	private String password;
	private String name;
	private String phone;
	
	private String useraccesstoken;
	private String userrefreshtoken;
	private int userseqno;
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDto(int user_seq, String email, String password, String name, String phone, String useraccesstoken,
			String userrefreshtoken, int userseqno) {
		super();
		this.user_seq = user_seq;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.useraccesstoken = useraccesstoken;
		this.userrefreshtoken = userrefreshtoken;
		this.userseqno = userseqno;
	}
	public int getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUseraccesstoken() {
		return useraccesstoken;
	}
	public void setUseraccesstoken(String useraccesstoken) {
		this.useraccesstoken = useraccesstoken;
	}
	public String getUserrefreshtoken() {
		return userrefreshtoken;
	}
	public void setUserrefreshtoken(String userrefreshtoken) {
		this.userrefreshtoken = userrefreshtoken;
	}
	public int getUserseqno() {
		return userseqno;
	}
	public void setUserseqno(int userseqno) {
		this.userseqno = userseqno;
	}
	@Override
	public String toString() {
		return "UserDto [user_seq=" + user_seq + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", phone=" + phone + ", useraccesstoken=" + useraccesstoken + ", userrefreshtoken=" + userrefreshtoken
				+ ", userseqno=" + userseqno + "]";
	}
	
	
}