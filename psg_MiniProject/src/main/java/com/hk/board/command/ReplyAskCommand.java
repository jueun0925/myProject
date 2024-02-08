package com.hk.board.command;

import jakarta.validation.constraints.NotBlank;

public class ReplyAskCommand {
	private String id;
	private int ask_seq;
	private int ask_refer;
	private int ask_depth;
	private int ask_step;
	
	@NotBlank(message="제목을 입력하세요")
	private String ask_rTitle;
	@NotBlank(message="내용을 입력하세요")
	private String ask_rContent;
	public ReplyAskCommand() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReplyAskCommand(String id, int ask_seq, int ask_refer, int ask_depth, int ask_step,
			@NotBlank(message = "제목을 입력하세요") String ask_rTitle, @NotBlank(message = "내용을 입력하세요") String ask_rContent) {
		super();
		this.id = id;
		this.ask_seq = ask_seq;
		this.ask_refer = ask_refer;
		this.ask_depth = ask_depth;
		this.ask_step = ask_step;
		this.ask_rTitle = ask_rTitle;
		this.ask_rContent = ask_rContent;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public int getAsk_depth() {
		return ask_depth;
	}
	public void setAsk_depth(int ask_depth) {
		this.ask_depth = ask_depth;
	}
	public int getAsk_step() {
		return ask_step;
	}
	public void setAsk_step(int ask_step) {
		this.ask_step = ask_step;
	}
	public String getAsk_rTitle() {
		return ask_rTitle;
	}
	public void setAsk_rTitle(String ask_rTitle) {
		this.ask_rTitle = ask_rTitle;
	}
	public String getAsk_rContent() {
		return ask_rContent;
	}
	public void setAsk_rContent(String ask_rContent) {
		this.ask_rContent = ask_rContent;
	}
	@Override
	public String toString() {
		return "ReplyAskCommand [id=" + id + ", ask_seq=" + ask_seq + ", ask_refer=" + ask_refer + ", ask_depth="
				+ ask_depth + ", ask_step=" + ask_step + ", ask_rTitle=" + ask_rTitle + ", ask_rContent=" + ask_rContent
				+ "]";
	}
	
	
}
