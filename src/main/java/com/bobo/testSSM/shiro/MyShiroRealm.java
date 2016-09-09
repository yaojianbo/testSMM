package com.bobo.testSSM.shiro;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.bobo.testSSM.dao.pojo.Permission;
import com.bobo.testSSM.dao.pojo.User;
import com.bobo.testSSM.service.IAuthService;

public class MyShiroRealm extends AuthorizingRealm
{
	private IAuthService authService;
	
	public IAuthService getAuthService()
	{
		return authService;
	}

	public void setAuthService(IAuthService authService)
	{
		this.authService = authService;
	}
	
	/**
	 * 为当前登录的Subject授予角色和权限</p>
	 * 本例中该方法的调用时机为需授权资源被访问时</p>
	 * 并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache</p>
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		String currentUserName = (String)super.getAvailablePrincipal(principals);
		if (!StringUtils.isEmpty(currentUserName))
		{
			List<Permission> permissions = authService.getPermissionsByUserName(currentUserName);
			if (null != permissions && !permissions.isEmpty())
			{
				SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
				for (Permission permission : permissions)
				{
					info.addStringPermission(permission.getUrl());
				}
				return info;
			}
		}
		
		return null;
	}

	/**
	 * 验证当前登录的Subject</p>
	 * 该方法的调用时机为AuthController.login()方法中执行Subject.login()时</p>
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
	{
		UsernamePasswordToken utoken = (UsernamePasswordToken) token;
		System.out.println("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
		
		// 通过表单接收的用户名
		String username = utoken.getUsername();
		if (null != username && !"".equals(username))
		{
			User user = authService.getUserByUserName(username);
			if (null != user)
			{
				this.setSession("currentUser", user);
				return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
			}
		}
		
		return null;
	}
	
	/** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
     */  
    private void setSession(Object key, Object value)
    {
        Subject currentUser = SecurityUtils.getSubject();
        if(null != currentUser)
        {
            Session session = currentUser.getSession();
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if(null != session)
            {
                session.setAttribute(key, value);
            }
        }
    }
}
