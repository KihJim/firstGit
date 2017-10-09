package com.taotao.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.ItemCatMapper;
import com.taotao.mapper.TestMapper;
import com.taotao.pojo.ItemCat;
import com.taotao.service.TestService;

@Service
public class TestServiceImpl implements TestService{
	@Autowired
	TestMapper tm;
	@Autowired
	ItemCatMapper ic;
	
	public String queryDate() {
		System.out.println("成功调用");
		return tm.queryDate();
	}
	
	public List<ItemCat> queryList() {
		return ic.selectAll();
	}
}
