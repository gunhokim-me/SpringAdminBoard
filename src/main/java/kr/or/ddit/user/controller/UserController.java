package kr.or.ddit.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@RequestMapping("user")
@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(path="login", method = {RequestMethod.POST})
	public String loginViewPost(UserVo vo, HttpSession session) {
		int cnt = userService.userlogin(vo);
		if(cnt == 1) {
			UserVo vo2 = userService.selectUser(vo.getUserid());
			session.setAttribute("S_USER", vo2);
			return "redirect:pagingUser";
		}else {
			return "login";
		}
	}
	
	@RequestMapping("pagingUser")
	public String pagingUser(@RequestParam(defaultValue = "1") int page, 
							 @RequestParam(defaultValue = "5") int pageSize,
							 @RequestParam(defaultValue = "") String type,
							 @RequestParam(defaultValue = "") String search, Model model) {
		PageVo pageVo = new PageVo(page, pageSize); 
		model.addAttribute("pagesize", pageSize);
		if(!type.equals("") && !search.equals("")) {
			pageVo.setVal(search);
			switch(type) {
			case "i":
				model.addAllAttributes(userService.idFindUser(pageVo));
				break;
			case "n":
				model.addAllAttributes(userService.nameFindUser(pageVo));
				break;
			case "a":
				model.addAllAttributes(userService.aliasFindUser(pageVo));
				break;
			}
			model.addAttribute("type",type);
			model.addAttribute("keyword",search);
		}else {
			model.addAllAttributes(userService.selectPagingUser(pageVo));
		}
		return "memberList";
	}
	
	@RequestMapping("userDetail")
	public String userDetail(String userid, Model model) {
		UserVo vo = userService.selectUser(userid);
		model.addAttribute("vo", vo);
		return "memberDetail";
	}
	
	@RequestMapping(path="userModify", method = RequestMethod.POST)
	public String userModify(String userid, Model model) {
		UserVo vo = userService.selectUser(userid);
		model.addAttribute("vo", vo);
		return "memberModify";
	}
	
	@RequestMapping(path="modifySubmit", method = RequestMethod.POST)
	public String userModifySubmit(UserVo vo, MultipartFile picture,  Model model) {
		String realfilename="";
		if(picture.getSize() == 0) {
			vo.setFilename(userService.selectUser(vo.getUserid()).getFilename());
			vo.setRealfilename(userService.selectUser(vo.getUserid()).getRealfilename());
		}else {
			realfilename = "D:/upload/" + UUID.randomUUID().toString() + "." + picture.getOriginalFilename().substring(picture.getOriginalFilename().lastIndexOf(".")+1);
			vo.setRealfilename(realfilename);
			vo.setFilename(picture.getOriginalFilename());
		}
		int cnt = userService.modifyUser(vo);
		
		if(cnt == 1 ) {
			try {
				picture.transferTo(new File(realfilename));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
			return "redirect:userDetail?userid=" + vo.getUserid();
		}
		return "redirect:userModify?userid=" + vo.getUserid();
	}
	
	@RequestMapping(path="deleteUser")
	public String deleteUser(String userid) {
		int cnt = userService.deleteUser(userid);
		if(cnt == 1 ) {
			return "redirect:pagingUser";
		}
		return "redirect:userModify?userid=" + userid;
	}
	
	@RequestMapping(path="registUser")
	public String registUser() {
		return "memberRegist";
	}
	
	@ResponseBody
	@RequestMapping(path="idcheck")
	public int idcheck(String id) {
		UserVo vo = userService.selectUser(id);
		int res = 0;
		if(vo != null) {
			res = 1;
		}else {
			res = 0;
		}
		return res;
	}
	
	@RequestMapping(path="registSubmit",  method = {RequestMethod.POST})
	public String userRegist(@Valid UserVo userVo, BindingResult result, MultipartFile picture, RedirectAttributes ar) {
		
		if(result.hasErrors()) {
			return "user/registUser";
		}
		
		if(picture != null) {
			String realfilename = "D:/upload/" + UUID.randomUUID().toString() + "." + picture.getOriginalFilename().substring(picture.getOriginalFilename().lastIndexOf(".")+1);
			userVo.setRealfilename(realfilename);
			userVo.setFilename(picture.getOriginalFilename());
			try {
				picture.transferTo(new File(realfilename));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		int cnt = userService.registUser(userVo);
		if(cnt == 1) {
			return "redirect:/user/pagingUser";
		}else {
			ar.addFlashAttribute("vo2",userVo);
			return "redirect:/user/userRegist";
		}
	}
	
	
	
	
	@RequestMapping("profile")
	public void profile(HttpServletResponse resp, String userid, HttpServletRequest req) {
		resp.setContentType("image");
		
		UserVo vo = userService.selectUser(userid);
		String path ="";
		
		if(vo.getRealfilename() == null) {
			path = req.getServletContext().getRealPath("/image/unknown.jpg");
		}else {
			
			path = vo.getRealfilename();
		}
		
		try {
			FileInputStream fis = new FileInputStream(path);
			ServletOutputStream sos = resp.getOutputStream();
			
			byte[] buff = new byte[512];
			
			while(fis.read(buff) != -1 ) {
				sos.write(buff);
			}
			
			fis.close();
			sos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
