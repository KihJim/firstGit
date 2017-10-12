package com.taotao.serviceImpl;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.taotao.mapper.ItemDescMapper;
import com.taotao.mapper.ItemMapper;
import com.taotao.pojo.Item;
import com.taotao.pojo.ItemDesc;
import com.taotao.service.ItemService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
@Service
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {

	@Autowired
	private ItemDescMapper deseMapper;
	@Autowired
	private ItemMapper itemMapper;
	/**
	 * 插入
	 */
	@Override
	public Long insertItemAndDesc(Item item, String desc) {
		super.saveSelective(item);
		ItemDesc idesc = new ItemDesc();
		//id
		idesc.setItemId(item.getId());
		//富文本内容
		idesc.setItemDesc(desc);
		//创建日期
		idesc.setCreated(new Date());
		//更改日期
		idesc.setUpdated(new Date());
		//插入
		deseMapper.insertSelective(idesc);
		return item.getId();
	}

	/**
	 * 商品列表
	 */
	@Override
	public Map<String, Object> itemList(String title, Integer page, Integer rows) {
		//分页
		PageHelper.startPage(page, rows);
		//条件查询
		Example example = new Example(Item.class);
		try {
			if(title != null && !title.equals("")) {
				title = URLDecoder.decode(title,"utf-8");
				Criteria cri = example.createCriteria();
				cri.andLike("title", "%" +title+ "%");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//排序
		example.orderBy("updateTime").desc();
		//结果集
		List<Item> list = itemMapper.selectByExample(example);
		
		Map<String, Object> map = new HashMap<>();
		map.put("total", list.size());
		map.put("rows", list);
		return map;
	}


	@Override
	public void changeStatus(Byte status, Long[] ids) {
		Item item = new Item();
		item.setStatus(status);
		
		Example example = new Example(Item.class);
		Criteria c = example.createCriteria();
		c.andIn("id", Arrays.asList(ids));
		itemMapper.updateByExampleSelective(item, example);
	}
	

}
