<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">

function reloadVerifyCode()
{
    document.getElementById('verifyCodeImage').setAttribute('src', '${pageContext.request.contextPath}/auth/getVerifyCodeImage');  
}
</script>
<html>
	<body>
		<h1>user login</h1>
		<div style="color:red; font-size:22px;">${msg}</div>
		<form action="${pageContext.request.contextPath }/auth/login" method="post">
			username:<input type="text" name="username"><br/>
			password:<input type="password" name="password"><br/>
			验证：<input type="text" name="verifyCode"/>
         	&nbsp;&nbsp;
         	<img id="verifyCodeImage" onclick="reloadVerifyCode()" src="<%=request.getContextPath()%>/auth/getVerifyCodeImage"/><br/>
			<input type="submit" value="确认">
		</form>
	</body>
</html>
