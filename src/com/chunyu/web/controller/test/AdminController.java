package com.chunyu.web.controller.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("admin/manage")
public class AdminController extends BaseController{
	
	private Logger log=LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="menu",method=RequestMethod.GET)
	public String menu(){
		return "menu";
	}
	
	@RequestMapping(value="main",method=RequestMethod.GET)
	public String main(){
		return "main";
	}
	
	@RequestMapping(value="top",method=RequestMethod.GET)
	public String top(){
		return "top";
	}
	
}
