<%@ page language="java" pageEncoding="UTF-8"%>  
管理员可访问<a href="<%=request.getContextPath()%>/user/showUserList" target="_self">用户信息列表页面</a>  
<br/>  
<br/>  
普通用户可访问<a href="<%=request.getContextPath()%>/jsp/info.jsp" target="_self">用户列表页面</a>  
<br/>  
<br/>  
<a href="<%=request.getContextPath()%>/auth/logout" target="_self">Logout</a>
