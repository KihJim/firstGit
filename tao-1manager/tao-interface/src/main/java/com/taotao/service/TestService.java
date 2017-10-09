package com.taotao.service;

import java.util.List;

public interface TestService<T> {
	//一个接口 2017-10-08 16:44:47
	String queryDate();
	
	public List<T> queryList();
}
