package com.hk.board.command;

import jakarta.validation.constraints.NotBlank;

public class ReplyBoardCommand {
	
	private String id;
	private int board_seq;
	private int refer;
	private int depth;
	private int step;
	
	@NotBlank(message="내용을 입력하세요")
	private String reply;

	public ReplyBoardCommand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReplyBoardCommand(String id, int board_seq, int refer, int depth, int step,
			@NotBlank(message = "내용을 입력하세요") String reply) {
		super();
		this.id = id;
		this.board_seq = board_seq;
		this.refer = refer;
		this.depth = depth;
		this.step = step;
		this.reply = reply;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBoard_seq() {
		return board_seq;
	}

	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}

	public int getRefer() {
		return refer;
	}

	public void setRefer(int refer) {
		this.refer = refer;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	@Override
	public String toString() {
		return "ReplyBoardCommand [id=" + id + ", board_seq=" + board_seq + ", refer=" + refer + ", depth=" + depth
				+ ", step=" + step + ", reply=" + reply + "]";
	}
	
	
}
