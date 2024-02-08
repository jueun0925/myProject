package com.hk.board.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartRequest;

import com.hk.board.command.DeleteAskCommand;
import com.hk.board.command.DeleteBoardCommand;
import com.hk.board.command.InsertAskCommand;
import com.hk.board.command.InsertBoardCommand;
import com.hk.board.command.ReplyAskCommand;
import com.hk.board.command.ReplyBoardCommand;
import com.hk.board.command.UpdateAskCommand;
import com.hk.board.command.UpdateBoardCommand;
import com.hk.board.dtos.AskDto;
import com.hk.board.dtos.BoardDto;
import com.hk.board.service.AskService;
import com.hk.board.utils.Paging;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/ask")
public class AskController {
	@Autowired
	private AskService askService;
			
	@GetMapping(value = "/getAskList")
	public String getAskList(Model model,HttpServletRequest request,HttpServletResponse response,String pnum) { 
		System.out.println("문의글 목록보기");
		
		//---페이지번호 유지를 위한 코드----------------------
		//페이지번호를 전달하지 않으면 세션에 저장된 페이지번호를 사용
		HttpSession session=request.getSession();
		if(pnum==null) {
			pnum=(String)session.getAttribute("pnum");//현재 조회중인 페이지번호
			
		}else {
			//새로 페이지를 요청할 경우 세션에 저장
			session.setAttribute("pnum", pnum);
		}
		//---페이지번호 유지를 위한 코드 종료-------------------
//		
		//글목록 구하기
		List<AskDto> aList=askService.getAskList(pnum);
		model.addAttribute("aList", aList);
		model.addAttribute("deleteAskCommand", new DeleteAskCommand());
		
		//페이지 수 구하기 
		int pcount=askService.getPCount();
		model.addAttribute("pcount", pcount);
		
		//---페이지에 페이징 처리 기능 추가
		//필요한 값: 페이지수, 페이지번호, 페이지범위(페이지수)
		Map<String, Integer>map=Paging.pagingValue(pcount, pnum, 10);
		model.addAttribute("pMap", map);
		
		return "ask/askList";
	}	
	
	//문의글 추가 폼
	@GetMapping(value="/insertAskBoardForm")
	public String insertAskBoardForm(Model model) {
		System.out.println("문의글 추가하기");
		
		model.addAttribute("insertAskCommand", new InsertAskCommand());
		
		return "ask/insertAskBoardForm";
	}
	
	//문의글 추가
	@PostMapping(value="/insertAskBoard")
	public String insertAskBoard(@Validated InsertAskCommand insertAskCommand,BindingResult result,Model model) {
		
		if(result.hasErrors()) {
			System.out.println("글을 모두 입력하세요");
			
			return "ask/insertAskBoardForm";
		}
		
		askService.insertAskBoard(insertAskCommand);
		return "redirect:/ask/getAskList";
	}
	
	//글 상세보기
	@GetMapping(value="/detailAskBoard")
	public String detailAskBoard(int ask_seq,Model model,HttpServletRequest request
			,HttpServletResponse response,int ask_refer) {
		System.out.println("글 상세보기");
		
		AskDto adto = askService.detailAskBoard(ask_seq);
		
		//출력용
		model.addAttribute("adto", adto);

		//댓글목록용
		List<AskDto>aList=askService.replyList(ask_refer);
		model.addAttribute("aList", aList);
		
		
		//유효값 처리용
		model.addAttribute("replyAskCommand", new ReplyAskCommand());
		
		
		return "ask/detailAskBoard";
	}
	
	//문의글수정폼으로 이동	
	@GetMapping(value="/updateAskBoardForm")
	public String updateAskBoardForm(int ask_seq,Model model){
		AskDto adto = askService.detailAskBoard(ask_seq);
		
		//유효값 처리용
		model.addAttribute("updateAskCommand", new UpdateAskCommand());
		
		//출력용
		model.addAttribute("adto", adto);
		
		return "ask/updateAskBoardForm";
	}
	
	//문의글수정
	@PostMapping(value="/updateAskBoard")															
	public String updateAskBoard(@Validated UpdateAskCommand updateAskCommand
								,BindingResult result
								,Model model){
		if(result.hasErrors()) {
			System.out.println("수정내용을 모두 입력하세요");

			AskDto adto=askService.detailAskBoard(updateAskCommand.getAsk_seq());
			model.addAttribute("adto", adto);

			return "ask/updateAskBoardForm";
		}
		
		try {
			askService.updateAskBoard(updateAskCommand);
			System.out.println("글수정 성공");
			return "redirect:/ask/detailAskBoard?ask_seq="+updateAskCommand.getAsk_seq()+"&ask_refer="+updateAskCommand.getAsk_refer();
		} catch (Exception e) {
			System.out.println("글수정 실패");
			e.printStackTrace();
			
			return "redirect:/ask/updateAskBoardForm?ask_seq="+updateAskCommand.getAsk_seq();
		}
	}
	//글 삭제
	@RequestMapping(value="mulDelAsk",method = {RequestMethod.POST,RequestMethod.GET})
	public String mulDelAsk(@Validated DeleteAskCommand deleteAskCommand
						 ,BindingResult result
			             , Model model) {
		if(result.hasErrors()) {
			System.out.println("최소하나 체크하기");
			List<AskDto> aList=askService.getAskList("1");
			model.addAttribute("aList", aList);
			model.addAttribute("deleteAskCommand", deleteAskCommand);
			int pcount=askService.getPCount();
			model.addAttribute("pcount", pcount);
			Map<String, Integer>map=Paging.pagingValue(pcount,"1", 10);
			model.addAttribute("pMap", map);
			return "ask/askList";
		}
		askService.mulDelAsk(deleteAskCommand.getChk());
		System.out.println("글삭제함");
		return "redirect:/ask/getAskList";
	}
	
	//답변달기
	@PostMapping(value="/insertReply")
	public String insertReply(@Validated ReplyAskCommand replyAskCommand
			,BindingResult result,Model model) {
		if(result.hasErrors()) {
			System.out.println("답변을 입력하세요");
			
			AskDto adto =askService.detailAskBoard(replyAskCommand.getAsk_seq());
			//출력용
			model.addAttribute("adto", adto);

			//댓글목록용
			List<AskDto>aList=askService.replyList(replyAskCommand.getAsk_refer());
			model.addAttribute("aList", aList);
			
			
			return "ask/detailAskBoard";
		}
		
		askService.replyBoard(replyAskCommand);
		System.out.println(replyAskCommand.getAsk_refer());
		System.out.println(replyAskCommand.getAsk_step());
		
		return "redirect:/ask/detailAskBoard?ask_seq="+replyAskCommand.getAsk_seq()+"&ask_refer="+replyAskCommand.getAsk_refer();
	}
	
	//답변삭제
	@GetMapping(value="/replyDel")
	public String replyDel(int ask_seq,int ask_refer,int rAsk_seq) {
		String chk = Integer.toString(rAsk_seq);
		String[] chks = {chk};
		askService.mulDelAsk(chks);
		System.out.println("댓글삭제함");
		return "redirect:/ask/detailAskBoard?ask_seq="+ask_seq+"&ask_refer="+ask_refer;
	}
}
