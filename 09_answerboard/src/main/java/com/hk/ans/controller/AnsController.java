package com.hk.ans.controller;
//class로 생성했는데 서블릿으로 사용할거임

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.hk.ans.daos.AnsDao;
import com.hk.ans.dtos.AnsDto;
import com.hk.ans.utils.Paging;

@WebServlet("*.board")
public class AnsController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1단계 : command값 구하기
		String command = request.getRequestURI()
						.substring(request.getContextPath().length());
		System.out.println("command값 : "+command);
		
		//2단계 dao객체 생성
		AnsDao dao = new AnsDao();
		
		//Session 객체 생성
		HttpSession session=request.getSession();
		
		//3단계 : if분기
		if(command.equals("/boardList.board")) {
			
			//글 목록으로 이동하면 쿠키 rseq값을 삭제하자
			Cookie cookie = getCookie("rseq", request);
			if(cookie!=null) {
				cookie.setMaxAge(0); //유효기간 0 => 삭제됨
				response.addCookie(cookie);//클라이언트로 변경사항을 전달
			}
			//페이지번호 받기
			String pnum=request.getParameter("pnum");
			
			//---페이지번호 유지를 위한 코드----------------------
			//페이지번호를 전달하지 않으면 세션에 저장된 페이지번호를 사용
			if(pnum==null) {
				pnum=(String)session.getAttribute("pnum");//현재 조회중인 페이지번호
			}else {
				//새로 페이지를 요청할 경우 세션에 저장
				session.setAttribute("pnum", pnum);
			}
			//---페이지번호 유지를 위한 코드 종료-------------------
			
			//글목록 
			List<AnsDto> list = dao.getAllList(pnum);
			request.setAttribute("list", list);
			
			//페이지수
			int pcount = dao.getPCount();
			request.setAttribute("pcount", pcount);
			
			//---페이지에 페이징 처리 기능 추가
			//필요한 값: 페이지수, 페이지번호, 페이지범위(페이지수)
			Map<String, Integer> map=Paging.pagingValue(pcount, pnum, 5);
			request.setAttribute("pMap", map);
			
			dispatch("board/boardList.jsp", request, response);
			
		}else if(command.equals("/insertForm.board")) {
			dispatch("board/insertForm.jsp", request, response);
		}else if(command.equals("/insertBoard.board")) {
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			boolean isS=dao.insertBoard(new AnsDto(id,title,content));
			
			if(isS) {
				response.sendRedirect("boardList.board");
			}else {
				request.setAttribute("msg", "글추가실패");
				dispatch("error.jsp", request, response);
			}
					
		}else if(command.equals("/detailBoard.board")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			AnsDto dto = dao.getBoard(seq);
			
			//쿠키객체 가져오기 : 반환타입 - 배열
			//getName():쿠키 이름 구하기
			//getValue():쿠키 값 구하기
//			Cookie[] cookies=request.getCookies();
//			String s=null;
//			for (int i = 0; i < cookies.length; i++) {
//				if(cookies[i].getName().equals("rseq")) {
//					s=cookies[i].getValue();
//				}
//			}
			
			
			//getCookie메서드 구현해서 활용하기
			Cookie cookieObj = getCookie("rseq", request);
//			String s = cookieObj.getValue(); //getValue()한 값이 null이면 오류가 남
			String s=null;
			if(cookieObj!=null) { //cookie가 null이 아닐경우 실행
				s=cookieObj.getValue(); 
			}
			//"rseq"라는 이름의 값이 있는지 확인
			//새로고침할때 조회수 올라가지 않게할려고 쿠키 사용
			if(s==null||!s.equals(String.valueOf(seq))) { //쿠키값이 없는 경우
				//쿠키객체 생성하기
				Cookie cookie = new Cookie("rseq", String.valueOf(seq)); //(저장할 이름,담을 값)//쿠키는 문자열로 저장을 함 int를 String으로 변환해서 보내삼
				cookie.setMaxAge(60*10); //유효기간 10분
				response.addCookie(cookie); //클라이언트로 cookie객체 전달
	
				//쿠키값이 없으면 조회수를 올림
				//조회수올리기코드
				dao.readCount(seq);
				//조회수 코드 종료
			}
			
			request.setAttribute("dto", dto);
			dispatch("board/detailBoard.jsp", request, response);
		}else if(command.equals("/updateBoardForm.board")) {
			int seq = Integer.parseInt(request.getParameter("seq"));		
			AnsDto dto = dao.getBoard(seq);
			
			request.setAttribute("dto", dto);
			
			dispatch("board/updateBoard.jsp", request, response);
		}else if(command.equals("/updateBoard.board")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			boolean isS = dao.updateBoard(new AnsDto(seq,title,content));
			
			if(isS) {
				response.sendRedirect("detailBoard.board?seq="+seq);
			}else {
				request.setAttribute("msg","수정실패");
				dispatch("error.jsp", request, response);
			}
			
		}else if(command.equals("/mulDel.board")) {
			String[] seqs = request.getParameterValues("chk");
			
			boolean isS=dao.mulDel(seqs);
			
			if(isS) {
				response.sendRedirect("boardList.board");
			}else {
				request.setAttribute("msg", "글삭제실패");
				
				dispatch("error.jsp", request, response);
			}
		}else if(command.equals("/replyBoard.board")) {
			int seq = Integer.parseInt(request.getParameter("seq"));//부모의 번호
			
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			
			boolean isS=dao.replyBoard(new AnsDto(seq,id,title,content));
			
			if(isS) {
				response.sendRedirect("boardList.board");
				
			}else {
				request.setAttribute("msg", "답글추가실패");
				
				dispatch("error.jsp", request, response);
			}
			
		}else if(command.equals("/")) {
			
		}else if(command.equals("/")) {
			
		}else if(command.equals("/")) {
			
		}else if(command.equals("/")) {
			
		}else if(command.equals("/")) {
			
		}else if(command.equals("/")) {
			
		}else if(command.equals("/")) {
			
		}else if(command.equals("/")) {
			
		}else if(command.equals("/")) {
			
		}
		
	}
	
	//forward구현
	public void dispatch(String url,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher(url).forward(request,response);
	}
	
	//getCookie기능구현
	public Cookie getCookie(String cookiename,HttpServletRequest request) throws ServletException, IOException{
		Cookie[] cookies=request.getCookies();
		Cookie cookie = null;
		for(int i=0;i<cookies.length;i++) {
			if(cookies[i].getName().equals(cookiename)) {
				cookie = cookies[i];
			}
		}
		return cookie; //쿠키 객체 반환
	}
}
