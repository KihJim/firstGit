package com.taotao.testController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taotao.pojo.Item;
import com.taotao.service.ItemService;
@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService service;
	/**
	 * 新商品插入
	 * @param cat
	 * @param desc
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> insertOne(Item item,String desc){
		try {
			Long insertItemAndDesc = service.insertItemAndDesc(item,desc);
			System.out.println(insertItemAndDesc);
			return ResponseEntity.ok().body(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
}
