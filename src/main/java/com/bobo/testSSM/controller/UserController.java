package com.bobo.testSSM.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bobo.testSSM.pojo.User;
import com.bobo.testSSM.service.IUserService;


@Controller
@RequestMapping("/user")
public class UserController 
{
	/**
	 * @Resource
	 * 默认安装名称进行装配，名称可以通过name属性进行指定，如果没有指定name属性，当注解写在字段上时，默认取字段名进行安装名称查找，
	 * 如果注解写在setter方法上默认取属性名进行装配。
	 * 当找不到与名称匹配的bean时才按照类型进行装配。
	 * 但是需要注意的是，如果name属性一旦指定，就只会按照名称进行装配。
	 */
	@Resource
	private IUserService userService;
	
	@RequestMapping("/showUser")
	public String toIndex(HttpServletRequest request,Model model)
	{
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		return "showUser";
	}
	
	@RequestMapping("/getUser")
	@ResponseBody
	public Object test(HttpServletRequest request) 
	{
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = this.userService.getUserById(userId);
		return user;
	}
}
