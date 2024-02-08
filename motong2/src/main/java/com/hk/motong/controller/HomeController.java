package com.hk.motong.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


import com.hk.motong.service.MoimService;


@Controller
public class HomeController {

	@Autowired
	MoimService moimService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	// 로그인 된 상태 main
	@GetMapping("/main")
	public String main() {
		return "main";
	}


}