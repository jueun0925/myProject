package com.hk.board.dtos;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias(value = "userDto")
public class UserDto {
	private int seq;
	private String id;
	private String name;
	private String password;
	private String email;
	private String address;
	private String enabled;
	private String role;
	private Date regdate;
	
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(int seq, String id, String name, String password, String email, String address, String enabled,
			String role, Date regdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.address = address;
		this.enabled = enabled;
		this.role = role;
		this.regdate = regdate;
	}

	
	
	public UserDto(String id,String role,String enabled) {
		super();
		this.id = id;
		this.role = role;
		this.enabled = enabled;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}
