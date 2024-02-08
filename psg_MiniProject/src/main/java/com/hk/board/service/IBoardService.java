package com.hk.board.service;

import java.io.IOException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartRequest;

import com.hk.board.command.InsertBoardCommand;
import com.hk.board.command.ReplyBoardCommand;
import com.hk.board.command.UpdateBoardCommand;
import com.hk.board.dtos.BoardDto;
import com.hk.board.dtos.FileBoardDto;

import jakarta.servlet.http.HttpServletRequest;

public interface IBoardService{
		//자유게시판 글목록
		public List<BoardDto> boardList(String pnum);
	
		//1-2.페이지수 구하기
		public int getPCount() ;
				
		//상세보기
		public BoardDto detailBoard(int board_seq);
		
		public void insertBoard(InsertBoardCommand insertBoardCommand, MultipartRequest multipartRequest, HttpServletRequest request)throws IllegalStateException, IOException;
		
		//수정하기
		public boolean updateBoard(UpdateBoardCommand updateBoardCommand);
		
		//삭제하기
		public boolean mulDel(String[] chks);
		
		//조회수
		public boolean readCount(int board_seq);
		
		//댓글목록
		public List<BoardDto> replyList(int refer);
		
		//답글추가하기
		public boolean replyBoard(ReplyBoardCommand replyBoardCommand);
}
