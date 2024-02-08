package com.hk.board.service;

import java.util.List;

import com.hk.board.command.InsertAskCommand;
import com.hk.board.command.ReplyAskCommand;
import com.hk.board.command.UpdateAskCommand;
import com.hk.board.dtos.AskDto;


public interface IAskService {
	//문의하기 글목록
	public List<AskDto> getAskList(String pnum);

	//1-2.페이지수 구하기
	public int getPCount() ;
			
	//상세보기
	public AskDto detailAskBoard(int ask_seq);
	
	public void insertAskBoard(InsertAskCommand insertAskCommand);
	
	//수정하기
	public boolean updateAskBoard(UpdateAskCommand updateAskCommand);
	
	//삭제하기
	public boolean mulDelAsk(String[] chks);
	
	//조회수
	public boolean readCount(int ask_seq);
	
	//댓글목록
	public List<AskDto> replyList(int ask_refer);
	
	//답글추가하기
	public boolean replyBoard(ReplyAskCommand replyAskCommand);

}
