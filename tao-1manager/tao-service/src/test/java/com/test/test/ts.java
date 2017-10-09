package com.test.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.taotao.mapper.ItemCatMapper;
import com.taotao.pojo.ItemCat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext-service.xml",
								"classpath*:applicationContext-dao.xml",
								"classpath*:applicationContext-trans.xml",
								"classpath*:mybatis-config.xml"})
public class ts {
	@Autowired
	ItemCatMapper ic;
	@Test
	public void tt1() {
		List<ItemCat> selectAll = ic.selectAll();
		for (ItemCat itemCat : selectAll) {
			System.out.println(itemCat.getName());
		}
	}
}
