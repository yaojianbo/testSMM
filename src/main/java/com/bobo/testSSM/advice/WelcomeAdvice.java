package com.bobo.testSSM.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * 一个测试前置通知
 * @author YAOJIANBO
 *
 */
public class WelcomeAdvice implements MethodBeforeAdvice 
{

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable 
	{
		System.out.println("This is a beforeAdvice named welcomeAdvice");
	}

}
