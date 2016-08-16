package com.bobo.testSSM.pojo;

import java.util.Date;

public class User
{
	private Integer id;

	private String username;

	private String password;

	private Integer age;

	private Integer isdelete;

	private Date createdate;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username == null ? null : username.trim();
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password == null ? null : password.trim();
	}

	public Integer getAge()
	{
		return age;
	}

	public void setAge(Integer age)
	{
		this.age = age;
	}

	public Integer getIsdelete()
	{
		return isdelete;
	}

	public void setIsdelete(Integer isdelete)
	{
		this.isdelete = isdelete;
	}

	public Date getCreatedate()
	{
		return createdate;
	}

	public void setCreatedate(Date createdate)
	{
		this.createdate = createdate;
	}
}