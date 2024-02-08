package com.hk.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hk.board.command.AddUserCommand;
import com.hk.board.command.LoginCommand;
import com.hk.board.command.UpdateUserCommand;
import com.hk.board.dtos.UserDto;
import com.hk.board.mapper.UserMapper;
import com.hk.board.status.RoleStatus;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	//공통기능
	//회원가입
	public boolean addUser(AddUserCommand addUserCommand){
		UserDto dto = new UserDto();
		dto.setId(addUserCommand.getId());
		dto.setName(addUserCommand.getName());
		dto.setPassword(addUserCommand.getPassword());
		dto.setAddress(addUserCommand.getAddress());
		dto.setEmail(addUserCommand.getEmail());
		dto.setRole(RoleStatus.USER+"");

		return userMapper.addUser(dto);
	}
		
	//아이디 중복체크
	public String idChk(String id){
		return userMapper.idChk(id);
	}
	
	//로그인
	public String login(LoginCommand loginCommand,HttpServletRequest request,Model model) {
		UserDto dto = userMapper.login(loginCommand.getId());
		String path="home";
		if(dto!=null) {
			if(loginCommand.getPassword().equals(dto.getPassword())&&dto.getEnabled().equals("Y")) {
				System.out.println("패스워드같음 : 회원이 맞음");
				request.getSession().setAttribute("dto", dto); //회원 맞아서 session객체에 회원정보 저장한거임
				request.getSession().setMaxInactiveInterval(30*60);
				return path;
			}else if(dto.getEnabled().equals("N")){
				System.out.println("탈퇴한 회원");
				model.addAttribute("msg", "탈퇴한 회원입니다");
				path ="user/login";
			}else { //패스워드가 틀리면
				System.out.println("패스워드 틀림");
				model.addAttribute("msg", "패스워드를 확인하세요");
				path ="user/login";
				
			}
		}else { //dto가 null이면
			System.out.println("회원이 아닙니다");
			model.addAttribute("msg", "회원이 아닙니다.");
			path = "user/login";
		}
		return path;
	}
	
	
	
	//사용자기능
	//나의정보
	public UserDto myInfo(String id){
		return userMapper.myInfo(id);
	}
	
	//나의정보 수정
	public boolean updateUser(UpdateUserCommand updateUserCommand) {
		
		UserDto dto = new UserDto();
		dto.setId(updateUserCommand.getId());
		dto.setAddress(updateUserCommand.getAddress());
		dto.setEmail(updateUserCommand.getEmail());
		
		return userMapper.updateUser(dto);
	}
		
	//회원탈퇴
	public boolean deleteUser(String id) {
		return userMapper.deleteUser(id);
	}
	
	
	//관리자 기능
	//회원목록 전체조회
	public List<UserDto> getAllList(){
		return userMapper.getAllList();
	}
	
	//회원 상세조회
	public UserDto userDetail(String id) {
		return userMapper.userDetail(id);
	}
		
	//회원등급변경
	public boolean updateUserRole(String id, String role,String enabled) {
		return userMapper.updateUserRole(id,role,enabled);
	}
	
	//회원 비활성화 시키기
	public boolean mulDel(String[] chks) {
		return userMapper.mulDel(chks);
	}
	
}
