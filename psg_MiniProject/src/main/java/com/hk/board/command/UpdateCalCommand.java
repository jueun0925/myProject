package com.hk.board.command;

import com.hk.board.utils.Util;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateCalCommand {
	@NotNull(message="년도를 입력하세요")
	private int year;
	
	@NotNull(message="월을 입력하세요")
	private int month;
	
	@NotNull(message="일을 입력하세요")
	private int date;
	
	@NotNull(message="시간을 입력하세요")
	private int hour;
	
	@NotNull(message="분을 입력하세요")
	private int min;
	
	@NotBlank(message="제목을 입력하세요")
	private String title;
	
	private String score;
	
	@NotBlank(message="내용을 입력하세요")
	private String content;

	public UpdateCalCommand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdateCalCommand(@NotNull(message = "년도를 입력하세요") int year, @NotNull(message = "월을 입력하세요") int month,
			@NotNull(message = "일을 입력하세요") int date, @NotNull(message = "시간을 입력하세요") int hour,
			@NotNull(message = "분을 입력하세요") int min, @NotBlank(message = "제목을 입력하세요") String title, String score,
			@NotBlank(message = "내용을 입력하세요") String content) {
		super();
		this.year = year;
		this.month = month;
		this.date = date;
		this.hour = hour;
		this.min = min;
		this.title = title;
		this.score = score;
		this.content = content;
	}


	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}
