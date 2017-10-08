package com.taotao.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TestMapper;
import com.taotao.service.TestService;

@Service
public class TestServiceImpl implements TestService{
	@Autowired
	TestMapper tm;
	
	public String queryDate() {
		System.out.println("成功调用");
		return tm.queryDate();
	}
}
