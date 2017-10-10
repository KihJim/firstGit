package com.taotao.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.ItemDescMapper;
import com.taotao.mapper.ItemMapper;
import com.taotao.pojo.Item;
import com.taotao.pojo.ItemDesc;
import com.taotao.service.ItemService;
@Service
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {

	@Autowired
	private ItemDescMapper deseMapper;
	
	
	@Override
	public Long insertItemAndDesc(Item item, String desc) {
		super.saveSelective(item);
		ItemDesc idesc = new ItemDesc();
		//id
		idesc.setItemId(item.getId());
		//富文本内容
		idesc.setItemDesc(desc);
		//创建日期
		idesc.setCreateTime(new Date());
		//更改日期
		idesc.setUpdateTime(idesc.getCreateTime());
		//插入
		deseMapper.insertSelective(idesc);
		return item.getId();
	}

}
