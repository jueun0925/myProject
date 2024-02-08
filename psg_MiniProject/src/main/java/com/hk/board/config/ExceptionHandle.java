package com.hk.board.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;



public class ExceptionHandle {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//execption이 발생하면 처리해주는 매소드
	@ExceptionHandler(Exception.class)
	private String handleException(Exception e,Model model) {
		logger.error("Exception발생", e.getMessage());
		model.addAttribute("mgs", "오류가 발생하여 확인중입니다.");
		
		return "error";
	}
}
