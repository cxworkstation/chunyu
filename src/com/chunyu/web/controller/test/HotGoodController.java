package com.chunyu.web.controller.test;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chunyu.web.service.HotGoodService;
import com.chunyun.web.model.Goods;


@Controller
@RequestMapping("hotGood/manage")
public class HotGoodController extends BaseController{
	
	@Resource(name="hotGoodService")
	private HotGoodService hotGoodService;
	
	
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String index(){
		return "hotGood/index";
	}
	
	@RequestMapping(value="show")
	public void show(HttpServletResponse response,int data){
	    List<Goods> list=hotGoodService.selectRangeGood(data);
	    printListToHtml(response, list);
	}

}
