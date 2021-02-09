package kr.or.ddit.user.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Ignore;
import org.junit.Test;

import kr.or.ddit.user.test.config.WebTestConfig;

public class UserControllerTest extends WebTestConfig {

	@Test
	public void LoginViewTest() throws Exception {
		mock.perform(post("/user/login").param("userid", "sally").param("pass", "sallyPass"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:pagingUser"));
	}
	
	@Test
	public void LoginViewFailTest() throws Exception {
		mock.perform(post("/user/login").param("userid", "sally").param("pass", ""))
		.andExpect(status().isOk())
		.andExpect(view().name("login"));
	}
	
	@Test
	public void PagingTest() throws Exception {
		mock.perform(get("/user/pagingUser"))
		.andExpect(model().attributeExists("pagination"))
		.andExpect(model().attributeExists("userList"))
		.andExpect(model().attributeExists("pageVo"))
		.andExpect(status().isOk())
		.andExpect(view().name("memberList"));
	}
	
	@Test
	public void PagingParamTest() throws Exception {
		mock.perform(get("/user/pagingUser").param("page", "2"))
		.andExpect(model().attributeExists("pagination"))
		.andExpect(model().attributeExists("userList"))
		.andExpect(model().attributeExists("pageVo"))
		.andExpect(status().isOk())
		.andExpect(view().name("memberList"))
		.andDo(print());
	}
	
//	@Test
//	public void PagingParamIdTest() throws Exception {
//		mock.perform(get("/user/pagingUser").param("type", "i"))
//		.andExpect(model().attributeExists("pagination"))
//		.andExpect(model().attributeExists("userList"))
//		.andExpect(model().attributeExists("pageVo"))
//		.andExpect(model().attributeExists("type"))
//		.andExpect(model().attributeExists("keyword"))
//		.andExpect(status().isOk())
//		.andExpect(view().name("memberList"))
//		.andDo(print());
//	}
//	
//	@Test
//	public void PagingParamNameTest() throws Exception {
//		mock.perform(get("/user/pagingUser").param("type", "n"))
//		.andExpect(model().attributeExists("pagination"))
//		.andExpect(model().attributeExists("userList"))
//		.andExpect(model().attributeExists("pageVo"))
//		.andExpect(model().attributeExists("type"))
//		.andExpect(model().attributeExists("keyword"))
//		.andExpect(status().isOk())
//		.andExpect(view().name("memberList"));
//	}
//	
//	@Test
//	public void PagingParamAliasTest() throws Exception {
//		mock.perform(get("/user/pagingUser").param("type", "a"))
//		.andExpect(model().attributeExists("pagination"))
//		.andExpect(model().attributeExists("userList"))
//		.andExpect(model().attributeExists("pageVo"))
//		.andExpect(model().attributeExists("type"))
//		.andExpect(model().attributeExists("keyword"))
//		.andExpect(status().isOk())
//		.andExpect(view().name("memberList"));
//	}
	
	@Test
	public void userDetailTest() throws Exception{
		mock.perform(post("/user/userDetail").param("userid", "sally"))
		.andExpect(view().name("memberDetail"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void userModifyTest() throws Exception{
		mock.perform(post("/user/userModify").param("userid", "sally"))
		.andExpect(view().name("memberModify"))
		.andExpect(model().attributeExists("vo"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test @Ignore
	public void userModifySubmitTest() throws Exception{
		mock.perform(post("/user/modifySubmit").param("page", "test"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void userRegistTest() throws Exception{
		mock.perform(post("/user/registUser"))
		.andExpect(view().name("memberRegist"))
		.andExpect(status().isOk())
		.andDo(print());
	}	
	
	@Test @Ignore
	public void userRegistsubmitTest() throws Exception{
		mock.perform(post("/user/registSubmit"))
		.andExpect(view().name("/user/pagingUser"))
		.andExpect(status().isOk())
		.andDo(print());
	}	
	
	@Test @Ignore
	public void userDeleteTest() throws Exception{
		mock.perform(get("/user/deleteUser").param("userid", "sally"))
		.andExpect(view().name("redirect:pagingUser"))
		.andExpect(status().is3xxRedirection())
		.andDo(print());
	}
	
	@Test
	public void userDeleteFailTest() throws Exception{
		mock.perform(get("/user/deleteUser").param("userid", "test"))
		.andExpect(view().name("redirect:userModify?userid=test"))
		.andExpect(status().is3xxRedirection())
		.andDo(print());
	}
	

}
