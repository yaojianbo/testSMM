package com.bobo.testSSM.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bobo.testSSM.dao.UserDao;
import com.bobo.testSSM.dao.pojo.User;
import com.bobo.testSSM.service.BaseService;
import com.bobo.testSSM.service.IUserService;

@Service("userService")
public class UserServiceImpl extends BaseService implements IUserService
{
	private static Logger Logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<User> queryByPage(int pageNum, int pageSize, int isDelete)
	{
		Logger.debug("UserServiceImpl invoke->queryByPage:pageNum=" + pageNum 
				+ ";pageSize=" + pageSize + ";isDelete=" + isDelete);
		
		if (pageNum < 0)
		{
			// 页码默认为1
			pageNum = 1;
		}
		
		if (pageSize < 0)
		{
			// 每页的记录数默认为10
			pageSize = 10;
		}
		
		if (isDelete != 0 && isDelete != 1)
		{
			// 用户状态默认为1，未删除
			isDelete = 1;
		}
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("isDelete", isDelete);
		parameterMap.put("offset", ((pageNum - 1) * pageSize));
		parameterMap.put("length", pageSize);
		
		return userDao.queryByPage(parameterMap);
	}

}
