package com.bobo.testSSM.service;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.JedisPool;

public class BaseService 
{
	@Autowired
	private JedisPool jedisPool;

	public JedisPool getJedisPool() 
	{
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) 
	{
		this.jedisPool = jedisPool;
	}
}
