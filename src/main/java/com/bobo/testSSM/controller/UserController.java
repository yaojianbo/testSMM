package com.bobo.testSSM.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bobo.testSSM.controller.request.GetUserRequest;
import com.bobo.testSSM.pojo.User;
import com.bobo.testSSM.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController 
{
	private static Logger Logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/showUserJSP")
	public String toIndex(HttpServletRequest request, Model model)
	{
		int userId = Integer.parseInt(request.getParameter("id"));
		
		Logger.debug("Request for->/showUserJSP:id=" + userId);
		
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		return "showUser";
	}
	
	@RequestMapping("/getUser")
	@ResponseBody
	public Object getUser(@RequestBody GetUserRequest request)
	{
		Logger.debug("Request for->/getUser:userID=" + request.getUserID());
		
		User user = this.userService.getUserById(request.getUserID());
		return user;
	}
}
