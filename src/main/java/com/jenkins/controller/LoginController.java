package com.jenkins.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jenkins.service.IUserService;
import com.jenkins.vo.User;



@Controller
public class LoginController extends BaseController {
	@Autowired
	private IUserService userservice;

	private void getRemortIP(String username) {
		HttpServletRequest request = this.getRequest();
		Map<String, String> map = new HashMap<String, String>();
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		map.put("username", username);
		map.put("loginIp", ip);
		userservice.saveIP(map);
	}

	@RequestMapping(value = "/login", produces = "text/html;charset=UTF-8")
	public ModelAndView toLogin() throws ClassNotFoundException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
	@RequestMapping(value = "/loginon",method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
//	@ResponseBody
	public String doLogin(String username,String password,HttpSession session,HttpServletRequest request) throws ClassNotFoundException {
		username=request.getParameter("username");
		password=request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		User user=userservice.findByUsernameAndPassword(username, password);
		
		if("test".equals(username)&& "123".equals(password)) {		
	//		System.out.println("---->"+user);	
			session.setAttribute("user", user);
			
			// String username1=(String)session.getAttribute("user");
			return "redirect:index";
		}else {
			System.out.println("这是一只小小小鸟！！！！");
			return "redirect:login";
		
		}
		
		
		
	}
//	@RequestMapping(value = "/logincheck", produces = "text/html;charset=UTF-8")
//	public String loginCheck(@RequestParam(name="username") String username,
//			@RequestParam(name="password") String password,Map<String,Object> map,HttpSession session,HttpServletRequest request) {
//		username = request.getParameter("username");
//		System.out.println(username);
//		if((username!=null &&username!="")&&(password!=null&& password!="")) {
//			User user=userservice.findByUsernameAndPassword(username, password);
//			if(user!=null) {
//				session.setAttribute("user", user);
//				return "redirect /index";
//			}else {
//				map.put("msg", "用户名或密码错误");
//				return "err";
//			}
//		}else {
//			map.put("msg", "缺少必要的参数");
//			return "缺少必要参数";
//		}
//	
//		
//	}
	
	@RequestMapping(value = "/index", produces = "text/html;charset=UTF-8")
	public ModelAndView toMain(HttpSession session,HttpServletRequest request,ModelMap model) {
		ModelAndView mv=new ModelAndView();
		User user=(User) request.getSession().getAttribute("user");
	
		String username=user.getUsername();	
		System.out.println(username+"<<<<");
//		mv.addObject("username", userservice.findByUsername(username));
		model.addAttribute("user",userservice.findByUsername(username));
		
		
		return mv;
		
}
}