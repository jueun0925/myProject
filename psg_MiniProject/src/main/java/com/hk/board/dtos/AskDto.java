package com.hk.board.dtos;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias(value = "askDto")
public class AskDto {
	private int ask_seq;
	private String id;
	private String ask_title;
	private String ask_content;
	private Date ask_regdate;
	private int ask_refer;
	private int ask_step;
	private int ask_depth;
	private int ask_readcount;
	private String ask_delflag;
	
	public AskDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AskDto(int ask_seq, String id, String ask_title, String ask_content, Date ask_regdate, int ask_refer,
			int ask_step, int ask_depth, int ask_readcount, String ask_delflag) {
		super();
		this.ask_seq = ask_seq;
		this.id = id;
		this.ask_title = ask_title;
		this.ask_content = ask_content;
		this.ask_regdate = ask_regdate;
		this.ask_refer = ask_refer;
		this.ask_step = ask_step;
		this.ask_depth = ask_depth;
		this.ask_readcount = ask_readcount;
		this.ask_delflag = ask_delflag;
	}

	public int getAsk_seq() {
		return ask_seq;
	}

	public void setAsk_seq(int ask_seq) {
		this.ask_seq = ask_seq;
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

	public Date getAsk_regdate() {
		return ask_regdate;
	}

	public void setAsk_regdate(Date ask_regdate) {
		this.ask_regdate = ask_regdate;
	}

	public int getAsk_refer() {
		return ask_refer;
	}

	public void setAsk_refer(int ask_refer) {
		this.ask_refer = ask_refer;
	}

	public int getAsk_step() {
		return ask_step;
	}

	public void setAsk_step(int ask_step) {
		this.ask_step = ask_step;
	}

	public int getAsk_depth() {
		return ask_depth;
	}

	public void setAsk_depth(int ask_depth) {
		this.ask_depth = ask_depth;
	}

	public int getAsk_readcount() {
		return ask_readcount;
	}

	public void setAsk_readcount(int ask_readcount) {
		this.ask_readcount = ask_readcount;
	}

	public String getAsk_delflag() {
		return ask_delflag;
	}

	public void setAsk_delflag(String ask_delflag) {
		this.ask_delflag = ask_delflag;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}
