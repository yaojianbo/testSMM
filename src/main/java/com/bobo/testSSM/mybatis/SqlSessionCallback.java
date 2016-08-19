package com.bobo.testSSM.mybatis;

import org.apache.ibatis.session.SqlSession;

/**
 * SqlSession callback
 * 
 * @author pengpeng
 * @date 2013-10-14 下午10:29:34
 * @version 1.0
 */
public interface SqlSessionCallback<T>
{

	public T doInSqlSession(SqlSession sqlSession);
}
