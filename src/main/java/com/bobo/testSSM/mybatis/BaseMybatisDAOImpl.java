package com.bobo.testSSM.mybatis;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mybatis DAO接口实现
 * 
 * @author pengpeng
 * @date 2013-10-14
 * @version 1.0
 */
public abstract class BaseMybatisDAOImpl implements BaseMybatisDAO
{

	/**
	 * 默认分页总数查询statementKey的后缀
	 * 例如分页查询数据的statementKey为'getXxxxList',则针对该分页的查询总记录数的statementKey一定要以
	 * <code>DEFAULT_PAGING_COUNT_STATEMENT_KEY_SUFFIX</code>结尾
	 */
	public static final String DEFAULT_PAGING_COUNT_STATEMENT_KEY_SUFFIX = "_count";

	/**
	 * 大批量操作时,每100笔批量操作后执行jdbc操作：stmt.executeBatch();提交部分操作的结果到数据库
	 */
	public static final int DEFAULT_FLUSH_BATCH_SIZE = 100;

	private SqlSessionTemplate sqlSessionTemplate;

	@Resource(name = "sqlSessionTemplate")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate)
	{
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public int insert(String statementKey, Object paramObj)
	{
		return sqlSessionTemplate.insert(statementKey, paramObj);
	}

	public <T> int[] batchInsert(String statementKey, List<T> paramObjList)
	{
		SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		try
		{
			for (int i = 0; i < paramObjList.size(); i++)
			{
				session.insert(statementKey, paramObjList.get(i));
				if ((i + 1) % DEFAULT_FLUSH_BATCH_SIZE == 0)
				{
					session.flushStatements();
				}
			}
			session.commit();
		} 
		catch (Exception e)
		{
			session.rollback();
		} 
		finally
		{
			session.close();
		}
		return null;
	}

	public int update(String statementKey, Object paramObj)
	{
		return sqlSessionTemplate.update(statementKey, paramObj);
	}

	public <T> int[] batchUpdate(String statementKey, List<T> paramObjList)
	{
		return null;
	}

	public int delete(String statementKey, Object paramObj)
	{
		return sqlSessionTemplate.delete(statementKey, paramObj);
	}

	public <T> int[] batchDelete(String statementKey, List<T> paramObjList)
	{
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> T selectOne(String statementKey, Object paramObj)
	{
		return (T) sqlSessionTemplate.selectOne(statementKey, paramObj);
	}

	public <T> T selectOne(String statementKey, Object paramObj, EscapeFilter<T> escapeFilter)
	{
		EscapeResultHandler<T> resultHandler = new EscapeResultHandler<T>(escapeFilter);
		sqlSessionTemplate.select(statementKey, paramObj, resultHandler);
		List<T> list = resultHandler.getResultList();
		if (list == null || list.isEmpty())
		{
			return null;
		} 
		else if (list.size() == 1)
		{
			return list.get(0);
		}
		throw new TooManyResultsException(
				"Expected one result (or null) to be returned by selectOne(), but found: " + list.size());
	}

	public <T> List<T> selectList(String statementKey, Object paramObj)
	{
		return sqlSessionTemplate.selectList(statementKey, paramObj);
	}

	public <T> List<T> selectList(String statementKey, Object paramObj, EscapeFilter<T> escapeFilter)
	{
		EscapeResultHandler<T> resultHandler = new EscapeResultHandler<T>(escapeFilter);
		sqlSessionTemplate.select(statementKey, paramObj, resultHandler);
		return resultHandler.getResultList();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public <T> PageList<T> selectList(String statementKey, Object paramObj, Pager pager)
	{
		SqlSession sqlSession = sqlSessionTemplate;
		int offset = (pager.getCurrentPage() - 1) * pager.getPageSize();
		List<T> dataList = sqlSession.selectList(statementKey, paramObj, new RowBounds(offset, pager.getPageSize()));
		int totalRowCount = (Integer) sqlSession.selectOne(statementKey + DEFAULT_PAGING_COUNT_STATEMENT_KEY_SUFFIX,
				paramObj);
		pager.setTotalRowCount(totalRowCount);
		pager.setPageItems(PaginationUtils.getPaginationItems(pager));
		return new PageList<T>(dataList, pager);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public <T> PageList<T> selectList(String statementKey, Object paramObj, EscapeFilter<T> escapeFilter, Pager pager)
	{
		SqlSession sqlSession = sqlSessionTemplate;
		int offset = (pager.getCurrentPage() - 1) * pager.getPageSize();
		EscapeResultHandler<T> resultHandler = new EscapeResultHandler<T>(escapeFilter);
		sqlSession.select(statementKey, paramObj, new RowBounds(offset, pager.getPageSize()), resultHandler);
		int totalRowCount = (Integer) sqlSession.selectOne(statementKey + DEFAULT_PAGING_COUNT_STATEMENT_KEY_SUFFIX,
				paramObj);
		List<T> dataList = resultHandler.getResultList();
		pager.setTotalRowCount(totalRowCount);
		pager.setPageItems(PaginationUtils.getPaginationItems(pager));
		return new PageList<T>(dataList, pager);
	}

	public <T> T execute(SqlSessionCallback<T> action)
	{
		return action.doInSqlSession(sqlSessionTemplate);
	}

}
