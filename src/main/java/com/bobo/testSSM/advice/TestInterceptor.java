package com.bobo.testSSM.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TestInterceptor implements MethodInterceptor 
{
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable 
	{
		System.out.println("AOP TestInterceptor:" + invocation.getMethod().getDeclaringClass().getName());
		
		// 放行,交给下一个拦截器或者执行被拦截的目标方法
		return invocation.proceed();
	}

}
