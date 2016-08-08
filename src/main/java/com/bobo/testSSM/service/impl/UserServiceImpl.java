package com.bobo.testSSM.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bobo.testSSM.dao.IUserDao;
import com.bobo.testSSM.pojo.User;
import com.bobo.testSSM.service.BaseService;
import com.bobo.testSSM.service.IUserService;

@Service("userService")
public class UserServiceImpl extends BaseService implements IUserService 
{
	private static Logger Logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private IUserDao userDao;

	@Override
	public User getUserById(int userID) 
	{
		Logger.debug("getUserById : userID = " + userID);
		
		getJedisPool().getResource().set("bobo_test_user_name", String.valueOf(userID));
		
		return this.userDao.selectByPrimaryKey(userID);
	}

}
