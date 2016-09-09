package com.bobo.testSSM.dao;

import java.util.List;

import com.bobo.testSSM.dao.pojo.RolePermission;


public interface RolePermissionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Integer id);
    
    List<RolePermission> selectByRoleID(Integer role_id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);
}