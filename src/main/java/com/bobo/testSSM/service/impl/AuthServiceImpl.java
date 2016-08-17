package com.bobo.testSSM.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bobo.testSSM.dao.PermissionDao;
import com.bobo.testSSM.dao.RoleDao;
import com.bobo.testSSM.dao.RolePermissionDao;
import com.bobo.testSSM.dao.UserDao;
import com.bobo.testSSM.dao.UserRoleDao;
import com.bobo.testSSM.pojo.Permission;
import com.bobo.testSSM.pojo.Role;
import com.bobo.testSSM.pojo.RolePermission;
import com.bobo.testSSM.pojo.User;
import com.bobo.testSSM.pojo.UserRole;
import com.bobo.testSSM.service.IAuthService;
import com.bobo.testSSM.service.BaseService;
import com.bobo.testSSM.util.CommonUtil;
import com.bobo.testSSM.util.JSONUtil;

@Service("authService")
public class AuthServiceImpl extends BaseService implements IAuthService
{

	private static Logger Logger = LoggerFactory.getLogger(AuthServiceImpl.class);
	
	private static String TESTSSM_Auth_USER_MAP = "testsmm.auth.user.map";
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RolePermissionDao rolePermissionDao;
	
	@Autowired
	private PermissionDao permissionDao;
	
	@Override
	public List<Permission> getPermissionsByUserName(String userName)
	{
		// 获取该用户信息
		User user = getUserByUserName(userName);
		if (user == null)
		{
			return null;
		}
		
		// 判断该用户是否绑定角色
		List<UserRole> userRoleList = userRoleDao.selectByUserID(user.getId());
		if (CommonUtil.isEmpty(userRoleList))
		{
			return null;
		}
		
		List<Permission> permissionList = new ArrayList<Permission>(); 
		for (UserRole userRole : userRoleList)
		{
			// 根据角色Role信息获取该角色Role拥有的权限列表
			List<RolePermission> rolePermissionList = rolePermissionDao.selectByRoleID(userRole.getRoleId());
			if (!CommonUtil.isEmpty(rolePermissionList))
			{
				for (RolePermission rolePermission : rolePermissionList)
				{
					Permission permission = permissionDao.selectByPrimaryKey(rolePermission.getPermissionId());
					if (null != permission)
					{
						permissionList.add(permission);
					}
				}
			}
		}
		return permissionList;
	}
	
	@Override
	public User getUserByUserName(String userName)
	{
		Logger.debug("getUserByUserName : userName = " + userName);

		User user = null;

		boolean isCacheExist = getJedisPool().getResource().hexists(TESTSSM_Auth_USER_MAP, userName);
		if (isCacheExist)
		{
			String user_json = getJedisPool().getResource().hget(TESTSSM_Auth_USER_MAP, userName);
			if (!CommonUtil.isEmpty(user_json))
			{
				user = (User) JSONUtil.json2Object(user_json, User.class);
			}
		} 
		else
		{
			user = this.userDao.selectByUserName(userName);
			getJedisPool().getResource().hset(TESTSSM_Auth_USER_MAP, userName, JSONUtil.bean2json(user));
		}

		return user;
	}
	
	@Override
	public List<Role> getRolesByUserID(Integer userID)
	{
		Logger.debug("getRolesByUserID : userID = " + userID);

		// 判断该用户是否绑定角色
		List<UserRole> userRoleList = userRoleDao.selectByUserID(userID);
		if (CommonUtil.isEmpty(userRoleList))
		{
			return null;
		}
		
		List<Role> roleList = new ArrayList<Role>(); 
		for (UserRole userRole : userRoleList)
		{
			Role role = roleDao.selectByPrimaryKey(userRole.getRoleId());
			if (null != role)
			{
				roleList.add(role);
			}
		}

		return roleList;
	}

}
