package com.bobo.testSSM.service;

import java.util.List;

import com.bobo.testSSM.pojo.Permission;
import com.bobo.testSSM.pojo.Role;
import com.bobo.testSSM.pojo.User;

public interface IAuthService
{
	/**
	 * 根据userName获取用户的访问权限
	 * @param userName 用户登录名
	 * @return
	 */
	public List<Permission> getPermissionsByUserName(String userName);

	/**
	 * 根据userName获取用户实体
	 * @param userName 用户登录名
	 * @return
	 */
	User getUserByUserName(String userName);
	
	/**
	 * 根据userID获取用户角色列表
	 * @param userID 用户ID
	 * @return
	 */
	public List<Role> getRolesByUserID(Integer userID);
}
