package com.taotao.service;

import java.util.Map;

import com.taotao.pojo.Item;
import com.taotao.pojo.ItemDesc;

public interface ItemService extends BaserService<Item>{
	
	Long insertItemAndDesc(Item item, String desc);

	Map<String, Object> itemList(String title, Integer page, Integer rows);

	void changeStatus(Byte status, Long[] ids);

	
}

