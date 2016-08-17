package com.bobo.testSSM.dao;

import java.util.List;

import com.bobo.testSSM.pojo.UserRole;

public interface UserRoleDao
{
	int deleteByPrimaryKey(Integer id);

	int insert(UserRole record);

	int insertSelective(UserRole record);

	UserRole selectByPrimaryKey(Integer id);

	List<UserRole> selectByUserID(Integer user_id);

	int updateByPrimaryKeySelective(UserRole record);

	int updateByPrimaryKey(UserRole record);
}