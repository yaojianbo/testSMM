package com.bobo.testSSM.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bobo.testSSM.dao.UserDao;
import com.bobo.testSSM.pojo.User;
import com.bobo.testSSM.service.IAuthService;
import com.bobo.testSSM.service.BaseService;
import com.bobo.testSSM.util.CommonUtil;
import com.bobo.testSSM.util.JSONUtil;

@Service("authService")
public class AuthServiceImpl extends BaseService implements IAuthService
{

	private static Logger Logger = LoggerFactory.getLogger(AuthServiceImpl.class);
	
	private static String TESTSSM_USER_HASH_MAP = "testsmm.user.hashmap";
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public List<String> getPermissionsByUserName(String userName)
	{
		User user = getUserByUserName(userName);
		if (user == null)
		{
			return null;
		}
		return null;
	}
	
	@Override
	public User getUserByUserName(String userName)
	{
		Logger.debug("getUserByUserName : userName = " + userName);

		User user = null;

		boolean isCacheExist = getJedisPool().getResource().hexists(TESTSSM_USER_HASH_MAP, userName);
		if (isCacheExist)
		{
			String user_json = getJedisPool().getResource().hget(TESTSSM_USER_HASH_MAP, userName);
			if (!CommonUtil.isEmpty(user_json))
			{
				user = (User) JSONUtil.json2Object(user_json, User.class);
			}
		} 
		else
		{
			user = this.userDao.selectByUserName(userName);
			getJedisPool().getResource().hset(TESTSSM_USER_HASH_MAP, userName, JSONUtil.bean2json(user));
		}

		return user;
	}

}
