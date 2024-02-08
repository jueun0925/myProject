package com.hk.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.hk.board.dtos.BoardDto;

@Mapper
public interface BoardMapper {
	
	//게시판 목록(페이징처리x)
	public List<BoardDto> boardList(Map<String,String> map);

	//페이지수 구하기
	public int getPCount() ;
	
	//게시판 상세보기
	public BoardDto detailBoard(int board_seq);
	
	//글추가
	public boolean insertBoard(BoardDto dto);
	
	//글수정
	public boolean updateBoard(BoardDto dto);
	
	//글삭제
	public boolean mulDel(String[] chks);
	
	//조회수
	public boolean readCount(int board_seq);
	
	//댓글목록
	public List<BoardDto> replyList(int refer);
	
	//7.답글추가하기: update문, insert문 실행 --> 트랜잭션 처리 필요
	public int replyUpdate(BoardDto dto);
	public int replyInsert(BoardDto dto);
}
