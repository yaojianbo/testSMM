<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户列表</title>
  </head>
  
  <body>
  	<form action="${pageContext.request.contextPath }/userModel/showUserJSP?userID=5" method="post">
  		查询条件：
        <table width="100%" border=1>
            <tr>
                <td><input type="submit" value="查询" /></td>
            </tr>
        </table>
                        用户列表：
        <table width="100%" border=1>
            <tr>
                <td>用户ID</td>
                <td>用户姓名</td>
                <td>用户密码</td>
                <td>用户年龄</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <!-- <td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td> -->
                    <td>${user.password}</td>
                    <td>${user.age}</td>

                    <td><a href="#">修改</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>
  </body>
</html>
