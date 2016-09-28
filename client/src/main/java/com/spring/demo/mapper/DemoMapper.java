package com.spring.demo.mapper;

import java.util.List;

import com.spring.demo.po.Config;

public interface DemoMapper {
	public List<Config> select();
	
	public Config connect();
}
