package com.bobo.testSSM.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 处理器拦截器 测试DEMO
 * @author YAOJIANBO
 *
 */
public class MyHandlerInterceptor implements HandlerInterceptor
{
	private static Logger Logger = LoggerFactory.getLogger(MyHandlerInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		String url = request.getRequestURI();
		
		Logger.info("MyHandlerInterceptor.......preHandle:URL:" + url);
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception
	{
		Logger.info("MyHandlerInterceptor.......postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception
	{
		Logger.info("MyHandlerInterceptor.......afterCompletion");
	}

}
