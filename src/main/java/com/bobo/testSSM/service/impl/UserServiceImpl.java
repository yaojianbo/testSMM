package com.bobo.testSSM.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bobo.testSSM.dao.IUserDao;
import com.bobo.testSSM.pojo.User;
import com.bobo.testSSM.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService 
{
	@Autowired
	private IUserDao userDao;

	@Override
	public User getUserById(int userID) 
	{
		// System.out.println("getUserById 执行");
		return this.userDao.selectByPrimaryKey(userID);
	}

}
