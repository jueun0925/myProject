package com.hk.board.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartRequest;

import com.hk.board.command.DeleteBoardCommand;
import com.hk.board.command.InsertBoardCommand;
import com.hk.board.command.ReplyBoardCommand;
import com.hk.board.command.UpdateBoardCommand;
import com.hk.board.dtos.BoardDto;
import com.hk.board.dtos.FileBoardDto;
import com.hk.board.mapper.BoardMapper;
import com.hk.board.mapper.FileMapper;

import jakarta.servlet.http.HttpServletRequest;


@Service
public class BoardService implements IBoardService{
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private FileMapper fileMapper;
	
	@Autowired
	private FileService fileService;
	
	//자유게시판 글목록
	@Override
	public List<BoardDto> boardList(String pnum){
		
		Map<String, String>map=new HashMap<>();
		map.put("pnum", pnum);

		return boardMapper.boardList(map);
	}
	
	//페이지수구하기
	@Override
	public int getPCount() {
		
		return boardMapper.getPCount();
	}
	
	//상세보기
	public BoardDto detailBoard(int board_seq) {
		
		return boardMapper.detailBoard(board_seq);
	}
	
	//글추가, 파일업로드 및 파일정보 추가
	@Override
	@Transactional //여러작업들어갈때 처리
	public void insertBoard(InsertBoardCommand insertBoardCommand, MultipartRequest multipartRequest, HttpServletRequest request) throws IllegalStateException, IOException {
			
		//command : UI ->dto:DB 데이터 옮겨담기
		BoardDto boardDto = new BoardDto();
		boardDto.setId(insertBoardCommand.getId());
		boardDto.setTitle(insertBoardCommand.getTitle());
		boardDto.setContent(insertBoardCommand.getContent());
		
		//새글을 추가할때 파라미터로 전달된 boardDto객체에 자동으로
		//증가된 board_seq값이 저장됨
		boardMapper.insertBoard(boardDto); //새글 추가
												//isEmpty:비어있으면 true 
		if(!multipartRequest.getFiles("filename").get(0).isEmpty()) {//첨부된 파일이 있으면 
			//상대경로 지정할거면 HttpservletRequest필요
			//파일저장경로 설정 : 절대경로,상대경로
			String filepath = request.getSession().getServletContext().getRealPath("upload");
			System.out.println("파일저장경로:"+filepath);
			
			//파일업로드 작업은 FileService쪽에서 업로드하고 업로드된 파일정보 반환
			List<FileBoardDto> uploadFileList=fileService.uploadFiles(filepath, multipartRequest);
			
			//파일정보를 DB에 추가
			//글추가할때 board_seq 증가된값 --> file정보를 추가할때 사용
			///Testboard:board_seq Pk       board_seq Fk 
			for(FileBoardDto fDto:uploadFileList) {			//증가된 board_seq값을 넣는다
				fileMapper.insertFileBoard(new FileBoardDto(0,boardDto.getBoard_seq(), fDto.getOrigin_filename(), fDto.getStored_filename()));
			}
		}
	}
	
	//수정하기
	@Override
	public boolean updateBoard(UpdateBoardCommand updateBoardCommand) {
		BoardDto dto = new BoardDto();
		dto.setBoard_seq(updateBoardCommand.getBoard_seq());
		dto.setTitle(updateBoardCommand.getTitle());
		dto.setContent(updateBoardCommand.getContent());
		
		return boardMapper.updateBoard(dto);
	}
	
	//삭제하기
	@Override
	public boolean mulDel(String[] chks) {
		return boardMapper.mulDel(chks);
	}
	
	//조회수
	@Override
	public boolean readCount(int board_seq) {
		return boardMapper.readCount(board_seq);
	}
	
	//답글목록
	@Override
	public List<BoardDto> replyList(int refer) {	
		return boardMapper.replyList(refer);
	}

	//답글추가
	@Transactional(propagation =  Propagation.REQUIRED) //선언적
	@Override
	public boolean replyBoard(ReplyBoardCommand replyBoardCommand) {
		int count=0;
		BoardDto boardDto = new BoardDto();
		boardDto.setBoard_seq(replyBoardCommand.getBoard_seq());
		boardDto.setId(replyBoardCommand.getId());
		boardDto.setContent(replyBoardCommand.getReply());
		
		boardMapper.replyUpdate(boardDto); //update문
		count=boardMapper.replyInsert(boardDto); //insert문
		return count>0?true:false;
	}
	
}
