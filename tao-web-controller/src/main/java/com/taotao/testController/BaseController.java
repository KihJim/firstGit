package com.taotao.testController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("page")
public class BaseController {

	@RequestMapping("/{jsp}")
	public String jumpLocaltion(@PathVariable("jsp")String jsp) {
		return jsp;
	}
}
