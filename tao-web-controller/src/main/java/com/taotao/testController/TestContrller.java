package com.taotao.testController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.service.TestService;

@Controller
@RequestMapping("tt")
public class TestContrller {
	@Autowired
	TestService ts;
	@RequestMapping("query")
	@ResponseBody
	public String tt() {
		System.out.println("进入成功!");
		return ts.queryDate();
	}
	
	@RequestMapping("list")
	@ResponseBody
	public List tt2() {
		System.out.println("进入成功!");
		return ts.queryList();
	}
}
