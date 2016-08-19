package com.bobo.testSSM.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

/**
 * 用于对查询结果集的每行数据部分字段进行转义的ResultHandler
 * 
 * @author pengpeng
 * @date 2013-10-14 下午10:29:24
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class EscapeResultHandler<T> implements ResultHandler
{

	private final List<T> list;

	private EscapeFilter<T> escapeFilter;

	public EscapeResultHandler()
	{
		list = new ArrayList<T>();
	}

	public EscapeResultHandler(EscapeFilter<T> escapeFilter)
	{
		this();
		this.escapeFilter = escapeFilter;
	}

	public EscapeResultHandler(ObjectFactory objectFactory)
	{
		list = objectFactory.create(List.class);
	}

	public void handleResult(ResultContext context)
	{
		T element = (T) context.getResultObject();
		try
		{
			list.add(element);
		} 
		finally
		{
			if (escapeFilter != null)
			{
				escapeFilter.doEscapeFilter(element);
			}
		}
	}

	public List<T> getResultList()
	{
		return list;
	}
}
