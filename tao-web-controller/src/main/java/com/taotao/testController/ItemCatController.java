package com.taotao.testController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.pojo.Item;
import com.taotao.pojo.ItemCat;
import com.taotao.service.ItemCatService;
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	@Autowired
	private ItemCatService service;
	/**
	 * 类目表加载
	 * @param parentId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ItemCat>> queryItemCatListByParentId(@RequestParam(value="id",defaultValue="0")Long parentId) {
		
		try {
			ItemCat itemCat = new ItemCat();
			itemCat.setParentId(parentId);
			
			List<ItemCat> queryListByWhere = service.queryListByWhere(itemCat);
			
			return ResponseEntity.ok().body(queryListByWhere);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 不知道什么鬼
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/query/{page}", method = RequestMethod.GET)
	public ResponseEntity<List<ItemCat>> queryListByPage(@PathVariable("page")Integer page,
			@RequestParam(value="rows",defaultValue="10")Integer rows){
		try {
			List<ItemCat> list = service.queryListByPage(page, rows);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); 
		
	}
	
}
