package com.bobo.testSSM.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bobo.testSSM.pojo.User;
import com.bobo.testSSM.service.IUserService;

/**
 * 采用ModelAndView模式的controller
 * @author YAOJIANBO
 *
 */
@Controller
@RequestMapping("/userModel")
public class ModelUserController 
{
	private static Logger Logger = LoggerFactory.getLogger(ModelUserController.class);
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/showUserJSP")
	public String toIndex(HttpServletRequest request, Model model)
	{
		int userId = Integer.parseInt(request.getParameter("userID"));
		
		Logger.debug("Request for->/showUserJSP:userID=" + userId);
		
		User user = this.userService.getUserById(userId);
		
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		userList.add(user);// 多添加一个重复的元素，制造列表效果
		
		// Model内容设置
		model.addAttribute("userList", userList);
		
		// 调取对应的VIEW(.jsp)
		return "showUser";
	}
}
