package com.taotao.testController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.Item;
import com.taotao.pojo.ItemDesc;
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
	/**
	 * 商品列表
	 * @param title
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> itemList(String title,Integer page,Integer rows){
		return service.itemList(title,page,rows);
	}
	/**
	 * 上架
	 */
	@RequestMapping(value="/control/{status}",method=RequestMethod.POST)
	public ResponseEntity<Object> reshelf(Long [] ids ,@PathVariable("status")String status){
		try {
			System.out.println(status);
			Byte statusCode = 0;
			if(status.equals("reshelf") ) {
				//上架
				statusCode = 1;
			}else if(status.equals("instock")){
				//下架
				statusCode = 2;
			}else if(status.equals("delete")) {
				//删除(逻辑)
				statusCode = 3;
			}
			if(statusCode != 0) {
				service.changeStatus(statusCode,ids);
			}
			return ResponseEntity.ok().body(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
//	/**
//	 * 下架
//	 */
//	@RequestMapping(value="/instock",method=RequestMethod.POST)
//	public void instock(Long [] ids) {
//		Byte status = 2;
//		service.changeStatus(status,ids);
//	}
//	/**
//	 * 删除
//	 * @param status
//	 */
//	@RequestMapping(value="/delete",method=RequestMethod.POST)
//	public void delete(Long [] ids) {
//		Byte status = 3;
//		service.changeStatus(status,ids);
//	}
}
