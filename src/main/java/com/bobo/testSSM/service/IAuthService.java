package com.bobo.testSSM.service;

import java.util.List;

import com.bobo.testSSM.pojo.User;

public interface IAuthService
{
	public List<String> getPermissionsByUserName(String userName);

	User getUserByUserName(String userName);
}
