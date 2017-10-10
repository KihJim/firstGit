package com.taotao.service;

import com.taotao.pojo.Item;

public interface ItemService {
	
	Long insertItemAndDesc(Item item, String desc);
	
}

