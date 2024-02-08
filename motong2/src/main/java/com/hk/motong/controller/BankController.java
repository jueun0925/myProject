package com.hk.motong.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hk.motong.dtos.AccountDto;
import com.hk.motong.dtos.MoimDto;
import com.hk.motong.dtos.UserDto;
import com.hk.motong.service.BankService;
import com.hk.motong.service.MoimService;
import com.hk.motong.utils.Paging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/bank")
public class BankController {

   @Autowired
   private MoimService moimService;
   @Autowired
   private BankService bankService;
   
	@GetMapping("/my_moim")
	   public String bank_main(Model model,String pnum, HttpServletRequest request) {
	      System.out.println("회비관리 페이지 이동");
	      
	      
	    //---페이지번호 유지를 위한 코드----------------------
			//페이지번호를 전달하지 않으면 세션에 저장된 페이지번호를 사용
			HttpSession session=request.getSession();
			UserDto ldto = (UserDto)session.getAttribute("ldto");
		    int user_seq= ldto.getUser_seq();
		    
			if(pnum==null) {
				pnum=(String)session.getAttribute("pnum");//현재 조회중인 페이지번호
			}else {
				//새로 페이지를 요청할 경우 세션에 저장
				session.setAttribute("pnum", pnum);
			}
			//---페이지번호 유지를 위한 코드 종료-------------------
					
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("user_seq", user_seq);
			map.put("pnum", Integer.parseInt(pnum));
		    List<MoimDto> list=moimService.subsMoim(map);
		    model.addAttribute("list",list);
			
			//페이지 수 구하기 
			int pcount=moimService.getSubsCount();
			model.addAttribute("pcount", pcount);
			
			//---페이지에 페이징 처리 기능 추가
			//필요한 값: 페이지수, 페이지번호, 페이지범위(페이지수)
			Map<String, Integer>pmap=Paging.pagingValue(pcount, pnum, 10);
			model.addAttribute("pMap", pmap);
					
			
	      return "my_moim";
	   }
   @GetMapping("/bank_moim")
   public String bank_moim(int account_seq, Model model) {
	   System.out.println(account_seq);
	   System.out.println("모임계좌 페이지로 이동");
	   AccountDto dto=bankService.getmoimAccount(account_seq);
	   System.out.println(dto);
	   model.addAttribute("dto",dto);
	   
	   // 모임 이름 뿌리기
	   String mname=moimService.moimName(account_seq);
	   System.out.println(mname);
	   model.addAttribute("mname",mname);

	   return "bank/bank_moim";
   }
   
}