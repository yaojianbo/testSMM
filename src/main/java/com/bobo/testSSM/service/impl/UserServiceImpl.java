package com.bobo.testSSM.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bobo.testSSM.dao.IUserDao;
import com.bobo.testSSM.pojo.User;
import com.bobo.testSSM.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService 
{
	@Resource
	private IUserDao userDao;

	@Override
	public User getUserById(int userID) 
	{
		return this.userDao.selectByPrimaryKey(userID);
	}

}
