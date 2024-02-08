package com.hk.motong.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.hk.motong.utils.Paging;
import com.hk.motong.dtos.MoimDto;
import com.hk.motong.dtos.UserDto;
import com.hk.motong.service.MoimService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/moim")
public class MoimController {
	
	@Autowired
	private MoimService moimService;
	
	@GetMapping("/moimlist")
	public String moimList(Model model, String pnum,HttpServletRequest request) {		
		System.out.println("moimlist요청");
		
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
				
		List<MoimDto>list=moimService.getMoimList(pnum);
		model.addAttribute("list", list);
		System.out.println(list);
		
		//페이지 수 구하기 
		int pcount=moimService.getPCount();
		model.addAttribute("pcount", pcount);
		
		//---페이지에 페이징 처리 기능 추가
		//필요한 값: 페이지수, 페이지번호, 페이지범위(페이지수)
		Map<String, Integer>map=Paging.pagingValue(pcount, pnum, 10);
		model.addAttribute("pMap", map);
				
		return "moimlist";
	}
	
	@GetMapping("/newmoim")
	public String newmoim(Model model, HttpServletRequest request) {
		HttpSession session=request.getSession();
		
		UserDto userDto =  (UserDto)session.getAttribute("ldto");
		int user_seq= userDto.getUser_seq();
		
		List<MoimDto>acList=moimService.getMyAccountList(user_seq);
		System.out.println("ac리스트");
		System.out.println(acList);
		model.addAttribute("acList", acList);
        return "newmoim";
	}
	
	@PostMapping("/addMoim")
	public String addMoim(int leader,String mname, int account_seq,int moimPin) { //account = accoun_num_masked
		MoimDto mdto = new MoimDto();
		mdto.setLeader(leader);
		mdto.setMname(mname);
		mdto.setAccount_seq(account_seq);
		mdto.setPin(moimPin);
		System.out.println("addMoim : "+mdto);
		moimService.addMoim(mdto);
		
		int moim_seq = moimService.getMoimSeq(account_seq);
		System.out.println(moim_seq);
		
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("user_seq", leader);
		map.put("moim_seq", moim_seq);
		System.out.println(map);
		moimService.addUserMoim(map);
		
		moimService.updateAcDelflag(account_seq);
		return "redirect:/moim/moimlist";
	}
	
	// 모임 검색 기능
	@PostMapping("/research")
	public String moimSearch(String searchData,Model model,HttpServletRequest request) {		
		List<MoimDto>list=moimService.moimSearch(searchData);
		model.addAttribute("list", list);
		System.out.println(list);
			
		return "moimlist";
	}
	
	// 모임 상세 보기
		@GetMapping("/getMoim")
		public String getMoim(int moim_seq,String pnum, Model model,HttpServletRequest request) {
			System.out.println(moim_seq);
			System.out.println("모임 상세 페이지로 이동");
			MoimDto dto=moimService.getMoim(moim_seq);
			
			
			//페이징처리시작
			HttpSession session=request.getSession();
		    
			if(pnum==null) {
				pnum=(String)session.getAttribute("pnum");//현재 조회중인 페이지번호
			}else {
				//새로 페이지를 요청할 경우 세션에 저장
				session.setAttribute("pnum", pnum);
			}
			//---페이지번호 유지를 위한 코드 종료-------------------
					
			// 모임원 리스트
			Map<String,Integer> map = new HashMap<String, Integer>();
			map.put("pnum", Integer.parseInt(pnum));
			map.put("moim_seq", moim_seq);
			List<UserDto> list=moimService.moimUser(map);
			System.out.println(list);
			model.addAttribute("list",list);
			
			//페이지 수 구하기 
			int pcount=moimService.getGetMoimPCount(moim_seq);
			model.addAttribute("pcount", pcount);
			
			Map<String, Integer>pmap=Paging.pagingValue(pcount, pnum, 10);
			model.addAttribute("pMap", pmap);
			
			//페이징처리끝 //////////////////////////
			
			
			// 모임원 user_seq 만 받는 리스트
			List<Integer> seqList=moimService.moimUserSeq(moim_seq);
			System.out.println(seqList);
			model.addAttribute("seqList",seqList);
					
			System.out.println(dto);
			model.addAttribute("dto",dto);
			
			return "getMoim";
		}
		
	// 모임 가입 (모임원 추가)
	@PostMapping("/addUserMoim")
	public String addUserMoim(int user_seq, int moim_seq, HttpServletRequest request) {
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("user_seq", user_seq);
		map.put("moim_seq", moim_seq);
		System.out.println(map);
		moimService.addUserMoim(map);
		
		request.setAttribute("url","/moim/getMoim?moim_seq="+moim_seq);
		return "alert";
	}

	
}