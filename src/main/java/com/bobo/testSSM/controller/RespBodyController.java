package com.bobo.testSSM.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bobo.testSSM.controller.request.GetUserRequest;
import com.bobo.testSSM.controller.request.ShowUserListRequest;
import com.bobo.testSSM.controller.response.BaseResponse;
import com.bobo.testSSM.dao.pojo.User;
import com.bobo.testSSM.service.IAuthService;
import com.bobo.testSSM.service.IUserService;
import com.bobo.testSSM.util.JSONUtil;

/**
 * 测试返回JSON对象的controller
 * 
 * @author YAOJIANBO
 * RestController:相当于类上加@Controller和方法上加@ResponseBody的结合体，两个标注合并起来的作用
 *
 */
@RestController
@RequestMapping("/respBodyTest")
public class RespBodyController
{
	private static Logger Logger = LoggerFactory.getLogger(RespBodyController.class);
	
	@Autowired
	private IAuthService authService;
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/getUser")
	public Object getUser(@RequestBody GetUserRequest request)
	{
		Logger.info("Request for->/getUser:userName=" + request.getUserName());
		BaseResponse response = new BaseResponse();
		
		User user = this.authService.getUserByUserName(request.getUserName());
		if (null == user)
		{
			makeResponse(response, "10000", "NO DATA", null);
		}
		else
		{
			makeResponse(response, "10001", "QUERY USER SUCCESS", JSONUtil.bean2json(user));
		}
		
		return response;
	}
	
	@RequestMapping("/showUserList")
	public Object showUserList(@RequestBody ShowUserListRequest request)
	{
		BaseResponse response = new BaseResponse();
		
		int pageNum = request.getPageNum();
		int pageSize = request.getPageSize();
		int isDelete = request.getIsDelete();
		
		Logger.info("Request for->/showUserListJSP:pageNum=" + pageNum 
				+ ";pageSize=" + pageSize + ";isDelete=" + isDelete);
		
		List<User> userList = this.userService.queryByPage(pageNum, pageSize, isDelete);
		if (null == userList || userList.isEmpty())
		{
			makeResponse(response, "10000", "NO DATA", null);
		}
		else
		{
			makeResponse(response, "10001", "QUERY USER LIST SUCCESS", JSONUtil.collection2json(userList));
		}
		
		return response;
	}
	
	public void makeResponse(BaseResponse response, String code, String msg, String data)
	{
		response.setCode(code);
		response.setMsg(msg);
		response.setData(data);
	}
}
