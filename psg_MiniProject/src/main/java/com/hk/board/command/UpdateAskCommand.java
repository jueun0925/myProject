package com.hk.board.command;

import jakarta.validation.constraints.NotBlank;

public class UpdateAskCommand {
	private int ask_seq;
	private int ask_refer;
	@NotBlank(message = "제목을 입력하세요")
	private String ask_title;
	@NotBlank(message = "내용을 입력하세요")
	private String ask_content;
	public UpdateAskCommand() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UpdateAskCommand(int ask_seq, int ask_refer, @NotBlank(message = "제목을 입력하세요") String ask_title,
			@NotBlank(message = "내용을 입력하세요") String ask_content) {
		super();
		this.ask_seq = ask_seq;
		this.ask_refer = ask_refer;
		this.ask_title = ask_title;
		this.ask_content = ask_content;
	}
	public int getAsk_seq() {
		return ask_seq;
	}
	public void setAsk_seq(int ask_seq) {
		this.ask_seq = ask_seq;
	}
	public int getAsk_refer() {
		return ask_refer;
	}
	public void setAsk_refer(int ask_refer) {
		this.ask_refer = ask_refer;
	}
	public String getAsk_title() {
		return ask_title;
	}
	public void setAsk_title(String ask_title) {
		this.ask_title = ask_title;
	}
	public String getAsk_content() {
		return ask_content;
	}
	public void setAsk_content(String ask_content) {
		this.ask_content = ask_content;
	}
	@Override
	public String toString() {
		return "UpdateAskCommand [ask_seq=" + ask_seq + ", ask_refer=" + ask_refer + ", ask_title=" + ask_title
				+ ", ask_content=" + ask_content + "]";
	}
	
	
}
