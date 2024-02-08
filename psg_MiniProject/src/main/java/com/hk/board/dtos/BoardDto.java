package com.hk.board.dtos;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias(value = "boardDto")
public class BoardDto {
	private int board_seq;
	private String id;
	private String title;
	private String content;
	private Date regdate;
	private int refer;
	private int step;
	private int depth;
	private int readcount;
	private String delflag;
	
	//Join용 맴버필드
	private List<FileBoardDto> fileBoardDto;

	public BoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardDto(int board_seq, String id, String title, String content, Date regdate, int refer, int step,
			int depth, int readcount, String delflag, List<FileBoardDto> fileBoardDto) {
		super();
		this.board_seq = board_seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.refer = refer;
		this.step = step;
		this.depth = depth;
		this.readcount = readcount;
		this.delflag = delflag;
		this.fileBoardDto = fileBoardDto;
	}

	public int getBoard_seq() {
		return board_seq;
	}

	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getRefer() {
		return refer;
	}

	public void setRefer(int refer) {
		this.refer = refer;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public List<FileBoardDto> getFileBoardDto() {
		return fileBoardDto;
	}

	public void setFileDto(List<FileBoardDto> fileBoardDto) {
		this.fileBoardDto = fileBoardDto;
	}

	@Override
	public String toString() {
		return "BoardDto [board_seq=" + board_seq + ", id=" + id + ", title=" + title + ", content=" + content
				+ ", regdate=" + regdate + ", refer=" + refer + ", step=" + step + ", depth=" + depth + ", readcount="
				+ readcount + ", delflag=" + delflag + ", fileBoardDto=" + fileBoardDto + "]";
	}

	
		
	
	
}
