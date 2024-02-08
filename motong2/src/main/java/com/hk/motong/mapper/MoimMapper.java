package com.hk.motong.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.hk.motong.dtos.MoimDto;
import com.hk.motong.dtos.UserDto;


@Mapper
public interface MoimMapper {
   
	public int getSubsCount();
	
	public List<MoimDto> subsMoim(Map<String, Integer> map);
   
	public List<String> moimLeader(int user_seq);

	public String moimName(int account_seq);
   
   //모임리스트
   public int getPCount();
   
   public List<MoimDto> getMoimList(String pnum);
   
   public List<MoimDto> getMyAccountList(int user_seq);
   
   //모임 추가할때 실행될 작업들
   public boolean addMoim(MoimDto dto);
   public int getMoimSeq(int account_seq);
   public boolean addUserMoim(Map<String, Integer> map);
   public boolean updateAcDelflag(int account_seq);
   
   // 모임 검색 기능
   public List<MoimDto> moimSearch(String searchData);

   // 모임 상세
   public MoimDto getMoim(int moim_seq);
   
   //모임원목록 페이징처리용 ////////////////
   public int getGetMoimPCount(int moim_seq);
   
   // 모임원 목록 페이징처리 /////
   public List<UserDto> moimUser(Map<String, Integer> map);
   
   // 모임원 userseq만 받아옴
   public List<Integer> moimUserSeq(int moim_seq);
}