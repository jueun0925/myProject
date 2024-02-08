package com.hk.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hk.board.command.InsertAskCommand;
import com.hk.board.command.ReplyAskCommand;
import com.hk.board.command.UpdateAskCommand;
import com.hk.board.dtos.AskDto;
import com.hk.board.mapper.AskMapper;

@Service
public class AskService implements IAskService{
	@Autowired
	private AskMapper askMapper;

	//문의 목록
	@Override
	public List<AskDto> getAskList(String pnum) {
		Map<String, String>map=new HashMap<>();
		map.put("pnum", pnum);
		
		return askMapper.getAskList(map);
	}

	//페이지수 구하기
	@Override
	public int getPCount() {
		
		return askMapper.getPCount();
	}

	//문의 상세보기
	@Override
	public AskDto detailAskBoard(int ask_seq) {
		
		return askMapper.detailAskBoard(ask_seq);
	}

	//문의글 추가
	@Override
	public void insertAskBoard(InsertAskCommand insertAskCommand) {
		AskDto aDto = new AskDto();
		aDto.setId(insertAskCommand.getId());
		aDto.setAsk_title(insertAskCommand.getAsk_title());
		aDto.setAsk_content(insertAskCommand.getAsk_content());
		
		askMapper.insertAskBoard(aDto);
	}

	//문의글 수정
	@Override
	public boolean updateAskBoard(UpdateAskCommand updateAskCommand) {
		AskDto aDto = new AskDto();
		aDto.setAsk_seq(updateAskCommand.getAsk_seq());
		aDto.setAsk_title(updateAskCommand.getAsk_title());
		aDto.setAsk_content(updateAskCommand.getAsk_content());
		
		return askMapper.updateAskBoard(aDto);
	}

	//문의글 삭제
	@Override
	public boolean mulDelAsk(String[] chks) {		
		return askMapper.mulDelAsk(chks);
	}

	//조회수
	@Override
	public boolean readCount(int ask_seq) {
		return askMapper.readCount(ask_seq);
	}

	//문의글 답변목록
	@Override
	public List<AskDto> replyList(int ask_refer) {
		return askMapper.replyList(ask_refer);
	}

	//문의글 답변하기
	@Transactional(propagation =  Propagation.REQUIRED) //선언적
	@Override
	public boolean replyBoard(ReplyAskCommand replyAskCommand) {
		int count=0;
		AskDto aDto = new AskDto();
		aDto.setAsk_seq(replyAskCommand.getAsk_seq());
		aDto.setId(replyAskCommand.getId());
		aDto.setAsk_title(replyAskCommand.getAsk_rTitle());
		aDto.setAsk_content(replyAskCommand.getAsk_rContent());
		
		askMapper.replyUpdate(aDto);
		count=askMapper.replyInsert(aDto);
		return count>0?true:false;
	}
	
	
}
