package com.hk.board.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hk.board.service.CalService;
import com.hk.board.command.InsertCalCommand;
import com.hk.board.dtos.BoardDto;
import com.hk.board.dtos.CalDto;
import com.hk.board.utils.Util;
import com.hk.board.command.UpdateCalCommand;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/calendar")
public class CalController {
	@Autowired
	private CalService calService;
	
	@GetMapping(value="/calendar")
	public String calendar(Model model, HttpServletRequest request) {
		System.out.println("달력보기");
		 
		//달력에서 일일별 일정 목록 구하기
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		
		if(year==null||month==null) {
			Calendar cal = Calendar.getInstance();
			year = cal.get(Calendar.YEAR)+"";
			month = (cal.get(Calendar.MONTH)+1)+"";
		}
		
//		String yyyyMM = year+Util.isTwo(month); //isTwo써서 6자리로 맞춰줌 
//		List<CalDto>clist=calService.calViewList(yyyyMM);
//		model.addAttribute("clist",clist);
		
		//달력만들기 위한 값 구하기
		Map<String, Integer>map=calService.makeCalendar(request);
		model.addAttribute("calMap", map);
		return "calendar/calendar";
	}
	
	@GetMapping(value="/insertCalBoardForm")
	public String insertCalBoardForm(Model model,InsertCalCommand insertCalCommand){
		System.out.println("경기일정추가폼");
		
		
		model.addAttribute("insertCalCommand", insertCalCommand);
		return "calendar/insertCalBoardForm";
	}
	
	
	@PostMapping(value="/insertCalBoard")
	public String insertCalBoard(@Validated InsertCalCommand insertCalCommand,
			  BindingResult result,Model model){
		System.out.println("경기일정추가하기");
		if(result.hasErrors()) {
			System.out.println("모두 입력해야 함");
			model.addAttribute("insertCalCommand", insertCalCommand);
			return "calendar/insertCalBoardForm";
		}
		calService.insertCalBoard(insertCalCommand);		
		
		return "redirect:/calendar/calendar?year="+insertCalCommand.getYear()+"&month="+insertCalCommand.getMonth();
	}
	
	@GetMapping(value="/detailCalBoard")
	public String detailCalBoard(@RequestParam Map<String,String>map,Model model,HttpServletRequest request){
		System.out.println("일정 상세보기");
		
		//일정 목록을 조회할때마다 year,month,date를 session에 저장해서 관리
		HttpSession session=request.getSession();
				
		if(map.get("year")==null) {
			//조회한 상태이기 때문에 ymd가 저장되어 있어서 값을 가져옴
			map=(Map<String,String>)session.getAttribute("ymdMap");
		}else{
			//일정을 처음 조회했을때 ymd를 저장함
			session.setAttribute("ymdMap", map);
		}
				
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String date = Util.isTwo(request.getParameter("date"));
		
		String yyyyMMdd = year+month+date;
		CalDto dto = calService.detailCalBoard(yyyyMMdd);
		if(dto != null) {
			model.addAttribute("dto",dto);			
		}else{
			String fdate = yyyyMMdd+"0000";
			model.addAttribute("fdate", fdate);
			model.addAttribute("dto",null);
		}
		
		return "calendar/detailCalBoard";
	}
	
	@GetMapping(value="/updateCalBoardForm")
	public String updateCalBoardForm(UpdateCalCommand updateCalCommand,Model model,HttpServletRequest request){
		System.out.println("일정 추가 폼");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String date = Util.isTwo(request.getParameter("date"));
		
		String yyyyMMdd = year+month+date;
		
		CalDto dto = calService.detailCalBoard(yyyyMMdd);
		
		//dto ---> command
		updateCalCommand.setTitle(dto.getTitle());
		updateCalCommand.setScore(dto.getScore());
		updateCalCommand.setContent(dto.getContent());
		updateCalCommand.setYear(Integer.parseInt(dto.getMdate().substring(0,4)));
		updateCalCommand.setMonth(Integer.parseInt(dto.getMdate().substring(4,6)));
		updateCalCommand.setDate(Integer.parseInt(dto.getMdate().substring(6,8)));
		updateCalCommand.setHour(Integer.parseInt(dto.getMdate().substring(8,10)));
		updateCalCommand.setMin(Integer.parseInt(dto.getMdate().substring(10)));
		
		model.addAttribute("updateCalCommand",updateCalCommand);
		model.addAttribute("yyyyMMdd", yyyyMMdd);
		
		return "calendar/updateCalBoardForm";
	}
	
	@PostMapping(value="/updateCalBoard")
	public String updateCalBoard(@Validated UpdateCalCommand updateCalCommand,BindingResult result,String yyyyMMdd,String chk,Model model){
		
		System.out.println("경기일정수정하기");
		if(result.hasErrors()) {
			System.out.println("모두 입력해야 함");
			
			CalDto dto = calService.detailCalBoard(yyyyMMdd);
			
			//dto ---> command
			updateCalCommand.setTitle(dto.getTitle());
			updateCalCommand.setScore(dto.getScore());
			updateCalCommand.setContent(dto.getContent());
			updateCalCommand.setYear(Integer.parseInt(dto.getMdate().substring(0,4)));
			updateCalCommand.setMonth(Integer.parseInt(dto.getMdate().substring(4,6)));
			updateCalCommand.setDate(Integer.parseInt(dto.getMdate().substring(6,8)));
			updateCalCommand.setHour(Integer.parseInt(dto.getMdate().substring(8,10)));
			updateCalCommand.setMin(Integer.parseInt(dto.getMdate().substring(10)));
			
			model.addAttribute("updateCalCommand",updateCalCommand);
			model.addAttribute("yyyyMMdd", yyyyMMdd);

			
			return "calendar/updateCalBoardForm";
		}
		calService.updateCalBoard(updateCalCommand,yyyyMMdd);
		model.addAttribute("chk",chk);	
		return "redirect:/calendar/calendar";
	}
	
	@GetMapping(value="/deleteCalBoard")
	public String deleteCalBoard(HttpServletRequest request) {
		System.out.println("일정 삭제하기");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String date = Util.isTwo(request.getParameter("date"));
	
		String yyyyMMdd = year+month+date;
		
		calService.deleteCalBoard(yyyyMMdd);
		
		return "redirect:/calendar/calendar";
	}
}
