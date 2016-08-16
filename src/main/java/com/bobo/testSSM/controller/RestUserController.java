package com.bobo.testSSM.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bobo.testSSM.controller.request.UserLoginRequest;
import com.bobo.testSSM.pojo.User;
import com.bobo.testSSM.service.IAuthService;

/**
 * UserController:用于demo的controller建立
 * 
 * @author YAOJIANBO
 * RestController:相当于类上加@Controller和方法上加@ResponseBody的结合体，两个标注合并起来的作用
 *
 */
@RestController
@RequestMapping("/userRest")
public class RestUserController
{
	private static Logger Logger = LoggerFactory.getLogger(RestUserController.class);
	
	@Autowired
	private IAuthService authService;
	
	@RequestMapping("/login")
	public Object login(@RequestBody UserLoginRequest request)
	{
		Logger.debug("Request for->/getUser:userName=" + request.getUserName());
		
		User user = this.authService.getUserByUserName(request.getUserName());
		return user;
	}
}
