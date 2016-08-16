package com.bobo.testSSM.controller;

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
	
	@RequestMapping("/showUserListJSP")
	public String toIndex(HttpServletRequest request, Model model)
	{
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		
		Logger.debug("Request for->/showUserListJSP:pageNum=" + pageNum + ";pageSize=" + pageSize);
		
		List<User> userList = this.userService.queryByPage(pageNum, pageSize);
		
		// Model内容设置
		model.addAttribute("userList", userList);
		
		// 调取对应的VIEW(.jsp)
		return "showUserList";
	}
}
