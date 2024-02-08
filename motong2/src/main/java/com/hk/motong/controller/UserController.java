package com.hk.motong.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.motong.dtos.AccountDto;
import com.hk.motong.dtos.UserDto;
import com.hk.motong.feignMapper.OpenBankingFeign;
import com.hk.motong.service.UserService;
import com.hk.motong.apidto.AccountBalanceDto;
import com.hk.motong.apidto.UserMeAccountDto;
import com.hk.motong.apidto.UserMeDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private OpenBankingFeign openBankingFeign;

	// 회원가입창 이동
	@GetMapping("/signup")
	public String signUp() {
		return "signup";
	}
	
	// 아이디 중복체크
	@ResponseBody
	@PostMapping("/idChk")
	public String idChk(Model model,@RequestParam String email) {
		System.out.println("idChk 요청");
		String resultEmail=userService.idChk(email);
		System.out.println(resultEmail);
		model.addAttribute("resultEmail",resultEmail);
		
		return resultEmail;
	}
	
	// 회원가입
	@PostMapping("/addUser")
	public String addUser(UserDto dto) {
		boolean isS=userService.addUser(dto);
		
		if(isS) {
			System.out.println("회원가입 성공");
			return "redirect:/";
		}else {
			System.out.println("회원가입 실패");
			return "error";
		}
	}
	
	// 로그인 창 이동
	@GetMapping("/signin_form")
	public String signinForm() {
		System.out.println("로그인 폼 이동");
		return "signin_form";
	}
	
	// 로그인
	@PostMapping("/login")
	public String login(Model model,UserDto dto,HttpServletRequest request ) {
		UserDto ldto=userService.loginUser(dto);
		
		if(ldto==null) {
			System.out.println("회원이 아님");
			model.addAttribute("failID", "가입되지 않은 아이디 입니다");
			model.addAttribute("ID", dto.getEmail());
			model.addAttribute("pw", dto.getPassword());
			return "signin_form";
		}else if(!ldto.getPassword().equals(dto.getPassword())){
			System.out.println("비밀번호 틀림");
			model.addAttribute("failPW", "비밀번호가 틀립니다");
			model.addAttribute("ID", dto.getEmail());
			model.addAttribute("pw", dto.getPassword());
			System.out.println(ldto.getPassword());
			System.out.println(dto.getPassword());
			return "signin_form";
		}else {
			System.out.println("회원이 맞음");
			HttpSession session=request.getSession();
			session.setAttribute("ldto", ldto);
			session.setAttribute("user_seq", ldto.getUser_seq());
			session.setMaxInactiveInterval(60*10);
			return "redirect:/main";
		}
	}
	
	//main페이지로 이동
	@GetMapping("/main")
	public String main() {
		System.out.println("메인페이지로이동");
		return "main";
	}
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		System.out.println("로그아웃");
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:/";
	}
	
	//마이페이지 창 이동
	@GetMapping("/myPage")
	public String myPage(HttpServletRequest request,String email,Model model) throws IOException, ParseException {
//		System.out.println("마이페이지로 이동");
		UserDto dto=userService.myInfo(email);
		model.addAttribute("dto", dto);
//		System.out.println(dto);
		if(dto.getUseraccesstoken() ==null || dto.getUseraccesstoken()=="null" || dto.getUseraccesstoken() == "") {
			System.out.println(dto.getUseraccesstoken());
			model.addAttribute("exitAccessToken", "사용자 인증 안된 회원");
		}else {
			//DB 계좌 리스트 불러오기
			List<AccountDto> acList = userService.getAccountList(dto.getUser_seq());
			
			//계좌 리스트 불러오기 - 잔액 새로 가져올라고 ㅜㅜ
			UserMeDto userMeDto = getAccount(request,dto.getUseraccesstoken());
			List<UserMeAccountDto> list = userMeDto.getRes_list();
			
			////////////////////////////////////////////////////////////////////////
			//이 부분 잔액업로드 하는 부분 다시 한번만 살펴보기
			for (int j = 0; j < acList.size(); j++) {
				for (int i = 0; i < list.size(); i++) {
					String fintech_use_num = list.get(i).getFintech_use_num();
					//계좌 잔액 정보 들은 dto 가져오기
					AccountBalanceDto accountBalanceDto = getAccountBalanceDto(request,dto.getUseraccesstoken(),fintech_use_num);
					
					if(acList.get(j).getFintech_use_num().equals(fintech_use_num)) {
						int balance_amt = Integer.parseInt(accountBalanceDto.getBalance_amt());
//						System.out.println("balance_amt"+balance_amt);
						int account_seq = acList.get(j).getAccount_seq();
//						System.out.println("account_seq:"+account_seq);
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("balance_amt", balance_amt);
						map.put("account_seq",account_seq);
						userService.updateBalanceAmt(map);
					}
				}
				
			}
			List<AccountDto> aList = userService.getAccountList(dto.getUser_seq());
			System.out.println(aList);
			//이제 잔액이랑 계좌 목록 띄울거 보내시오~
			model.addAttribute("aList", aList);
		}
		
		
		return "myPage";
	}
	
	//나의 정보 수정 폼
	@GetMapping("/updateMyInfoForm")
	public String updateMyInfoForm(String email,Model model) {
		System.out.println("나의 정보 수정 폼 이동");
		UserDto dto=userService.myInfo(email);
		model.addAttribute("dto", dto);
		return "updateMyInfoForm";
	}	
	
		
	//나의 정보 수정
	@PostMapping("/updateMyInfo")
	public String updateMyInfo(UserDto dto) {
		System.out.println("나의 정보 수정");
		userService.updateMyInfo(dto);
		return "redirect:/user/myPage?email="+dto.getEmail();
	}		
		
	//회원 탈퇴
	@GetMapping("/delUser")
	public String delUser(String email) {
		System.out.println("탈퇴하기");
		userService.delUser(email);
		return "redirect:/";
	}	
		
	
	//사용자인증
	//사용자 인증을 완료하면 code들을 반환해준다.
		//반환받은 code를 이용해서 토큰 발급 요청을 진행한다.
		@ResponseBody
		@GetMapping("/authresult")
		public String authResult(HttpServletRequest request,String code) throws IOException, java.text.ParseException, ParseException {
			System.out.println("인증후 받은 code:"+code);
			
			HttpSession session=request.getSession();
						
			HttpURLConnection conn=null;// api에서 제공하는 데이터를 받기 위한 연결 객체
			JSONObject result=null; //받아온 데이터를 json으로 저장할 객체
			
			//인증받고 얻은 code를 통해 토큰을 요청하여 발급받는다.
			URL url=new URL("https://testapi.openbanking.or.kr/oauth/2.0/token?"
					       +"code="+code
					       +"&client_id=4987e938-f84b-4e23-b0a2-3b15b00f4ffd"
					       +"&client_secret=3ff7570f-fdfb-4f9e-8f5a-7b549bf2adec"
					       +"&redirect_uri=http://localhost:8070/user/authresult"
					       +"&grant_type=authorization_code"
							);
			
			conn=(HttpURLConnection)url.openConnection();
			conn.setRequestMethod("POST"); //post로 요청 설정
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setDoOutput(true); //데이터를 가져온다면 true로 설정한다.
			
			// 데이터를 실제로 가져오는 작업
			BufferedReader br=new BufferedReader(
					         new InputStreamReader(conn.getInputStream(),"utf-8"));
			
			StringBuilder response=new StringBuilder();//데이터를 저장할 객체
			String responseLine=null;
			while((responseLine=br.readLine())!=null) {
//				System.out.println(responseLine);
				response.append(responseLine.trim());//데이터를 문자열에 추가
			}
			
			// json 형태의 텍스트 데이터를 json객체로 변환하는 작업
			result=(JSONObject)new JSONParser().parse(response.toString());
			
			//json객체에서 전달받은 값을 가져오기
			String access_token=result.get("access_token").toString();
			String refresh_token=result.get("refresh_token").toString();
			String user_seq_no=result.get("user_seq_no").toString();
			
			System.out.println("access_token:"+access_token);
			System.out.println("refresh_token:"+refresh_token);
			System.out.println("user_seq_no:"+user_seq_no);
			
      		UserDto dto = (UserDto)session.getAttribute("ldto");

			dto.setUseraccesstoken(access_token);
			dto.setUserrefreshtoken(refresh_token);
			dto.setUserseqno(Integer.parseInt(user_seq_no));
			
			//DB에 계좌 정보 등록하는 과정
			//계좌 정보 가져오기
			UserMeDto userMeDto = getAccount(request, access_token);
			List<UserMeAccountDto> list = userMeDto.getRes_list();
			
			String fintech_use_num = list.get(0).getFintech_use_num();
			String account_num_masked = list.get(0).getAccount_num_masked();
			System.out.println(list.get(0));
			
			//계좌 잔액 정보 들은 dto 가져오기
			AccountBalanceDto accountBalanceDto = getAccountBalanceDto(request,access_token,fintech_use_num);
			System.out.println(accountBalanceDto);
			AccountDto aDto = new AccountDto();
			aDto.setUser_seq(dto.getUser_seq());
			aDto.setFintech_use_num(fintech_use_num);
			aDto.setAccount_num_masked(account_num_masked);
			aDto.setBalance_amt(Integer.parseInt(accountBalanceDto.getBalance_amt()));
			aDto.setBank_name(list.get(0).getBank_name());
			System.out.println(aDto);
			
			userService.registTokenAccount(dto, aDto);
			
			String str="<script type='text/javascript'>"						  
						+"     self.close();"
						+"</script>";
			
			return str;
		}
		
		//계좌 정보 가져오기
		public UserMeDto getAccount(HttpServletRequest request,String access_token) throws IOException, ParseException {
				
			System.out.println("DB에 계좌 등록하기 위한 계좌들 목록 가져오기");
			//사용자 일련 번호를 가져오기 위해 session객체 구함
			HttpSession session=request.getSession();
			UserDto ldto=(UserDto)session.getAttribute("ldto");
			int userSeqNo=ldto.getUserseqno();//사용자 일련번호
			String useraccesstoken=access_token;//접근할 토큰
			
			//json값들을 userMeDto에 저장
			UserMeDto userMeDto=openBankingFeign.requestUserMe("Bearer "+useraccesstoken, userSeqNo+"");
				
			return userMeDto;
			
		}
		
		//잔액 정보 들어있는 dto 가져오기
		public AccountBalanceDto getAccountBalanceDto(HttpServletRequest request,String access_token,String fintech_use_num) throws IOException, ParseException {
						
//			System.out.println("잔액정보 들어있는 Dto가져오기");
			String useraccesstoken=access_token;//접근할 토큰
			String bank_tran_id = "M202201886U" + createNum();
			String tran_dtime = getDateTime();
			
			//json값들을 userMeDto에 저장
			AccountBalanceDto accountBalanceDto=openBankingFeign.requestAccountBalance("Bearer "+useraccesstoken, bank_tran_id,fintech_use_num,tran_dtime);

			return accountBalanceDto;
					
		}
		
		//이용기관부여번호 9자리를 생성하는 메서드
		public String createNum() {
			String createNum="";
			for (int i = 0; i < 9; i++) {
				createNum+=((int)(Math.random()*10))+"";
			}
//			System.out.println("이용기관부여번호9자리생성:"+createNum);
			return createNum;
		}
		
		//현재시간 구하는 메서드
		public String getDateTime() {
			LocalDateTime now = LocalDateTime.now(); //현재시간구하기
			String formatNow = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
			return formatNow;
		}
}