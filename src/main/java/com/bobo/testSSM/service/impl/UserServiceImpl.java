package com.bobo.testSSM.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bobo.testSSM.dao.UserDao;
import com.bobo.testSSM.pojo.User;
import com.bobo.testSSM.service.BaseService;
import com.bobo.testSSM.service.IUserService;

@Service("userService")
public class UserServiceImpl extends BaseService implements IUserService
{
	private static Logger Logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<User> queryByPage(int pageNum, int pageSize)
	{
		return null;
	}


}
