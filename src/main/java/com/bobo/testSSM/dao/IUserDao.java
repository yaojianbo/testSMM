package com.bobo.testSSM.dao;

import com.bobo.testSSM.pojo.User;

public interface IUserDao 
{
	public int deleteByPrimaryKey(Integer id);

	public int insert(User user);

	public int insertSelective(User user);

	public User selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(User user);

	public int updateByPrimaryKey(User user);
}