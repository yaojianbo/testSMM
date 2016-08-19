package com.bobo.testSSM.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * mybatis mapper key工具类
 * 
 * @author pengpeng
 * @date 2013-10-14 下午9:47:16
 * @version 1.0
 */
public class MybatisUtils
{

	public static String getMapperKey(Class<?> mapperClass, String key)
	{
		return mapperClass.getName() + "Mapper." + key;
	}

	public static boolean isEmpty(Object paramObj)
	{
		return !isNotEmpty(paramObj);
	}

	public static boolean isNotEmpty(Object paramObj)
	{
		if (paramObj == null)
		{
			return false;
		}
		if (paramObj instanceof String)
		{
			String str = (String) paramObj;
			return !str.trim().equals("");
		}
		if (paramObj.getClass().isArray())
		{
			return Array.getLength(paramObj) > 0;
		}
		if (paramObj instanceof Map)
		{
			Map<?, ?> map = (Map<?, ?>) paramObj;
			return !map.isEmpty();
		}
		if (paramObj instanceof Collection)
		{
			Collection<?> collection = (Collection<?>) paramObj;
			return !collection.isEmpty();
		}
		return true;
	}

	/**
	 * 是否设置成null
	 * 
	 * @param paramObj
	 * @return
	 */
	public static boolean mkNone(String paramObj)
	{
		return "none".equals(paramObj);
	}

	public static boolean notMkNone(String paramObj)
	{
		return !mkNone(paramObj);
	}

	public static boolean isArrayOrCollection(Object paramObj)
	{
		if (paramObj == null)
		{
			return false;
		}
		if (paramObj instanceof Collection || paramObj.getClass().isArray())
		{
			return true;
		}
		return false;
	}

}
