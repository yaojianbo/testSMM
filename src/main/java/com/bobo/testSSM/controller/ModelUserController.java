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
	
	@RequestMapping("/showUserList")
	public String toIndex(HttpServletRequest request, Model model)
	{
		int pageNum = 1; // 默认查询第一页
		int pageSize = 10; // 默认每页10条记录
		int isDelete = 1; // 默认查询状态为未删除的记录
		
		try
		{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
			isDelete = Integer.parseInt(request.getParameter("isDelete"));
		}
		catch (Exception e)
		{
			model.addAttribute("success", "-1");
			model.addAttribute("msg", "request parameter error, please check");
			model.addAttribute("userList", null);
			
			return "showUserList";
		}
		
		Logger.info("Request for->/showUserListJSP:pageNum=" + pageNum 
				+ ";pageSize=" + pageSize + ";isDelete=" + isDelete);
		
		List<User> userList = this.userService.queryByPage(pageNum, pageSize, isDelete);
		
		// Model内容设置
		model.addAttribute("success", "1");
		model.addAttribute("msg", "request success");
		model.addAttribute("userList", userList);
		
		// 调取对应的VIEW(.jsp)
		return "showUserList";
	}
}
