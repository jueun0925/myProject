package com.hk.board.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.hk.board.dtos.FileBoardDto;
import com.hk.board.mapper.FileMapper;

@Service
public class FileService {
	@Autowired
	private FileMapper fileMapper;
	
	//파일업로드하기
	public List<FileBoardDto> uploadFiles(String uploadPath,MultipartRequest multipartRequest) throws IllegalStateException, IOException{
		
		//여러개의 파일들을 List에 담는 코드
		List<MultipartFile> multipartFiles=multipartRequest.getFiles("filename");
		//업로드된 파일들의 정보(원본명,저장명)를 담아줄 List
		List<FileBoardDto> uploadFileList= new ArrayList<>();
		
		for(MultipartFile multipartFile:multipartFiles) {
			//원본파일명 구하기
			String origin_filename=multipartFile.getOriginalFilename();
			
			//저장파일명 구하기: 32자리생성(1234567812345678~.txt)
			String stored_filename=UUID.randomUUID()
			+origin_filename.substring(origin_filename.indexOf("."));
			
			//파일저장 경로 구하기(파일명을 바꾸기위한 작업)
			String fileuploadUrl=uploadPath+"/"+stored_filename;
			
			multipartFile.transferTo(new File(fileuploadUrl));//upload실행
			
			//각각의 파일정보를 list에 저장하는 코드
			uploadFileList.add(new FileBoardDto(0,0,origin_filename,stored_filename));
		}
		
		return uploadFileList;
	}
}
