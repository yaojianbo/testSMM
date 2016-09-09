package com.bobo.testSSM.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bobo.testSSM.dao.pojo.User;
import com.bobo.testSSM.service.IUserService;

/**
 * NomalUserController</p>
 * 采用ModelAndView模式的controller
 * @author YAOJIANBO
 *
 */
@Controller
@RequestMapping("/user")
public class NomalUserController 
{
	private static Logger Logger = LoggerFactory.getLogger(NomalUserController.class);
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/showUserList")
	public String showUserList(HttpServletRequest request, Model model)
	{
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isRemembered())
		{
        	Logger.debug("用户[" + currentUser.getPrincipal() + "]通过RememberMe登录通过");
		}
		
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
			pageNum = 1;
			pageSize = 10;
			isDelete = 1;
		}

		Logger.info("Request for->/user/showUserList:pageNum=" + pageNum 
				+ ";pageSize=" + pageSize + ";isDelete=" + isDelete);
		
		List<User> userList = this.userService.queryByPage(pageNum, pageSize, isDelete);
		
		// Model内容设置
		model.addAttribute("success", "1");
		model.addAttribute("msg", "request success");
		model.addAttribute("userList", userList);
		
		// 调取对应的VIEW(.jsp)
		return "listUser";
	}
	
	@RequestMapping("/getUserInfo")
    public String getUserInfo(HttpServletRequest request)
	{
        User currentUser = (User)request.getSession().getAttribute("currentUser");
        System.out.println("当前登录的用户为[" + currentUser.getUsername() + "]");
        request.setAttribute("currentUser", currentUser.getUsername());
        return "info";
    }
}
