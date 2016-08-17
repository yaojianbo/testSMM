package com.bobo.testSSM.dao;

import java.util.List;
import java.util.Map;

import com.bobo.testSSM.pojo.User;

public interface UserDao
{
	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer id);
	
	User selectByUserName(String userName);
	
	List<User> queryByPage(Map<String, Object> parameterMap);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);
}