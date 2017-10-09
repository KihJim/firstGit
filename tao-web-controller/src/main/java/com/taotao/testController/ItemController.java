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
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.ItemCat;
import com.taotao.service.ItemCatService;
@Controller
@RequestMapping("/ItemCat")
public class ItemController {
	@Autowired
	private ItemCatService service;
	
	@RequestMapping(value="/query/{page}", method = RequestMethod.GET)
	@ResponseBody
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
