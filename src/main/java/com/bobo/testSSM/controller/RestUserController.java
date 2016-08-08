package com.bobo.testSSM.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bobo.testSSM.controller.request.GetUserRequest;
import com.bobo.testSSM.pojo.User;
import com.bobo.testSSM.service.IUserService;

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
	private IUserService userService;
	
	@RequestMapping("/getUser")
	public Object getUser(@RequestBody GetUserRequest request)
	{
		Logger.debug("Request for->/getUser:userID=" + request.getUserID());
		
		User user = this.userService.getUserById(request.getUserID());
		return user;
	}
}
