package com.hk.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.board.dtos.CalDto;

@Mapper
public interface CalMapper {
	//한달의 일정 보여주기
	public List<CalDto> calViewList(String yyyyMM);
		
	//일정추가하기
	public boolean insertCalBoard(CalDto dto);
	
	//일정상세보기
	public CalDto detailCalBoard(String yyyyMMdd);
	
	//일정수정하기
	public boolean updateCalBoard(CalDto dto);
	
	//일정삭제하기
	public boolean deleteCalBoard(String yyyyMMdd);
	
	//그 날짜에 일정이 있는지 없는지
	public CalDto exitCal(String yyyyMMdd);
}
