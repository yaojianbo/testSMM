package com.bobo.testSSM.controller.request;

public class ShowUserListRequest 
{
	private int pageNum = 1; // 默认查询第一页
	
	private int pageSize = 10; // 默认每页10条记录
	
	private int isDelete = 1; // 默认查询状态为未删除的记录

	public int getPageNum()
	{
		return pageNum;
	}

	public void setPageNum(int pageNum)
	{
		this.pageNum = pageNum;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public int getIsDelete()
	{
		return isDelete;
	}

	public void setIsDelete(int isDelete)
	{
		this.isDelete = isDelete;
	}
	
	
}
