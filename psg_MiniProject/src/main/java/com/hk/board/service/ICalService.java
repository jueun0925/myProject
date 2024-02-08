package com.hk.board.service;

import java.util.List;
import java.util.Map;

import com.hk.board.command.InsertCalCommand;
import com.hk.board.command.UpdateCalCommand;
import com.hk.board.dtos.CalDto;

import jakarta.servlet.http.HttpServletRequest;

public interface ICalService {
	//달력생성시 필요한 값 구하는 메서드
	public Map<String, Integer> makeCalendar(HttpServletRequest request);
	
	//한달의 일정보여주기
	public List<CalDto> calViewList(String yyyyMM);
	
	//일정추가하기
	public boolean insertCalBoard(InsertCalCommand insertCalCommand);
		
	//일정상세보기
	public CalDto detailCalBoard(String yyyyMMdd);
		
	//일정수정하기
	public boolean updateCalBoard(UpdateCalCommand updateCalCommand,String yyyyMMdd);
		
	//일정삭제하기
	public boolean deleteCalBoard(String yyyyMMdd);
}
