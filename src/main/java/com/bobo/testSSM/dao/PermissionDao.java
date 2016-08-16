package com.bobo.testSSM.dao;

import com.bobo.testSSM.pojo.Permission;

public interface PermissionDao
{
	int deleteByPrimaryKey(Integer id);

	int insert(Permission record);

	int insertSelective(Permission record);

	Permission selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Permission record);

	int updateByPrimaryKey(Permission record);
}