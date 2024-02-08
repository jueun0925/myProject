package com.hk.board.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.board.command.InsertCalCommand;
import com.hk.board.command.UpdateCalCommand;
import com.hk.board.dtos.CalDto;
import com.hk.board.mapper.CalMapper;
import com.hk.board.utils.Util;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CalService implements ICalService{
	@Autowired
	private CalMapper calMapper;
	
	public Map<String, Integer> makeCalendar(HttpServletRequest request){
		Map<String ,Integer> map=new HashMap<>();
		
		//달력의 날짜를 바꾸기 위해 전달된 year와 month 파라미터를 받는 코드
		String paramYear=request.getParameter("year");
		String paramMonth=request.getParameter("month");
		
		Calendar cal=Calendar.getInstance(); // 추상클래스이고, static 메서드 new(X)
		
		//                          기본 오늘날짜로 저장할지  :  요청된 날짜로 저장할지
		int year=(paramYear==null)?cal.get(Calendar.YEAR):Integer.parseInt(paramYear) ;
		int month=(paramMonth==null)?cal.get(Calendar.MONTH)+1:Integer.parseInt(paramMonth) ;
		//                         calendar객체에서 month는 0~11월임
		
		// 11월,12월,13월.....      오류 처리
		// -2월, -1월 , 0월 , 1월   오류 처리
		if(month>12) {
			month=1;
			year++;
		}
		if(month<1) {
			month=12;
			year--;
		}
		
		//1.월의 1일에 대한 요일 구하기
		cal.set(year, month-1,1);// 원하는 날짜로 셋팅
		int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);//1~7중에 반환(1:일요일~7:토요일)
		
		//2.월의 마지막 날 구하기
		int lastDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		
		//makeCalendar()에서 실행해야 12->1   1->12 처리시 정상실행할 수 있음
		String yyyyMM=year+Util.isTwo(month+"");//202311 6자리변환
		List<CalDto>clist=calViewList(yyyyMM);
		request.setAttribute("clist", clist);
		
		map.put("year", year);
		map.put("month", month);
		map.put("dayOfWeek", dayOfWeek);
		map.put("lastDay", lastDay);
		
		return map;
	}
	
	//한달일정보여주기
	@Override
	public List<CalDto> calViewList(String yyyyMM) {
		
		return calMapper.calViewList(yyyyMM);
	}
	
	//일정추가하기
	@Override
	public boolean insertCalBoard(InsertCalCommand insertCalCommand) {
		
		// command --> dto로  값을 이동
		//DB에서는 mdate컬럼, command에서는 year,month,...:12자리로 변환작업
		String mdate = insertCalCommand.getYear()+""
						+Util.isTwo(insertCalCommand.getMonth()+"")
						+Util.isTwo(insertCalCommand.getDate()+"")
						+Util.isTwo(insertCalCommand.getHour()+"")
						+Util.isTwo(insertCalCommand.getMin()+"");
		//202311151335 12자리 / 2023 118 1110 -> 11자리 
				
		CalDto dto = new CalDto();
		dto.setTitle(insertCalCommand.getTitle());
		if(insertCalCommand.getScore()!="" || insertCalCommand.getScore()!=null) {
			dto.setScore(insertCalCommand.getScore());			
		}else {
			dto.setScore("no");
		}
		dto.setContent(insertCalCommand.getContent());
		dto.setMdate(mdate);
		
		return calMapper.insertCalBoard(dto);
	}
	
	//일정상세보기
	@Override
	public CalDto detailCalBoard(String yyyyMMdd) {
		
		return calMapper.detailCalBoard(yyyyMMdd);
	}
	
	//일정수정하기
	@Override
	public boolean updateCalBoard(UpdateCalCommand updateCalCommand,String yyyyMMdd) {
		
		String mdate = updateCalCommand.getYear()+""
				+Util.isTwo(updateCalCommand.getMonth()+"")
				+Util.isTwo(updateCalCommand.getDate()+"")
				+Util.isTwo(updateCalCommand.getHour()+"")
				+Util.isTwo(updateCalCommand.getMin()+"");
		//202311151335 12자리 / 2023 118 1110 -> 11자리 
		
		CalDto dto = new CalDto();
		dto.setTitle(updateCalCommand.getTitle());
		dto.setScore(updateCalCommand.getScore());
		dto.setContent(updateCalCommand.getContent());
		dto.setMdate(mdate);
		dto.setYyyyMMdd(yyyyMMdd);
		
		return calMapper.updateCalBoard(dto);
	}

	//일정삭제하기
	@Override
	public boolean deleteCalBoard(String yyyyMMdd) {
		
		return calMapper.deleteCalBoard(yyyyMMdd);
	}
	
	//그 날짜에 일정이 있나 없나
	public CalDto exitCal(String yyyyMMdd) {
		return calMapper.exitCal(yyyyMMdd);
	}
}
