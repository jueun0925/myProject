package com.hk.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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

import com.hk.board.command.DeleteBoardCommand;
import com.hk.board.command.InsertBoardCommand;
import com.hk.board.command.ReplyBoardCommand;
import com.hk.board.command.UpdateBoardCommand;
import com.hk.board.dtos.BoardDto;
import com.hk.board.service.BoardService;
import com.hk.board.service.FileService;
import com.hk.board.utils.Paging;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private FileService fileService;
	
	//getCookie 기능 구현
		public Cookie getCookie(String cookieName, HttpServletRequest request) {
			Cookie[] cookies=request.getCookies();
			Cookie cookie=null;
			if(cookies!=null) {
				for (int i = 0; i < cookies.length; i++) {
					if(cookies[i].getName().equals(cookieName)) {
						cookie=cookies[i];
					}
				}			
			}
			return cookie;
		}
		
		@GetMapping(value = "/boardList")
		public String boardList(Model model,HttpServletRequest request,HttpServletResponse response,String pnum) { 
			System.out.println("글 목록보기");
						//글목록으로 이동하면 쿠키 rseq값을 삭제하자
			Cookie cookie=getCookie("rseq", request);
			if(cookie!=null) {
				cookie.setMaxAge(0);//유효기간 0 --> 삭제됨
				response.addCookie(cookie);//클라이언트로 변경사항을 전달
			}
			//쿠키 삭제 코드 종료------------
			
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
			List<BoardDto>list=boardService.boardList(pnum);
			model.addAttribute("list", list);
			model.addAttribute("deleteBoardCommand", new DeleteBoardCommand());
			
			//페이지 수 구하기 
			int pcount=boardService.getPCount();
			model.addAttribute("pcount", pcount);
			
			//---페이지에 페이징 처리 기능 추가
			//필요한 값: 페이지수, 페이지번호, 페이지범위(페이지수)
			Map<String, Integer>map=Paging.pagingValue(pcount, pnum, 10);
			model.addAttribute("pMap", map);
			
			return "board/boardList";//"WEB-INF/views/"+boardList+".jsp"
		}
		
		@GetMapping(value="/detailBoard")
		public String boardDetail(int board_seq,Model model,HttpServletRequest request
				,HttpServletResponse response,int refer) {
			System.out.println("글 상세보기");
			
			BoardDto dto = boardService.detailBoard(board_seq);
			
			//출력용
			model.addAttribute("bdto", dto);

			//댓글목록용
			List<BoardDto>rList=boardService.replyList(refer);
			model.addAttribute("rList", rList);
			
			
			//유효값 처리용
			model.addAttribute("replyBoardCommand", new ReplyBoardCommand());
			
			String s=null;
			
			//getCookie메서드 구현해서 활용하기
			Cookie cookieObj=getCookie("rseq", request);
			
			if(cookieObj!=null) {//cookie가 null이 아닐 경우 실행
				s=cookieObj.getValue();				
			}
			
			//"rseq"라는 이름의 값이 있는지 확인(쿠키값이 없는 경우)
			if(s==null||!s.equals(String.valueOf(board_seq))) {
				//쿠키객체 생성하기
				//                    cookie에 값을 저장할때 타입은 String 이다
				Cookie cookie=new Cookie("rseq", String.valueOf(board_seq));
				cookie.setMaxAge(60*10);//유효기간 설정
				response.addCookie(cookie);//클라이언트로 cookie객체 전달
				
				//---조회수 올리기 코드
				boardService.readCount(board_seq);//조회수 증가
				//--조회수 코드 종료
			}
			
			return "board/detailBoard";
		}
		
		@GetMapping(value="/insertBoardForm")
		public String insertBoardForm(Model model) {
			System.out.println("글 추가하기");
			
			model.addAttribute("insertBoardCommand", new InsertBoardCommand());
			
			return "board/insertBoardForm";
		}
		
		@PostMapping(value="/insertBoard")
		public String insertBoard(@Validated InsertBoardCommand insertBoardCommand
				,BindingResult result
				,MultipartRequest multipartRequest //multipart data 를 처리할때
				,Model model
				,HttpServletRequest request) throws IllegalStateException, IOException {
			
			if(result.hasErrors()) {
				System.out.println("글을 모두 입력하세요");
				
				return "board/insertBoardForm";
			}
			
			boardService.insertBoard(insertBoardCommand, multipartRequest, request);
			return "redirect:/board/boardList";
		}
		
		@GetMapping(value="/updateBoardForm")
		public String updateBoardForm(int board_seq,Model model){
			BoardDto dto = boardService.detailBoard(board_seq);
			
			//유효값 처리용
			model.addAttribute("updateBoardCommand", new UpdateBoardCommand());
			
			//출력용
			model.addAttribute("bdto", dto);
			
			return "board/updateBoardForm";
		}
		
		@PostMapping(value="/updateBoard")															
		public String updateBoard(@Validated UpdateBoardCommand updateBoardCommand
									,BindingResult result
									,Model model){
			if(result.hasErrors()) {
				System.out.println("수정내용을 모두 입력하세요");

				BoardDto dto=boardService.detailBoard(updateBoardCommand.getBoard_seq());
				model.addAttribute("bdto", dto);

				return "board/updateBoardForm";
			}
			
			try {
				boardService.updateBoard(updateBoardCommand);
				System.out.println("글수정 성공");
				return "redirect:/board/detailBoard?board_seq="+updateBoardCommand.getBoard_seq()+"&refer="+updateBoardCommand.getRefer();
			} catch (Exception e) {
				System.out.println("글수정 실패");
				e.printStackTrace();
				
				return "redirect:/board/updateBoardForm?board_seq="+updateBoardCommand.getBoard_seq();
			}
		}
		
		@RequestMapping(value="mulDel",method = {RequestMethod.POST,RequestMethod.GET})
		public String mulDel(@Validated DeleteBoardCommand deleteBoardCommand
							 ,BindingResult result
				             , Model model) {
			if(result.hasErrors()) {
				System.out.println("최소하나 체크하기");
				List<BoardDto> list=boardService.boardList("1");
				model.addAttribute("list", list);
				model.addAttribute("deleteBoardCommand", deleteBoardCommand);
				int pcount=boardService.getPCount();
				model.addAttribute("pcount", pcount);
				Map<String, Integer>map=Paging.pagingValue(pcount,"1", 10);
				model.addAttribute("pMap", map);
				
				return "board/boardlist";
			}
			boardService.mulDel(deleteBoardCommand.getChk());
			System.out.println("글삭제함");
			return "redirect:/board/boardList";
		}
		
		@PostMapping(value="/insertReply")
		public String insertReply(@Validated ReplyBoardCommand replyBoardCommand
				,BindingResult result,Model model) {
			if(result.hasErrors()) {
				System.out.println("답글을 입력하세요");
				
				BoardDto dto = boardService.detailBoard(replyBoardCommand.getBoard_seq());
				//출력용
				model.addAttribute("bdto", dto);

				//댓글목록용
				List<BoardDto>rList=boardService.replyList(replyBoardCommand.getRefer());
				model.addAttribute("rList", rList);
				
				
				return "board/detailBoard";
			}
			
			boardService.replyBoard(replyBoardCommand);
			System.out.println(replyBoardCommand.getRefer());
			System.out.println(replyBoardCommand.getStep());
			
			return "redirect:/board/detailBoard?board_seq="+replyBoardCommand.getBoard_seq()+"&refer="+replyBoardCommand.getRefer();
		}
		
		@PostMapping(value="/insertReply2")
		public String insertReply2(@Validated ReplyBoardCommand replyBoardCommand
				,BindingResult result,Model model,String dolNum) {
			if(result.hasErrors()) {
				System.out.println("답글을 입력하세요");
				
				BoardDto dto = boardService.detailBoard(replyBoardCommand.getBoard_seq());
				//출력용
				model.addAttribute("bdto", dto);

				//댓글목록용
				List<BoardDto>rList=boardService.replyList(replyBoardCommand.getRefer());
				model.addAttribute("rList", rList);
				
				
				return "board/detailBoard";
			}
			
			boardService.replyBoard(replyBoardCommand);
			System.out.println(replyBoardCommand.getRefer());
			System.out.println(replyBoardCommand.getStep());
			
			return "redirect:/board/detailBoard?board_seq="+dolNum+"&refer="+replyBoardCommand.getRefer();
		}
		@GetMapping(value="/replyDel")
		public String replyDel(int board_seq,int refer,int rboard_seq) {
			String chk = Integer.toString(rboard_seq);
			String[] chks = {chk};
			boardService.mulDel(chks);
			System.out.println("댓글삭제함");
			return "redirect:/board/detailBoard?board_seq="+board_seq+"&refer="+refer;
		}
}
