package com.bobo.testSSM.service;

import java.util.List;

import com.bobo.testSSM.pojo.User;

public interface IUserService 
{
	public List<User> queryByPage(int pageNum, int pageSize);
}
