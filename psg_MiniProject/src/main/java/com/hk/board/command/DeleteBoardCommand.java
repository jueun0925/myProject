package com.hk.board.command;

import jakarta.validation.constraints.NotEmpty;

public class DeleteBoardCommand {
	//null이거나 길이가 0인 경우
	@NotEmpty(message = "삭제하려면 최소 하나 이상 체크하세요")
	private String[] chk;

	public DeleteBoardCommand() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public DeleteBoardCommand(@NotEmpty(message = "삭제하려면 최소 하나 이상 체크하세요") String[] chk) {
		super();
		this.chk = chk;
	}



	public String[] getChk() {
		return chk;
	}

	public void setChk(String[] chk) {
		this.chk = chk;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}
