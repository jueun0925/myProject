package com.hk.board.command;

import jakarta.validation.constraints.NotBlank;

public class UpdateUserCommand {
	
	private String id;
	
	@NotBlank(message = "이메일을 입력하세요")
	private String email;
	
	@NotBlank(message = "주소를 입력하세요")
	private String address;

	public UpdateUserCommand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdateUserCommand(String id, @NotBlank(message = "이메일을 입력하세요") String email,
			@NotBlank(message = "주소를 입력하세요") String address) {
		super();
		this.id = id;
		this.email = email;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	
	
	
}
