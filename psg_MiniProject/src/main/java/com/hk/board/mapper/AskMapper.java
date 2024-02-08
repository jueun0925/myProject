package com.hk.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.hk.board.dtos.AskDto;

@Mapper
public interface AskMapper {
		//게시판 목록(페이징처리x)
		public List<AskDto> getAskList(Map<String,String> map);

		//페이지수 구하기
		public int getPCount() ;
		
		//게시판 상세보기
		public AskDto detailAskBoard(int ask_seq);
		
		//글추가
		public boolean insertAskBoard(AskDto aDto);
		
		//글수정
		public boolean updateAskBoard(AskDto aDto);
		
		//글삭제
		public boolean mulDelAsk(String[] chks);
		
		//조회수
		public boolean readCount(int ask_seq);
		
		//댓글목록
		public List<AskDto> replyList(int ask_refer);
		
		//7.답글추가하기: update문, insert문 실행 --> 트랜잭션 처리 필요
		public int replyUpdate(AskDto aDto);
		public int replyInsert(AskDto aDto);
	
}

