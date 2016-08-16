package com.bobo.testSSM.dao;

import com.bobo.testSSM.pojo.User;

public interface UserDao
{
	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer id);
	
	User selectByUserName(String userName);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);
}