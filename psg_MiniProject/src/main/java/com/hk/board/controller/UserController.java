package com.hk.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.board.command.AddUserCommand;
import com.hk.board.command.DelUserCommand;
import com.hk.board.command.LoginCommand;
import com.hk.board.command.UpdateUserCommand;
import com.hk.board.dtos.BoardDto;
import com.hk.board.dtos.UserDto;
import com.hk.board.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;


@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@GetMapping(value = "/addUserForm")
	public String addUserForm(Model model) {
		System.out.println("회원가입폼이동");
		
		//회원가입 폼에서 addUserCommand객체를 사용하는 코드가 작성되어있어서
		//null일 경우 오류거 발생하기때문에 빈거라도 보내줘야함
		model.addAttribute("addUserCommand", new AddUserCommand());
		
		return "user/addUserForm";
		
	}
	
	@PostMapping(value = "/addUser")
	public String addUser(@Validated AddUserCommand addUserCommand,BindingResult result,Model model) {
		if(result.hasErrors()) {
			System.out.println("회원가입 유효값 오류");
			return "user/addUserForm";
		}
		
		try {
			userService.addUser(addUserCommand);
			System.out.println("회원가입 성공");
			return "redirect:/"; //home으로 이동
		} catch (Exception e) {
			System.out.println("회원가입 실패");
			e.printStackTrace();
			
			return "redirect:addUserForm";
		}
		
	}
	
	@ResponseBody
	@GetMapping(value = "/idChk")
	public Map<String,String> idChk(String id){
		System.out.println("Id중복체크");
		String resultId=userService.idChk(id);
		//json객체로 보내기 위해서 Map에 담아서 보냄
		//text라면 그냥 String으로 보내도됨
		Map<String,String>map=new HashMap<>();
		map.put("id", resultId);
		
		return map;
	}
	
	@GetMapping(value = "/login")
	public String loginForm(Model model) {
		System.out.println("로그인폼으로 이동");
		
		model.addAttribute("loginCommand", new LoginCommand());
		
		return "/user/login";
	}
	
	@PostMapping(value = "/login")
	public String login(@Validated LoginCommand loginCommand,BindingResult result,Model model,HttpServletRequest request) {
		System.out.println("로그인하기");
		
		if(result.hasErrors()) {
			System.out.println("로그인 유효값 오류");
			return "user/login";
		}
		
		String path = userService.login(loginCommand, request,model);
		
		return path;
	}
	
	@GetMapping(value="/logout")
	public String logout(HttpServletRequest request) {
		System.out.println("로그아웃");
		request.getSession().invalidate();
		return "redirect:/";
	}
	
	@GetMapping(value="/myInfo")
	public String myInfo(HttpServletRequest request,String id,Model model) {
		System.out.println("나의정보 보여주기");
		UserDto dto = userService.myInfo(id);
		model.addAttribute("dto", dto);
		return "/user/myInfo";
	}
	
	@GetMapping(value="/updateUserForm")
	public String updateUserForm(Model model,String id) {
		System.out.println("정보 수정폼으로 이동");
		UserDto dto = userService.myInfo(id);
		model.addAttribute("dto", dto);
		model.addAttribute("updateUserCommand", new UpdateUserCommand());
		return "/user/updateUserForm";
	}
	
	@PostMapping(value="/updateUser")
	public String updateUser(@Validated UpdateUserCommand updateUserCommand,BindingResult result,Model model,String id) {
		System.out.println("정보 수정");
		
		if(result.hasErrors()) {
			System.out.println("정보수정 유효값 오류");
			
			UserDto dto = userService.myInfo(updateUserCommand.getId());
			model.addAttribute("dto", dto);
			
			return "user/updateUserForm";
		}
		
		try {
			userService.updateUser(updateUserCommand);
			System.out.println("정보수정 성공");
			return "redirect:/user/myInfo?id="+updateUserCommand.getId(); 
		} catch (Exception e) {
			System.out.println("정보수정 실패");
			e.printStackTrace();
			
			return "redirect:/user/updateUserForm?id="+updateUserCommand.getId(); 
		}
	}
	
	@PostMapping(value="/deleteUser")
	public String deleteUser(String id,HttpServletRequest request) {
		System.out.println("회원탈퇴");
		
		userService.deleteUser(id);
		request.getSession().invalidate();
		
		return "redirect:/";
	}
	
	@GetMapping(value="/getAllList")
	public String getAllList(Model model) {
		System.out.println("전체 회원 조회");
		
		List<UserDto> list = userService.getAllList();
		model.addAttribute("delUserCommand", new DelUserCommand());
		model.addAttribute("list", list);
		
		return "user/getAllList";
	}
	
	@GetMapping(value="/userDetail")
	public String userDetail(Model model, String id) {
		System.out.println("회원 개인정보 조회");
		
		UserDto dto = userService.userDetail(id);
		model.addAttribute("dto", dto);
		return "user/userDetail";
	}
	
	@GetMapping(value="/updateUserRoleForm")
	public String updateUserRoleForm(Model model,String id) {
		
		System.out.println("회원정보 수정폼");
		
		UserDto dto = userService.userDetail(id);
		model.addAttribute("dto", dto);
		
		return "user/updateUserRoleForm";
	}
	
	@PostMapping(value="/updateUserRole")
	public String updateUserRole(String id,String role,String enabled) {
		System.out.println("회원 정보 수정");
		System.out.println(id+role+enabled);
		try {
			userService.updateUserRole(id,role,enabled);
			System.out.println("정보수정 성공");
			return "redirect:/user/userDetail?id="+id; 
		} catch (Exception e) {
			System.out.println("정보수정 실패");
			e.printStackTrace();
			
			return "redirect:/user/updateUserRoleForm?id="+id; 
		}
	}
	
	
	@RequestMapping(value="/mulDel",method = {RequestMethod.POST,RequestMethod.GET})
	public String mulDel(@Validated DelUserCommand delUserCommand
						 ,BindingResult result
			             , Model model) {
		if(result.hasErrors()) {
			System.out.println("최소하나 체크하기");
			List<UserDto> list=userService.getAllList();
			model.addAttribute("list", list);
			return "redirect:/user/getAllList";
		}
		userService.mulDel(delUserCommand.getChks());
		System.out.println("회원강제탈퇴");
		return "redirect:/user/getAllList";
	}
}










