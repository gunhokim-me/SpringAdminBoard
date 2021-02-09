package kr.or.ddit.test.login.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import kr.or.ddit.user.test.config.WebTestConfig;

public class LoginControllerTest extends WebTestConfig {

	@Test
	public void LoginTest() throws Exception {
		mock.perform(get("/login"))
					.andExpect(status().isOk())
					.andExpect(view().name("login"));
	}
}
