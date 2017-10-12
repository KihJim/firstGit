package com.taotao.testController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.ItemDesc;
import com.taotao.service.ItemDescService;

@Controller
@RequestMapping(value="/item/desc")
public class ItemDescController {
	@Autowired
	ItemDescService service;
	
	/**
	 * 富文本回显
	 */ 
	@RequestMapping(value="{itemId}" ,method=RequestMethod.GET)
	@ResponseBody
	public ItemDesc queryitemDescByid(@PathVariable("itemId")Long itemId) {
		return service.queryById(itemId);
	}
}
