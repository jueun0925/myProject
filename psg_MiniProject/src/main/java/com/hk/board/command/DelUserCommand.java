package com.hk.board.command;

import jakarta.validation.constraints.NotEmpty;

public class DelUserCommand {
	@NotEmpty(message = "최소 하나 이상 체크해야 합니다.")
	private String[] chks;

	public DelUserCommand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DelUserCommand(@NotEmpty(message = "최소 하나 이상 체크해야 합니다.") String[] chks) {
		super();
		this.chks = chks;
	}

	public String[] getChks() {
		return chks;
	}

	public void setChks(String[] chks) {
		this.chks = chks;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}
