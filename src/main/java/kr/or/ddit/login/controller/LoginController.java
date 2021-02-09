package kr.or.ddit.login.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.user.service.UserService;

@Controller
public class LoginController {
	
	@RequestMapping("login")
	public String loginView() {
		return "login";
	}
}
