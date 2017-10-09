package com.taotao.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.ItemMapper;
import com.taotao.pojo.ItemCat;
import com.taotao.service.ItemCatService;
@Service
public class ItemCatServiceImpl extends BaseServiceImpl<ItemCat> implements ItemCatService{
	@Autowired
	private ItemMapper mapper;
	
	
}
