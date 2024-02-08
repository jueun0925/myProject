package com.hk.board.command;

import jakarta.validation.constraints.NotBlank;

public class InsertAskCommand {
	private String id;
	
	@NotBlank(message="제목을 입력하세요")
	private String ask_title;
	
	@NotBlank(message="내용을 입력하세요")
	private String ask_content;

	public InsertAskCommand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InsertAskCommand(String id, @NotBlank(message = "제목을 입력하세요") String ask_title,
			@NotBlank(message = "내용을 입력하세요") String ask_content) {
		super();
		this.id = id;
		this.ask_title = ask_title;
		this.ask_content = ask_content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		return "InsertAskCommand [id=" + id + ", ask_title=" + ask_title + ", ask_content=" + ask_content + "]";
	}
	
	
}
