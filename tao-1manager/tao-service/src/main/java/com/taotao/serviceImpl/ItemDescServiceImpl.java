package com.taotao.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.ItemDescMapper;
import com.taotao.pojo.ItemDesc;
import com.taotao.service.ItemDescService;
@Service
public class ItemDescServiceImpl extends BaseServiceImpl<ItemDesc> implements ItemDescService {
	@Autowired
	ItemDescMapper mapper;
}
